package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class HfWoundedEvent extends Event implements LocalEvent, HfRelatedEvent {
	private int woundeeHfId = -1;
	private int wounderHfIf = -1;

	private int woundeeRace = -1;
	private int woundeeCaste = -1;
	private int bodyPart = -1;
	private int injuryType = -1;
	private int partLost = -1;

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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "woundee_hfid":
		case "woundee":
			setWoundeeHfId(Integer.parseInt(value));
			break;
		case "wounder_hfid":
		case "wounder":
			setWounderHfIf(Integer.parseInt(value));
			break;
		case "woundee_race":
			setWoundeeRace(Integer.parseInt(value));
			break;
		case "woundee_caste":
			setWoundeeCaste(Integer.parseInt(value));
			break;
		case "body_part":
			setBodyPart(Integer.parseInt(value));
			break;
		case "injury_type":
			setInjuryType(Integer.parseInt(value));
			break;
		case "part_lost":
			setPartLost(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
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
		return woundee + " was wounded by " + wounder + loc;
	}

}
