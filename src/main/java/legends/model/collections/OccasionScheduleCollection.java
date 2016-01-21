package legends.model.collections;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.Occasion;
import legends.model.Schedule;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.OccasionEvent;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("performance,procession,ceremony,competition")
public class OccasionScheduleCollection extends EventCollection {
	@Xml("civ_id")
	protected int civId = -1;
	@Xml("occasion_id")
	protected int occasionId = -1;
	protected int scheduleId = -1;

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
	public void process() {
		super.process();
		for (Event event : getAllHistoricalEvents()) {
			if (event instanceof OccasionEvent) {
				OccasionEvent oe = (OccasionEvent) event;
				this.occasionId = oe.getOccasionId();
				this.scheduleId = oe.getScheduleId();
				if (civId == -1)
					this.civId = oe.getCivId();
			}
		}
	}

	@Override
	public String getLink() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		String name = type;
		if (occ.getId() != -1) {
			Schedule sch = occ.getSchedule(scheduleId);
			name = sch.getType();
		}
		if (name.equals("ceremony")) {
			if (scheduleId == 0)
				name = "opening ceremony";
			else if (getCollection() != null
					&& getCollection().getEventCols().indexOf(id) == getCollection().getEventCols().size() - 1)
				name = "closing ceremony";
			else
				name = "main ceremony";
		}
		return "the <a href=\"" + getUrl() + "\" class=\"collection occasion\">" + getOrdinalString()
				+ EventHelper.name(name) + "</a> of <a href=\"" + getCollection().getUrl()
				+ "\" class=\"collection occasion\">" + occ.getName() + "</a>";
	}

	@Override
	public String getShortDescription() {
		return getLink() + " occurred";
	}

	public String getName() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		String name = type;
		if (occ.getId() != -1) {
			Schedule sch = occ.getSchedule(scheduleId);
			name = sch.getType();
		}
		if (name.equals("ceremony")) {
			if (scheduleId == 0)
				name = "opening ceremony";
			else if (getCollection() != null
					&& getCollection().getEventCols().indexOf(id) == getCollection().getEventCols().size() - 1)
				name = "closing ceremony";
			else
				name = "main ceremony";
		}
		return "The " + getOrdinalString() + EventHelper.name(name) + " of " + occ.getName();
	}

}
