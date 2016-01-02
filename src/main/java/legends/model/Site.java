package legends.model;

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

import legends.helper.EventHelper;
import legends.model.events.CreatedSiteEvent;
import legends.model.events.DestroyedSiteEvent;
import legends.model.events.HfSiteEvent;
import legends.model.events.basic.Event;

public class Site {
	private int id;
	private String name;
	private String type;
	private int x;
	private int y;

	private List<Structure> structures = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setStructures(List<Structure> structures) {
		this.structures = structures;
	}

	public List<Structure> getStructures() {
		return structures;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")" + " " + x + "," + y;
	}

	public String getLink() {
		return "<a href=\"/site/" + id + "\" class=\"site\">" + getName() + "</a>";
	}

	public String getFounded() {
		return "" + World.getHistoricalEvents().stream()
				.collect(new EventFilter<CreatedSiteEvent>(CreatedSiteEvent.class, e -> e.getSiteId() == id)).stream()
				.map(e -> {
					return e.getYear() + " by " + World.getEntity(e.getSiteCivId()).getLink();
				}).findFirst().orElse("");
	}

	public String getDestroyed() {
		return ""
				+ World.getHistoricalEvents().stream()
						.collect(
								new EventFilter<DestroyedSiteEvent>(DestroyedSiteEvent.class, e -> e.getSiteId() == id))
						.stream().map(e -> {
							return e.getYear() + " by " + World.getEntity(e.getAttackerCivId()).getLink();
						}).findFirst()
						.orElse(World.getHistoricalEvents().stream().collect(
								new EventFilter<HfSiteEvent>(HfSiteEvent.class, e -> e.getSiteId() == id))
								.stream().map(e -> {
									return e.getYear() + " by " + World.getHistoricalFigure(e.getAttackerHfId()).getLink();
								}).findFirst().orElse(""));
	}

	class EventFilter<T> implements Collector<Event, List<T>, List<T>> {

		Class<T> eventClass;
		Predicate<T> filter;

		public EventFilter(Class<T> eventClass, Predicate<T> filter) {
			this.eventClass = eventClass;
			this.filter = filter;
		}

		@Override
		public Supplier<List<T>> supplier() {
			return () -> new ArrayList<>();
		}

		@SuppressWarnings("unchecked")
		@Override
		public BiConsumer<List<T>, Event> accumulator() {
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
		public Function<List<T>, List<T>> finisher() {
			return e -> e;
		}

		@Override
		public Set<java.util.stream.Collector.Characteristics> characteristics() {
			return EnumSet.of(Characteristics.UNORDERED);
		}

	}
}
