package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("performance,ceremony,procession")
public class PerformanceEvent extends Event implements LocalEvent, EntityRelatedEvent {
	@Xml("civ_id")
	private int civId = -1;
	@Xml("occasion_id")
	private int occasionId = -1;
	@Xml("schedule_id")
	private int scheduleId = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();

		switch (type) {
		case "performance":
			return civ + " held a performance of UNKNOWN in " + location.getLink("in") + " as part of the " + occasionId
					+ "-" + scheduleId;
		case "ceremony":
			return civ + " held a ceremony in " + location.getLink("in") + " as part of the " + occasionId + "-"
					+ scheduleId;
		case "procession":
			return civ + " held a procession in " + location.getLink("in") + " as part of the " + occasionId + "-"
					+ scheduleId;
		default:
			return civ + " held an unknown event in " + location.getLink("in") + " as part of the " + occasionId + "-"
					+ scheduleId;
		}
	}

}
