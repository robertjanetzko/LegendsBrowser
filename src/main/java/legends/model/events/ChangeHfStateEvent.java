package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlIgnorePlus;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("change hf state")
public class ChangeHfStateEvent extends HfEvent implements LocalEvent {
	@Xml(value = "state", track = true)
	@XmlIgnorePlus
	private String state;
	@Xml("substate")
	private int subState = -1;
	@Xml(value = "reason", track = true)
	private String reason;
	@Xml(value = "mood", track = true)
	private String mood;

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

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		if (state != null)
			switch (state) {
			case "settled":
				if (reason != null)
					switch (reason) {
					case "be with master":
						return hf + " moved to study " + location.getLink("in") + " in order to be with the master";
					case "scholarship":
						return hf + " moved to study " + location.getLink("in") + " in order to pursue scholarship";
					case "exiled after conviction":
						return hf + " departed " + location.getLink("to") + " after being exiled following a criminal conviction";
					}
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
				if (reason != null)
					switch (reason) {
					case "gather information":
						return hf + " visited " + location.getLink("in") + " to gather information";
					case "on a pilgrimage":
						return hf + " visited " + location.getLink("in") + " on a pilgrimage";
					}
				return hf + " visited " + location.getLink("");
			}
		if (mood != null)
			switch (mood) {
			case "possessed":
				return hf + " was possessed " + location.getLink("in");
			case "fey":
				return hf + " was taken by a fey mood " + location.getLink("in");
			case "fell":
				return hf + " was taken by a fell mood " + location.getLink("in");
			case "catatonic":
				return hf + " stopped responding to the outside world " + location.getLink("in");
			case "secretive":
				return hf + " withdrew from society " + location.getLink("in");
			case "macabre":
				return hf + " began to skulk and brood " + location.getLink("in");
			case "berserk":
				if ("failed mood".equals(reason))
					return hf + " went berserk " + location.getLink("in") + " after failing to create an artifact";
				return hf + " went berserk " + location.getLink("in");
			case "insane":
				if ("failed mood".equals(reason))
					return hf + " became crazed " + location.getLink("in") + " after failing to create an artifact";
				return hf + " became crazed " + location.getLink("in");
			case "melancholy":
				if ("failed mood".equals(reason))
					return hf + " was striken by melancholy " + location.getLink("in") + " after failing to create an artifact";
				return hf + " was striken by melancholy " + location.getLink("in");
			}
		return hf + " changed state: " + state;
	}

	public static void printUnknownStates() {
		states.remove("settled");
		states.remove("wandering");
		states.remove("refugee");
		states.remove("visiting");

		if (states.size() > 0)
			LOG.debug("unknown change hf states: " + states);
		if (substates.size() > 0)
			LOG.debug("unknown change hf substates: " + substates);
	}

}
