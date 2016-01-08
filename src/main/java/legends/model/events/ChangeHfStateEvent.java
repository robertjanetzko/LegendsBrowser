package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class ChangeHfStateEvent extends HfEvent implements LocalEvent {
	private String state;

	private EventLocation location = new EventLocation();

	private static Set<String> states = new HashSet<>();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "state":
			states.add(value);
			setState(value);
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
		switch (state) {
		case "settled":
			return World.getHistoricalFigure(getHfId()).getLink() + " settled " + location.getLink("in") + ".";
		case "wandering":
			if (location.isPresent())
				return World.getHistoricalFigure(getHfId()).getLink() + " began wandering" + location.getLink("");
			else
				return World.getHistoricalFigure(getHfId()).getLink() + " began wandering the wilds.";
		case "refugee":
			return World.getHistoricalFigure(getHfId()).getLink() + " fled " + location.getLink("to","into") + ".";
		case "visiting":
			return World.getHistoricalFigure(getHfId()).getLink() + " visited " + location.getLink("") + ".";
		default:
			return super.getShortDescription() + ": " + state;
		}
	}

	public static void printUnknownStates() {
		states.remove("settled");
		states.remove("wandering");
		states.remove("refugee");
		states.remove("visiting");

		if (states.size() > 0)
			System.out.println("unknown change hf states: " + states);
	}

}
