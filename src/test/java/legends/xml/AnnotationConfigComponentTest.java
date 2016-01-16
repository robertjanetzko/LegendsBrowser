package legends.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import legends.xml.classes.SimpleClassWithComponent;
import legends.xml.classes.SimpleClassWithMultipleComponent;
import legends.xml.classes.SimpleClassWithMultiplePrefixedComponent;
import legends.xml.handlers.AnnotationConfig;

public class AnnotationConfigComponentTest {

	@Test
	public void testClassWithComponent()
			throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {

		SimpleClassWithComponent instance = new SimpleClassWithComponent();

		AnnotationConfig config = new AnnotationConfig(instance.getClass(), () -> instance);
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().size() == 2);
		assertTrue(config.getValues().containsKey("value1"));
		assertTrue(config.getValues().containsKey("value2"));

		assertEquals(instance.getComponent().getValue1(), -1);
		assertEquals(instance.getComponent().getValue2(), null);
		config.getValues().get("value1").accept("123");
		config.getValues().get("value2").accept("Test");
		assertEquals(instance.getComponent().getValue1(), 123);
		assertEquals(instance.getComponent().getValue2(), "Test");
	}

	@Test
	public void testClassWithMultipleComponent()
			throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {

		SimpleClassWithMultipleComponent instance = new SimpleClassWithMultipleComponent();

		AnnotationConfig config = new AnnotationConfig(instance.getClass(), () -> instance);
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().size() == 2);
		assertTrue(config.getValues().containsKey("value1"));
		assertTrue(config.getValues().containsKey("value2"));

		assertTrue(instance.getComponents().isEmpty());
		config.getValues().get("value1").accept("123");
		config.getValues().get("value2").accept("Test");
		assertEquals(instance.getComponents().size(), 1);
		assertEquals(instance.getComponents().get(0).getValue1(), 123);
		assertEquals(instance.getComponents().get(0).getValue2(), "Test");
		config.getValues().get("value1").accept("456");
		config.getValues().get("value2").accept("Test2");
		assertEquals(instance.getComponents().get(0).getValue1(), 123);
		assertEquals(instance.getComponents().get(0).getValue2(), "Test");
		assertEquals(instance.getComponents().get(1).getValue1(), 456);
		assertEquals(instance.getComponents().get(1).getValue2(), "Test2");
	}

	@Test
	public void testClassWithMultiplePrefixedComponent()
			throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {

		SimpleClassWithMultiplePrefixedComponent instance = new SimpleClassWithMultiplePrefixedComponent();

		AnnotationConfig config = new AnnotationConfig(instance.getClass(), () -> instance);
		assertTrue(config.getHandlers().isEmpty());
		assertTrue(config.getValues().size() == 4);
		assertTrue(config.getValues().containsKey("1_value1"));
		assertTrue(config.getValues().containsKey("1_value2"));
		assertTrue(config.getValues().containsKey("2_value1"));
		assertTrue(config.getValues().containsKey("2_value2"));

		assertTrue(instance.getComponents1().isEmpty());
		assertTrue(instance.getComponents2().isEmpty());
		config.getValues().get("1_value1").accept("123");
		config.getValues().get("1_value2").accept("Test");
		assertEquals(instance.getComponents1().size(), 1);
		assertEquals(instance.getComponents2().size(), 0);
		assertEquals(instance.getComponents1().get(0).getValue1(), 123);
		assertEquals(instance.getComponents1().get(0).getValue2(), "Test");
		
		config.getValues().get("2_value1").accept("456");
		config.getValues().get("2_value2").accept("Test2");
		assertEquals(instance.getComponents1().size(), 1);
		assertEquals(instance.getComponents2().size(), 1);
		assertEquals(instance.getComponents1().get(0).getValue1(), 123);
		assertEquals(instance.getComponents1().get(0).getValue2(), "Test");
		assertEquals(instance.getComponents2().get(0).getValue1(), 456);
		assertEquals(instance.getComponents2().get(0).getValue2(), "Test2");
		
		config.getValues().get("1_value1").accept("789");
		config.getValues().get("1_value2").accept("Test3");
		assertEquals(instance.getComponents1().size(), 2);
		assertEquals(instance.getComponents2().size(), 1);
		assertEquals(instance.getComponents1().get(0).getValue1(), 123);
		assertEquals(instance.getComponents1().get(0).getValue2(), "Test");
		assertEquals(instance.getComponents2().get(0).getValue1(), 456);
		assertEquals(instance.getComponents2().get(0).getValue2(), "Test2");
		assertEquals(instance.getComponents1().get(1).getValue1(), 789);
		assertEquals(instance.getComponents1().get(1).getValue2(), "Test3");
		
		config.getValues().get("2_value1").accept("1234");
		config.getValues().get("2_value2").accept("Test4");
		assertEquals(instance.getComponents1().size(), 2);
		assertEquals(instance.getComponents2().size(), 2);
		assertEquals(instance.getComponents1().get(0).getValue1(), 123);
		assertEquals(instance.getComponents1().get(0).getValue2(), "Test");
		assertEquals(instance.getComponents2().get(0).getValue1(), 456);
		assertEquals(instance.getComponents2().get(0).getValue2(), "Test2");
		assertEquals(instance.getComponents1().get(1).getValue1(), 789);
		assertEquals(instance.getComponents1().get(1).getValue2(), "Test3");
		assertEquals(instance.getComponents2().get(1).getValue1(), 1234);
		assertEquals(instance.getComponents2().get(1).getValue2(), "Test4");
	}

}
