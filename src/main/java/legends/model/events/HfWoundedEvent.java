package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class HfWoundedEvent extends Event implements LocalEvent, HfRelatedEvent {
	private int woundeeHfId = -1;
	private int wounderHfIf = -1;

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

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "woundee_hfid":
			setWoundeeHfId(Integer.parseInt(value));
			break;
		case "wounder_hfid":
			setWounderHfIf(Integer.parseInt(value));
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
		return woundee+" was wounded by "+wounder+loc;
	}

}
