package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class ChangeHfJobEvent extends HfEvent implements LocalEvent {
	private EventLocation location = new EventLocation("the depths of the world");

	@Override
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
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("in");
		return hf + " became a UNKNOWN" + loc;
	}

}
