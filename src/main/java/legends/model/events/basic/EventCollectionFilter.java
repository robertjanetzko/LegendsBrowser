package legends.model.events.basic;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import legends.model.collections.basic.EventCollection;

public class EventCollectionFilter<T extends EventCollection>
		implements Collector<EventCollection, List<T>, Stream<T>> {

	Class<T> eventClass;
	Predicate<T> filter;

	public EventCollectionFilter(Class<T> eventClass, Predicate<T> filter) {
		this.eventClass = eventClass;
		this.filter = filter;
	}

	@Override
	public Supplier<List<T>> supplier() {
		return () -> new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BiConsumer<List<T>, EventCollection> accumulator() {
		return (l, e) -> {
			if (e.getClass().equals(eventClass) && filter.test((T) e))
				l.add((T) e);
		};
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (l1, l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Function<List<T>, Stream<T>> finisher() {
		return e -> e.stream();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

}