package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class CreatureDevouredEvent extends Event implements LocalEvent, HfRelatedEvent {
	private EventLocation location = new EventLocation();

	private int calcDevouredHfId = -1;
	private int calcSlayerHfId = -1;

	public int getCalcDevouredHfId() {
		return calcDevouredHfId;
	}

	public void setCalcDevouredHfId(int calcDevouredHfId) {
		this.calcDevouredHfId = calcDevouredHfId;
	}

	public int getCalcSlayerHfId() {
		return calcSlayerHfId;
	}

	public void setCalcSlayerHfId(int calcSlayerHfId) {
		this.calcSlayerHfId = calcSlayerHfId;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcDevouredHfId == hfId || calcSlayerHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String devoured = "UNKNOWN HISTORICAL FIGURE";
		if (calcDevouredHfId != -1)
			devoured = World.getHistoricalFigure(calcDevouredHfId).getLink();
		String slayer = "UNKNOWN HISTORICAL FIGURE";
		if (calcSlayerHfId != -1)
			slayer = World.getHistoricalFigure(calcSlayerHfId).getLink();
		String loc = location.getLink("in");
		return slayer + " devoured " + devoured + loc;
	}

}
