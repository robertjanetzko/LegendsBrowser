package legends.model.events;

import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.LocalEvent;

public class BodyAbusedEvent extends Event implements LocalEvent {
	private EventLocation location = new EventLocation("");

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
		return "the bodys of UNKNOWN were impaled on UNKNOWN by UNKNOWN" + location.getLink("in");
	}

}
