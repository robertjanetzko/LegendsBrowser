package legends.model.collections.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.World;
import legends.model.basic.AbstractObject;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtypes;

@XmlSubtypes("type")
public class EventCollection extends AbstractObject {
	@Xml("start_year")
	protected int startYear;
	@Xml("start_seconds72")
	protected int startSeconds;
	@Xml("end_year")
	protected int endYear;
	@Xml("end_seconds72")
	protected int endSeconds;
	@Xml("type")
	protected String type;
	@Xml(value = "event", elementClass = Integer.class, multiple = true)
	protected List<Integer> events = new ArrayList<>();
	@Xml(value = "eventcol", elementClass = Integer.class, multiple = true)
	protected List<Integer> eventCols = new ArrayList<>();
	@Xml("parent_eventcol")
	protected int parentEventCol = -1;
	@Xml("ordinal")
	protected int ordinal = -1;

	private EventCollection collection;

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getStartSeconds() {
		return startSeconds;
	}

	public void setStartSeconds(int startSeconds) {
		this.startSeconds = startSeconds;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getEndSeconds() {
		return endSeconds;
	}

	public void setEndSeconds(int endSeconds) {
		this.endSeconds = endSeconds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getEvents() {
		return events;
	}

	public List<Integer> getEventCols() {
		return eventCols;
	}

	public int getParentEventCol() {
		return parentEventCol;
	}

	public void setParentEventCol(int parentEventCol) {
		this.parentEventCol = parentEventCol;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public EventCollection getCollection() {
		return collection;
	}

	public void setCollection(EventCollection collection) {
		this.collection = collection;
	}

	public String getDescription() {
		return "";
	}
	
	public String getShortDescription() {
		return "[" + id + "] " + type;
	}

	public String getSentence() {
		return EventHelper.capitalize(getShortDescription());
	}

	public void process() {
		getHistoricalEvents().stream().forEach(e -> e.setCollection(this));
		getHistoricalEventCollections().stream().forEach(e -> e.setCollection(this));
	}

	public List<Event> getHistoricalEvents() {
		return events.stream().map(World::getHistoricalEvent).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public List<Event> getAllHistoricalEvents() {
		List<Event> list = events.stream().map(World::getHistoricalEvent).filter(Objects::nonNull).collect(Collectors.toList());
		getHistoricalEventCollections().stream().map(EventCollection::getAllHistoricalEvents).forEach(list::addAll);
		Collections.sort(list, EventHelper.getComparator());
		return list;
	}

	public List<EventCollection> getHistoricalEventCollections() {
		return eventCols.stream().map(World::getHistoricalEventCollection).collect(Collectors.toList());
	}

	public String getOrdinalString() {
		switch (ordinal) {
		case 1:
			return "";
		case 2:
			return "Second ";
		case 3:
			return "Third ";
		case 11:
			return ordinal + "th ";

		default:
			switch (ordinal % 10) {
			case 1:
				return ordinal + "st ";
			case 2:
				return ordinal + "nd ";
			case 3:
				return ordinal + "rd ";
			default:
				return ordinal + "th ";
			}
		}
	}

	public String getUrl() {
		return Application.getSubUri() + "/collection/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getUrl() + "\">" + id + " " + getOrdinalString() + type + "</a>";
	}

	public String getDate() {
		if (endYear == -1)
			return "since " + startYear;
		else if (startYear == endYear)
			return "in " + startYear;
		else
			return "from " + startYear + " till " + endYear;
	}
}
