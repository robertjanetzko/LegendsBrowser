package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class ChangeHfBodyStateEvent extends HfEvent implements LocalEvent {
	private String bodyState;
	private int buildingId = -1;

	private EventLocation location = new EventLocation("");

	private static Set<String> bodyStates = new HashSet<>();

	public String getBodyState() {
		return bodyState;
	}

	public void setBodyState(String bodyState) {
		this.bodyState = bodyState;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "body_state":
			bodyStates.add(value);
			setBodyState(value);
			break;
		case "building_id":
			setBuildingId(Integer.parseInt(value));
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
		switch (bodyState) {
		case "entombed at site":
			return hf + " was entombed" + loc+" within "+buildingId;
		default:
			return hf + " " + bodyState + loc;
		}
	}

	public static void printUnknownBodyStates() {
		bodyStates.remove("entombed at site");
		
		if (bodyStates.size() > 0)
			System.out.println("unknown hf body states: " + bodyStates);
	}

}
