package legends.model.events;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.Occasion;
import legends.model.Schedule;
import legends.model.ScheduleFeature;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("performance,ceremony,procession")
public class OccasionEvent extends Event implements LocalEvent, EntityRelatedEvent {
	@Xml("civ_id")
	protected int civId = -1;
	@Xml("occasion_id")
	protected int occasionId = -1;
	@Xml("schedule_id")
	protected int scheduleId = -1;
	@XmlComponent
	protected EventLocation location = new EventLocation();

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
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		Schedule sch = occ.getSchedule(scheduleId);

		String features = "";
		if (!sch.getFeatures().isEmpty()) {
			features = ". The event featured "
					+ sch.getFeatures().stream().map(ScheduleFeature::getText).collect(EventHelper.stringList());
		}

		switch (type) {
		case "performance":
			String perf = "UNKNOWN ART FORM";
			switch (sch.getType()) {
			case "poetry recital":
				perf = "a recital of " + World.getPoeticForm(sch.getReference()).getLink();
				break;
			case "musical performance":
				perf = "a performance of " + World.getMusicalForm(sch.getReference()).getLink();
				break;
			case "dance performance":
				perf = "a performance of " + World.getDanceForm(sch.getReference()).getLink();
				break;
			case "storytelling":
				if (sch.getReference() != -1)
					perf = "the story of " + World.getHistoricalEvent(sch.getReference()).getShortDescription();
				else
					perf = "a story recital";
				break;
			case "":
				perf = " a performance";
				break;
			default:
				perf = sch.getType();
			}

			return civ.getLink() + " held " + perf + " " + location.getLink("in") + " as part of the " + occ.getName();
		case "ceremony":
			return civ.getLink() + " held a ceremony " + location.getLink("in") + " as part of the " + occ.getName()
					+ features;
		case "procession":
			String route = "";
			if (sch.getReference() != -1) {
				route = ". It started at " + World.getStructure(sch.getReference(), location.getSiteId()).getLink();
				if (sch.getReference2() != -1 && sch.getReference() != sch.getReference2())
					route += " and ended at " + World.getStructure(sch.getReference(), location.getSiteId()).getLink();
				else
					route += " and returned there after following its route";
			}
			return civ.getLink() + " held a procession " + location.getLink("in") + " as part of the " + occ.getName()
					+ route + features;
		default:
			return civ.getLink() + " held an unknown event " + location.getLink("in") + " as part of the "
					+ occ.getName();
		}
	}

}
