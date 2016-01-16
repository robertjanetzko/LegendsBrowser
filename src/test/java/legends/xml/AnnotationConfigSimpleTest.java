package legends.xml;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import legends.xml.annotation.Xml;
import legends.xml.handlers.AnnotationConfig;

public class AnnotationConfigSimpleTest {
	@Test
	public void testEmptyClass() {

		class EmptyClass {
		}

		AnnotationConfig config = new AnnotationConfig(EmptyClass.class, () -> new EmptyClass());
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().isEmpty());
	}

	@Test
	public void testClassWithInt() throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {

		class SimpleClass {
			@Xml("value")
			int value = -1;
		}
		SimpleClass instance = new SimpleClass();

		AnnotationConfig config = new AnnotationConfig(SimpleClass.class, () -> instance);
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().size() == 1);
		assertTrue(config.getValues().containsKey("value"));

		assertEquals(instance.value, -1);
		config.getValues().get("value").accept("123");
		assertEquals(instance.value, 123);
	}

	@Test
	public void testClassWithString()
			throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {

		class SimpleClass {
			@Xml("value")
			String value = "";
		}
		SimpleClass instance = new SimpleClass();

		AnnotationConfig config = new AnnotationConfig(SimpleClass.class, () -> instance);
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().size() == 1);
		assertTrue(config.getValues().containsKey("value"));

		assertEquals(instance.value, "");
		config.getValues().get("value").accept("123");
		assertEquals(instance.value, "123");
	}
}
