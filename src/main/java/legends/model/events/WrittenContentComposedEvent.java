package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.InspiredEvent;
import legends.model.events.basic.LocalEvent;

public class WrittenContentComposedEvent extends InspiredEvent implements LocalEvent {
	private int wcId = -1;
	
	private EventLocation location = new EventLocation();

	public int getWcId() {
		return wcId;
	}

	public void setWcId(int wcId) {
		this.wcId = wcId;
	}
	
	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "wc_id":
			setWcId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String loc = location.getLink("in");
		return wcId + " was authored by " + hf + loc + getReasonString() + getCircumstanceString();
	}

}
