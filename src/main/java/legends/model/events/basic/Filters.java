package legends.model.events.basic;

import java.util.function.Predicate;

import legends.model.collections.basic.EventCollection;

public class Filters {
	public static <T extends Event> EventFilter<T> filterEvent(Class<T> eventClass) {
		return new EventFilter<T>(eventClass, e -> true);
	}

	public static <T extends Event> EventFilter<T> filterEvent(Class<T> eventClass, Predicate<T> filter) {
		return new EventFilter<T>(eventClass, filter);
	}

	public static <T extends EventCollection> EventCollectionFilter<T> filterCollection(Class<T> eventClass) {
		return new EventCollectionFilter<T>(eventClass, e -> true);
	}

	public static <T extends EventCollection> EventCollectionFilter<T> filterCollection(Class<T> eventClass, Predicate<T> filter) {
		return new EventCollectionFilter<T>(eventClass, filter);
	}

}
