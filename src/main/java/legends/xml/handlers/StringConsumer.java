package legends.xml.handlers;

import java.lang.reflect.InvocationTargetException;

public interface StringConsumer {
	public void accept(String string)
			throws InvocationTargetException, IllegalAccessException, IllegalArgumentException;
}
