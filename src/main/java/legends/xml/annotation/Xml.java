package legends.xml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD })
public @interface Xml {
	public String value();

	public String element() default "";

	public Class<?> elementClass() default void.class;
	
	public boolean multiple() default false;

	public boolean subtypes() default false;
	
	public boolean track() default false;
}
