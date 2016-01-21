package legends.xml.handlers;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;

import com.google.common.base.Predicate;

import legends.model.World;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlAutoIncrement;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlConverter;
import legends.xml.annotation.XmlIgnorePlus;
import legends.xml.converter.ValueConverter;

public class AnnotationConfig {
	private Class<?> objectClass;

	private Map<String, StackContentHandler> handlers = new HashMap<>();
	private Map<String, StringConsumer> values = new HashMap<>();
	private Set<String> unknownElements = new HashSet<>();
	private Map<Annotation, Object> componentObjects = new HashMap<>();

	public AnnotationConfig(final Class<?> objectClass, final ObjectAccessor object) {
		this.objectClass = objectClass;
		try {
			analyzeClass(objectClass, object, "");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public Map<String, StackContentHandler> getHandlers() {
		return handlers;
	}

	public Map<String, StringConsumer> getValues() {
		return values;
	}

	public Set<String> getUnknownElements() {
		return unknownElements;
	}

	@SuppressWarnings("unchecked")
	private void analyzeClass(final Class<?> analyzeClass, final ObjectAccessor object, String prefix)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Predicate<AnnotatedElement> xmlAnnotation = ReflectionUtils.withAnnotation(Xml.class);
		for (final Field field : ReflectionUtils.getAllFields(analyzeClass, xmlAnnotation)) {
			Xml xml = field.getAnnotation(Xml.class);
			field.setAccessible(true);

			for (String element : xml.value().split(",")) {
				if (field.getAnnotation(XmlIgnorePlus.class) != null && World.isPlusMode()) {
					unknownElements.add(element);
					continue;
				}

				analyzeField(field, xml, prefix + element, object);
			}
		}

		for (final Method method : ReflectionUtils.getAllMethods(analyzeClass, xmlAnnotation)) {
			Xml xml = method.getAnnotation(Xml.class);

			for (String element : xml.value().split(",")) {
				if (method.getAnnotation(XmlIgnorePlus.class) != null && World.isPlusMode()) {
					unknownElements.add(element);
					continue;
				}

				analyzeMethod(method, xml, prefix + element, object);
			}
		}

		for (final Field field : ReflectionUtils.getAllFields(analyzeClass,
				ReflectionUtils.withAnnotation(XmlComponent.class))) {
			XmlComponent component = field.getAnnotation(XmlComponent.class);
			field.setAccessible(true);
			if (!component.multiple()) {
				analyzeClass(field.getType(), () -> field.get(object.get()), component.prefix());
			} else {
				componentObjects.put(component, component.elementClass().newInstance());
				analyzeClass(component.elementClass(), () -> componentObjects.get(component), component.prefix());
				final String consumeOn = component.prefix() + component.consumeOn();
				final StringConsumer componentConsmer = values.get(consumeOn);
				if (componentConsmer == null) {
					System.err.println("no value handler for consumeOn" + consumeOn);
				} else {
					values.put(consumeOn, v -> {
						componentConsmer.accept(v);
						((List<Object>) field.get(object.get())).add(componentObjects.get(component));
						try {
							componentObjects.put(component, component.elementClass().newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void analyzeField(final Field field, final Xml xml, final String element, final ObjectAccessor object)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		XmlConverter xmlConverter = field.getAnnotation(XmlConverter.class);
		if (xmlConverter != null) {
			ValueConverter converter = xmlConverter.value().newInstance();
			values.put(element, v -> field.set(object.get(), converter.convert(v)));

		} else {
			Class<?> type = field.getType();

			if ((type == List.class || type == Map.class) && !xml.multiple()) {
				AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.element(),
						xml.elementClass());
				if (type == List.class) {
					if (xml.elementClass().getAnnotation(XmlAutoIncrement.class) == null)
						elementHandler.setConsumer(v -> ((List<Object>) field.get(object.get())).add(v));
					else
						elementHandler.setConsumer(v -> {
							final List<Object> list = ((List<Object>) field.get(object.get()));
							((AbstractObject) v).setId(list.size());
							list.add(v);
						});

				} else {
					elementHandler.setConsumer(
							v -> ((Map<Integer, Object>) field.get(object.get())).put(((AbstractObject) v).getId(), v));
					StringConsumer idConsumer = elementHandler.config.values.get("id");
					elementHandler.config.values.put("id", new StringConsumer() {
						@Override
						public void accept(String v)
								throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {
							Map<Integer, Object> map = ((Map<Integer, Object>) field.get(object.get()));
							Object existingObject = map.get(Integer.parseInt(v));
							if (existingObject == null) {
								idConsumer.accept(v);
							} else {
								elementHandler.setObject(existingObject);
							}
						}
					});
				}

				ListContentHandler listHandler = new ListContentHandler(element, elementHandler);
				handlers.put(element, listHandler);

			} else if (type == int.class) {
				values.put(element, v -> field.setInt(object.get(), Integer.parseInt(v)));

			} else if (type == String.class) {
				values.put(element, v -> field.set(object.get(), v));

			} else if (type == boolean.class) {
				values.put(element, v -> field.setBoolean(object.get(), true));

			} else if (!xml.multiple()) {
				AnnotationContentHandler elementHandler = new AnnotationContentHandler(element, type);
				elementHandler.setConsumer(obj -> field.set(object.get(), obj));
				handlers.put(element, elementHandler);

			} else if (type == List.class) {
				if (xml.elementClass() == int.class || xml.elementClass() == Integer.class) {
					values.put(element, v -> ((List<Object>) field.get(object.get())).add(Integer.parseInt(v)));

				} else if (xml.elementClass() == String.class) {
					values.put(element, v -> ((List<Object>) field.get(object.get())).add(v));

				} else {
					AnnotationContentHandler elementHandler = new AnnotationContentHandler(element, xml.elementClass());
					elementHandler.setConsumer(obj -> ((List<Object>) field.get(object.get())).add(obj));
					handlers.put(element, elementHandler);

				}
			} else if (type == Map.class) {
				AnnotationContentHandler elementHandler = new AnnotationContentHandler(element, xml.elementClass());
				elementHandler.setConsumer(obj -> ((Map<Integer, Object>) field.get(object.get()))
						.put(((AbstractObject) obj).getId(), obj));
				handlers.put(element, elementHandler);
			} else {
				System.out.println("unknown type for field " + field);
			}
		}
	}

	private void analyzeMethod(final Method method, final Xml xml, final String element, final ObjectAccessor object)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		XmlConverter xmlConverter = method.getAnnotation(XmlConverter.class);
		if (xmlConverter != null) {
			ValueConverter converter = xmlConverter.value().newInstance();
			values.put(element, v -> method.invoke(object.get(), converter.convert(v)));

		} else {
			Class<?> type = method.getParameterTypes()[0];
			if (type == List.class) {
				AnnotationContentHandler elementHandler = new AnnotationContentHandler(xml.element(),
						xml.elementClass());
				ListContentHandler listHandler = new ListContentHandler(element, elementHandler);
				listHandler.setConsumer(list -> method.invoke(object.get(), list));
				handlers.put(element, listHandler);

			} else if (type == int.class) {
				values.put(element, v -> method.invoke(object.get(), Integer.parseInt(v)));

			} else if (type == String.class) {
				values.put(element, v -> method.invoke(object.get(), v));

			} else {
				AnnotationContentHandler elementHandler = new AnnotationContentHandler(element, type);
				elementHandler.setConsumer(obj -> method.invoke(object.get(), obj));
				handlers.put(element, elementHandler);

			}
		}
	}

}
