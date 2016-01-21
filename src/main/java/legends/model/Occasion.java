package legends.model;

import java.util.LinkedHashMap;
import java.util.Map;

import legends.model.basic.NamedObject;
import legends.xml.annotation.Xml;

public class Occasion extends NamedObject {
	@Xml("event")
	private int event = -1;
	@Xml(value = "schedule", elementClass = Schedule.class, multiple = true)
	Map<Integer, Schedule> schedule = new LinkedHashMap<>();

	private static Schedule UNKNOWN_SCHEDULE = new Schedule();
	
	public Occasion() {
		this.name = "UNKNOWN FESTIVAL";
	}
	
	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public Schedule getSchedule(int id) {
		return schedule.getOrDefault(id, UNKNOWN_SCHEDULE);
	}
	
	public Map<Integer, Schedule> getSchedule() {
		return schedule;
	}

}
