package legends.xml.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ListMethodConsumer {
	public void accept(List<?> list) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException;
}
