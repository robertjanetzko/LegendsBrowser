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

import legends.model.HistoricalFigure;

public class HfListCollector implements Collector<HistoricalFigure, List<String>, String> {

	@Override
	public Supplier<List<String>> supplier() {
		return () -> new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BiConsumer<List<String>, HistoricalFigure> accumulator() {
		return (l, e) -> {
			l.add(e.getLink());
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
				return "EMPTY LIST";
			}
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.of(Characteristics.UNORDERED);
	}

}