package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("change hf state")
public class ChangeHfStateEvent extends HfEvent implements LocalEvent {
	@Xml("state")
	private String state;
	@Xml("substate")
	private int subState = -1;

	@XmlComponent
	private EventLocation location = new EventLocation();

	private static Set<String> states = new HashSet<>();
	private static Set<String> substates = new HashSet<>();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getSubState() {
		return subState;
	}

	public void setSubState(int subState) {
		this.subState = subState;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		switch (state) {
		case "settled":
			switch (subState) {
			case 45:
				return hf + " fled " + location.getLink("to", "into");
			case 46:
			case 47:
				return hf + " moved to study " + location.getLink("in");
			default:
				return hf + " settled " + location.getLink("in");
			}
		case "wandering":
			if (location.isPresent())
				return hf + " began wandering" + location.getLink("");
			else
				return hf + " began wandering the wilds.";
		case "refugee":
			return hf + " fled " + location.getLink("to", "into");
		case "visiting":
			return hf + " visited " + location.getLink("");
		default:
			return hf + " changed state: " + state;
		}
	}

	public static void printUnknownStates() {
		states.remove("settled");
		states.remove("wandering");
		states.remove("refugee");
		states.remove("visiting");

		if (states.size() > 0)
			System.out.println("unknown change hf states: " + states);
		if (substates.size() > 0)
			System.out.println("unknown change hf substates: " + substates);
	}

}
