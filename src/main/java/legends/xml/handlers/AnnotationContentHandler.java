package legends.xml.handlers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import legends.ReflectionUtils;
import legends.xml.annotation.XmlSubtype;
import legends.xml.annotation.XmlSubtypes;

public class AnnotationContentHandler extends StackContentHandler {
	private static final Log LOG = LogFactory.getLog(AnnotationContentHandler.class);

//	private static Reflections reflections;
//
//	static {
//		reflections = Reflections.collect();
//		if (reflections == null) {
//			LOG.warn("reflections unavailable");
//			reflections = new Reflections("legends");
//		}
//	}

	private Object object;

	AnnotationConfig config, baseConfig;

	boolean subtypes = false;
	String subtypeElement;
	Map<String, AnnotationConfig> subtypeConfigs = new HashMap<>();
	Map<String, AnnotationConfig> subtypeLookup = new HashMap<>();

	String skipElement = null;
	int skipdepth = 0;

	String subtype;
	boolean unknownSubtype = false;
	private Set<String> unknownSubtypes = new HashSet<>();
	private List<CachedElement> cache = new ArrayList<>();

	public AnnotationContentHandler(Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this("", objectClass);
	}

	public AnnotationContentHandler(String name, Class<?> objectClass)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super(name);
		try {
			this.object = objectClass.newInstance();
		} catch (Exception e) {
			LOG.error("handler for " + name + ": " + objectClass + " could not be instanciated");
			return;
		}

		baseConfig = config = new AnnotationConfig(objectClass, this::getObject);

		analyzeSubtypes(objectClass);
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

		for (Class<?> subClass : ReflectionUtils.getSubTypesOf(objectClass)) {
			XmlSubtype sub = subClass.getAnnotation(XmlSubtype.class);
			if (sub == null)
				continue;
			AnnotationConfig subConfig = new AnnotationConfig(subClass, this::getObject);
			subtypeLookup.put(sub.value(), subConfig);
			for (String s : sub.value().split(","))
				subtypeConfigs.put(s, subConfig);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		super.startElement(uri, localName, qName, atts);

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
					if (!unknownSubtype && !config.getUnknownElements().contains(localName)) {
						LOG.warn(name + " - unknown element: " + (subtypes ? subtype + " - " : "") + localName + " = "
								+ value.trim());
						config.getUnknownElements().add(localName);
					}
					skipElement = null;
				}
			} else
				skipdepth--;
			return;
		}

		if (subtypes && subtype == null && localName.equals(subtypeElement)) {
			AnnotationConfig subConfig = subtypeConfigs.get(value);
			if (subConfig != null) {
				subtype = value;
				config = subConfig;
				try {
					object = subConfig.getObjectClass().newInstance();
					applyCache(object);
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("error initializing object", e);
				}

			} else {
				if (!unknownSubtypes.contains(value))
					LOG.warn(name + " - UNKNOWN SUBTYPE: " + value);
				unknownSubtype = true;
				unknownSubtypes.add(value);
			}
		}

		StringConsumer consumer = config.getValues().get(localName);
		if (consumer != null) {
			try {
				consumer.accept(value);
			} catch (Exception e) {
				LOG.error("error accepting: " + localName + " = " + value + " " + getName(), e);
			}
			if (subtypes && subtype == null) {
				cache.add(new CachedElement(localName, value));
			}
		} else {
			super.endElement(uri, localName, qName);
		}

	}

	private void applyCache(Object object) {
		for (CachedElement el : cache) {
			StringConsumer consumer = config.getValues().get(el.getElement());
			if (consumer != null) {
				try {
					consumer.accept(el.getValue());
				} catch (Exception e) {
					LOG.error("error accepting: " + el.getElement() + " = " + value + " " + getName(), e);
				}
			}
		}
	}

	@Override
	protected void consume() {
		try {
			if (consumer != null) {
				if (subtypes && subtype == null) {
					applyCache(object);
				}
				consumer.accept(object);
			}
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
			LOG.error("error initializing object", e);
		}
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		if (subtypes) {
			XmlSubtype sub = object.getClass().getAnnotation(XmlSubtype.class);
			if (sub != null) {
				subtype = sub.value();
				config = subtypeLookup.get(subtype);
			} else {
				subtype = "UNKNOWN";
			}
		}
		this.object = object;
	}

	@Override
	public Collection<StackContentHandler> getSubHandlers() {
		return config.getHandlers().values();
	}

	public void printMappedValues() {
		config.printMappedValues(name, null);
		subtypeConfigs.entrySet().forEach(sc -> sc.getValue().printMappedValues(name, sc.getKey()));
		super.printMappedValues();
	}

}
