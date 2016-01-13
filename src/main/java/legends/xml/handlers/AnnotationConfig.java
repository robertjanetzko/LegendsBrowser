package legends.xml.handlers;

import java.util.HashMap;
import java.util.Map;

public class AnnotationConfig {
	private Class<?> objectClass;
	Map<String, StackContentHandler> handlers = new HashMap<>();
	Map<String, StringConsumer> values = new HashMap<>();

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(Class<?> objectClass) {
		this.objectClass = objectClass;
	}

	public Map<String, StackContentHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, StackContentHandler> handlers) {
		this.handlers = handlers;
	}

	public Map<String, StringConsumer> getValues() {
		return values;
	}

	public void setValues(Map<String, StringConsumer> values) {
		this.values = values;
	}

}
