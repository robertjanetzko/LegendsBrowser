package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class HfTravelEvent extends HfEvent implements LocalEvent {
	private boolean returned;

	private EventLocation location = new EventLocation("the depths of the world");

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "group_hfid":
			setHfId(Integer.parseInt(value));
			break;
		case "return":
			setReturned(true);
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
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("to");
		if(!returned)
			return hf+" made a journey"+loc;
		else
			return hf+" returned"+loc;
	}

}
