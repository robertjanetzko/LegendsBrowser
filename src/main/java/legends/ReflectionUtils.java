package legends;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

public class ReflectionUtils {

	private static ScanResult reflections = new ClassGraph().enableAllInfo().scan();

	public ScanResult get() {
		return reflections;
	}

	public static ClassInfoList getClassesWithAnnotation(Class<?> annotation) {
		return reflections.getClassesWithAnnotation(annotation.getName());
	}

	public static List<Field> getAllFields(Class<?> analyzeClass, Class<?> annotation) {
		return reflections.getClassInfo(analyzeClass.getName()).getFieldInfo()
				.filter(fi -> fi.hasAnnotation(annotation.getName())).stream().map(fi -> fi.loadClassAndGetField())
				.collect(Collectors.toList());
	}

	public static List<Method> getAllMethods(Class<?> analyzeClass, Class<?> annotation) {
		return reflections.getClassInfo(analyzeClass.getName()).getMethodInfo()
				.filter(mi -> mi.hasAnnotation(annotation.getName())).stream().map(mi -> mi.loadClassAndGetMethod())
				.collect(Collectors.toList());
	}

	public static List<Class<?>> getSubTypesOf(Class<?> objectClass) {
		return reflections.getClassInfo(objectClass.getName()).getSubclasses().loadClasses();
	}

}
