package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf wounded")
public class HfWoundedEvent extends Event implements LocalEvent, HfRelatedEvent {
	@Xml("woundee_hfid,woundee")
	private int woundeeHfId = -1;
	@Xml("wounder_hfid,wounder")
	private int wounderHfIf = -1;

	@Xml("woundee_race")
	private int woundeeRace = -1;
	@Xml("woundee_caste")
	private int woundeeCaste = -1;
	@Xml("body_part")
	private int bodyPart = -1;
	@Xml("injury_type")
	private int injuryType = -1;
	@Xml("part_lost")
	private int partLost = -1;
	@Xml("was_torture")
	private boolean wasTorture;

	@XmlComponent
	private EventLocation location = new EventLocation();

	public int getWoundeeHfId() {
		return woundeeHfId;
	}

	public void setWoundeeHfId(int woundeeHfId) {
		this.woundeeHfId = woundeeHfId;
	}

	public int getWounderHfIf() {
		return wounderHfIf;
	}

	public void setWounderHfIf(int wounderHfIf) {
		this.wounderHfIf = wounderHfIf;
	}

	public int getWoundeeRace() {
		return woundeeRace;
	}

	public void setWoundeeRace(int woundeeRace) {
		this.woundeeRace = woundeeRace;
	}

	public int getWoundeeCaste() {
		return woundeeCaste;
	}

	public void setWoundeeCaste(int woundeeCaste) {
		this.woundeeCaste = woundeeCaste;
	}

	public int getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(int bodyPart) {
		this.bodyPart = bodyPart;
	}

	public int getInjuryType() {
		return injuryType;
	}

	public void setInjuryType(int injuryType) {
		this.injuryType = injuryType;
	}

	public int getPartLost() {
		return partLost;
	}

	public void setPartLost(int partLost) {
		this.partLost = partLost;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return woundeeHfId == hfId || wounderHfIf == hfId;
	}

	@Override
	public String getShortDescription() {
		String woundee = World.getHistoricalFigure(woundeeHfId).getLink();
		String wounder = World.getHistoricalFigure(wounderHfIf).getLink();
		String loc = location.getLink("in");
		return woundee + " was wounded by " + wounder + loc + (wasTorture ? " as a means of torture" : "");
	}

}
