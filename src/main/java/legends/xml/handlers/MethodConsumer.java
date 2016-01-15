package legends.xml.handlers;

import java.lang.reflect.InvocationTargetException;

public interface MethodConsumer {
	public void accept(Object object) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException;
}
