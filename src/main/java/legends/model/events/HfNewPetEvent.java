package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class HfNewPetEvent extends HfEvent implements LocalEvent {
	private EventLocation location = new EventLocation("the depths of the world");
	private String pets = "UNKNOWN PET";

	public String getPets() {
		return pets;
	}

	public void setPets(String pets) {
		this.pets = pets;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "group":
		case "group_hfid":
			setHfId(Integer.parseInt(value));
			break;
		case "pets":
			setPets(value);
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
		String loc = location.getLink("of");
		return hf + " tamed UNKNOWN" + loc;
	}

}
