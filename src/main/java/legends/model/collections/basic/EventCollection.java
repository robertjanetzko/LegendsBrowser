package legends.model.collections.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.events.basic.Event;

public class EventCollection {
	protected int id;
	protected int startYear;
	protected int startSeconds;
	protected int endYear;
	protected int endSeconds;
	protected String type;
	protected List<Integer> events = new ArrayList<>();
	protected List<Integer> eventCols = new ArrayList<>();
	protected int parentEventCol = -1;
	protected int ordinal = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean setProperty(String property, String value) {
		switch (property) {
		case "id":
			setId(Integer.parseInt(value));
			break;
		case "start_year":
			setStartYear(Integer.parseInt(value));
			break;
		case "start_seconds72":
			setStartSeconds(Integer.parseInt(value));
			break;
		case "end_year":
			setEndYear(Integer.parseInt(value));
			break;
		case "end_seconds72":
			setEndSeconds(Integer.parseInt(value));
			break;
		case "type":
			setType(value);
			break;
		case "event":
			getEvents().add(Integer.parseInt(value));
			break;
		case "eventcol":
			getEventCols().add(Integer.parseInt(value));
			break;
		case "parent_eventcol":
			setParentEventCol(Integer.parseInt(value));
			break;
		case "ordinal":
			setOrdinal(Integer.parseInt(value));
			break;

		default:
			if (!property.equals("historical_event_collection"))
				System.err.println(id + " " + startYear + " unknown property: " + property + " = " + value);
			return false;
		}
		return true;
	}

	public void populateFrom(EventCollection col) {
		id = col.getId();
		startYear = col.getStartYear();
		startSeconds = col.getStartSeconds();
		endYear = col.getEndYear();
		endSeconds = col.getEndSeconds();
		type = col.getType();
		events = col.getEvents();
		eventCols = col.getEventCols();
		parentEventCol = col.getParentEventCol();
		ordinal = col.getOrdinal();
	}

	public String getShortDescription() {
		return "[" + id + "] " + type;
	}

	public String getSentence() {
		return EventHelper.capitalize(getShortDescription());
	}
	
	public void process() {
		
	}

	public List<Event> getHistoricalEvents() {
		return events.stream().map(World::getHistoricalEvent).collect(Collectors.toList());
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
			return ordinal+"th ";
		
		default:
			switch (ordinal%10) {
			case 1:
				return ordinal+"st ";
			case 2:
				return ordinal+"nd ";
			case 3:
				return ordinal+"rd ";
			default:
				return ordinal+"th ";
			}
		}
	}
}
