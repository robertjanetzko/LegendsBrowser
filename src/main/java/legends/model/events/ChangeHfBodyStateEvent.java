package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("change hf body state")
public class ChangeHfBodyStateEvent extends HfEvent implements LocalEvent, StructureRelatedEvent {
	@Xml("body_state")
	private String bodyState;
	@Xml("building_id,structure_id")
	private int buildingId = -1;
	@XmlComponent
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
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.buildingId == structureId && this.location.getSiteId() == siteId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String loc = location.getLink("in");
		switch (bodyState) {
		case "entombed at site":
			return hf + " was entombed" + loc + " within "
					+ World.getStructure(buildingId, location.getSiteId()).getLink();
		default:
			return hf + " " + bodyState + loc;
		}
	}

	public static void printUnknownBodyStates() {
		bodyStates.remove("entombed at site");

		if (bodyStates.size() > 0)
			LOG.debug("unknown hf body states: " + bodyStates);
	}

}
