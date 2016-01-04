package legends.model.events.basic;

import java.util.function.Predicate;

public class Filters {
	public static <T> EventFilter<T> get(Class<T> eventClass) {
		return new EventFilter<T>(eventClass, e -> true);
	}

	public static <T> EventFilter<T> get(Class<T> eventClass, Predicate<T> filter) {
		return new EventFilter<T>(eventClass, filter);
	}
}
