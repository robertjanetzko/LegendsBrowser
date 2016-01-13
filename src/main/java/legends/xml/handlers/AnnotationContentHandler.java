package legends.xml.handlers;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.google.common.base.Predicate;

import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.annotation.XmlSubtype;
import legends.xml.annotation.XmlSubtypes;
import legends.xml.converter.ValueConverter;

public class AnnotationContentHandler extends StackContentHandler {
	private Object object;

	AnnotationConfig config, baseConfig;

	boolean subtypes = false;
	String subtypeElement;
	Map<String, AnnotationConfig> subtypeConfigs = new HashMap<>();

	String skipElement = null;
	int skipdepth = 0;

	String subtype;
	boolean unknownSubtype = false;
	private Set<String> unknownSubtypes = new HashSet<>();
	private Set<String> unknownElements = new HashSet<>();
	private List<CachedElement> cache = new ArrayList<>();

	public AnnotationContentHandler(Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this("", objectClass);
	}

	public AnnotationContentHandler(String name, Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super(name);
		this.object = objectClass.newInstance();

		baseConfig = config = analyzeClass(objectClass);

		analyzeSubtypes(objectClass);
	}

	private AnnotationConfig analyzeClass(Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		AnnotationConfig config = new AnnotationConfig();
		config.setObjectClass(objectClass);

		Predicate<AnnotatedElement> xmlAnnotation = ReflectionUtils.withAnnotation(Xml.class);

		for (final Field field : ReflectionUtils.getAllFields(objectClass, xmlAnnotation)) {
			Xml xml = field.getAnnotation(Xml.class);
			field.setAccessible(true);

			XmlConverter xmlConverter = field.getAnnotation(XmlConverter.class);
			if (xmlConverter != null) {
				ValueConverter converter = xmlConverter.value().newInstance();
				config.getValues().put(xml.value(), v -> field.set(getObject(), converter.convert(v)));
			} else {
				Class<?> type = field.getType();
				if (type == List.class && !xml.multiple()) {
					// list content
					AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.element(),
							xml.elementClass());
					ListContentHandler listHandler = new ListContentHandler(xml.value(), elementHandler);
					listHandler.setConsumer(list -> field.set(getObject(), list));
					config.getHandlers().put(xml.value(), listHandler);
				} else if (type == int.class) {
					config.getValues().put(xml.value(), v -> field.setInt(getObject(), Integer.parseInt(v)));
				} else if (type == String.class) {
					config.getValues().put(xml.value(), v -> field.set(getObject(), v));
				} else if (type == boolean.class) {
					config.getValues().put(xml.value(), v -> field.setBoolean(getObject(), true));
				} else if (!xml.multiple()) {
					AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.value(), type);
					elementHandler.setConsumer(obj -> field.set(getObject(), obj));
					config.getHandlers().put(xml.value(), elementHandler);
				} else {
					if (xml.elementClass() == int.class || xml.elementClass() == Integer.class) {
						config.getValues().put(xml.value(),
								v -> ((List<Object>) field.get(getObject())).add(Integer.parseInt(v)));
					} else if (xml.elementClass() == String.class) {
						config.getValues().put(xml.value(), v -> ((List<Object>) field.get(getObject())).add(v));
					} else {
						AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.value(),
								xml.elementClass());
						elementHandler.setConsumer(obj -> ((List<Object>) field.get(getObject())).add(obj));
						config.getHandlers().put(xml.value(), elementHandler);
					}
				}
			}
		}

		for (final Method method : ReflectionUtils.getAllMethods(objectClass, xmlAnnotation)) {
			Xml xml = method.getAnnotation(Xml.class);

			XmlConverter xmlConverter = method.getAnnotation(XmlConverter.class);
			if (xmlConverter != null) {
				ValueConverter converter = xmlConverter.value().newInstance();
				config.getValues().put(xml.value(), v -> method.invoke(getObject(), converter.convert(v)));
			} else {
				Class<?> type = method.getParameterTypes()[0];
				if (type == List.class) {
					// list content
					AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.element(),
							xml.elementClass());
					ListContentHandler listHandler = new ListContentHandler(xml.value(), elementHandler);
					listHandler.setConsumer(list -> method.invoke(getObject(), list));
					config.getHandlers().put(xml.value(), listHandler);
				} else if (type == int.class) {
					config.getValues().put(xml.value(), v -> method.invoke(getObject(), Integer.parseInt(v)));
				} else if (type == String.class) {
					config.getValues().put(xml.value(), v -> method.invoke(getObject(), v));
				} else {
					AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.value(), type);
					elementHandler.setConsumer(obj -> method.invoke(getObject(), obj));
					config.getHandlers().put(xml.value(), elementHandler);
				}
			}
		}

		return config;
	}

	private void analyzeSubtypes(Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		XmlSubtypes subs = objectClass.getAnnotation(XmlSubtypes.class);
		if (subs == null)
			return;

		subtypes = true;
		subtypeElement = subs.value();

		for (Field field : config.getObjectClass().getDeclaredFields())
			field.setAccessible(true);

		for (Class<?> subClass : new Reflections("legends").getSubTypesOf(objectClass)) {
			XmlSubtype sub = subClass.getAnnotation(XmlSubtype.class);
			if (sub == null)
				continue;

			subtypeConfigs.put(sub.value(), analyzeClass(subClass));
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (skipElement != null) {
			skipdepth++;
			return;
		}

		StackContentHandler contentHandler = config.getHandlers().get(localName);
		if (contentHandler != null) {
			pushContentHandler(contentHandler);
			return;
		}

		StringConsumer consumer = config.getValues().get(localName);
		if (consumer != null)
			return;

		skipElement = localName;
		skipdepth = 0;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (skipElement != null) {
			if (localName.equals(skipElement)) {
				if (skipdepth == 0) {
					if (!unknownSubtype && !unknownElements.contains(subtype + " - " + localName)) {
						System.out.println(name + " - unknown element: " + (subtypes ? subtype + " - " : "") + localName
								+ " = " + value.trim());
						unknownElements.add(subtype + " - " + localName);
					}
					skipElement = null;
				}
			} else
				skipdepth--;
			return;
		}

		if (subtypes && localName.equals(subtypeElement)) {
			AnnotationConfig subConfig = subtypeConfigs.get(value);
			if (subConfig != null) {
				subtype = value;
				config = subConfig;
				try {
					object = subConfig.getObjectClass().newInstance();
					for (CachedElement el : cache) {
						StringConsumer consumer = config.getValues().get(el.getElement());
						if (consumer != null) {
							try {
								consumer.accept(el.getValue());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}

			} else {
				if (!unknownSubtypes.contains(value))
					System.out.println(name + " - UNKNOWN SUBTYPE: " + value);
				unknownSubtype = true;
				unknownSubtypes.add(value);
			}
		}

		StringConsumer consumer = config.getValues().get(localName);
		if (consumer != null) {
			if (!subtypes || subtype != null) {
				try {
					consumer.accept(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				cache.add(new CachedElement(localName, value));
			}
		} else {
			super.endElement(uri, localName, qName);
		}

	}

	@Override
	protected void consume() {
		try {
			if (consumer != null && (!subtypes || subtype != null))
				consumer.accept(object);
		} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e1) {
			e1.printStackTrace();
		}
		try {
			if (subtypes) {
				config = baseConfig;
				unknownSubtype = false;
				subtype = null;
				cache.clear();
			}

			object = config.getObjectClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Object getObject() {
		return object;
	}

}
