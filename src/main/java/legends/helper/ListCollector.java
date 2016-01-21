package legends.helper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListCollector implements Collector<String, List<String>, String> {

	@Override
	public Supplier<List<String>> supplier() {
		return () -> new ArrayList<>();
	}

	@Override
	public BiConsumer<List<String>, String> accumulator() {
		return (l, e) -> {
			l.add(e);
		};
	}

	@Override
	public BinaryOperator<List<String>> combiner() {
		return (l1, l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Function<List<String>, String> finisher() {
		return e -> {
			if (e.size() > 1) {
				String last = e.remove(e.size() - 1);
				return e.stream().collect(Collectors.joining(", ")) + " and " + last;
			}
			if (e.size() == 1) {
				return e.get(0);
			} else {
				return "";
			}
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

}