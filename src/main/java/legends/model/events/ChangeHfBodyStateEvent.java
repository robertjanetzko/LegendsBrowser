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
public class ChangeHfBodyStateEvent extends HfEvent implements LocalEvent {
	@Xml(value = "body_state", track = true)
	private String bodyState;
	@XmlComponent
	private EventLocation location = new EventLocation("");

	public String getBodyState() {
		return bodyState;
	}

	public void setBodyState(String bodyState) {
		this.bodyState = bodyState;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		switch (bodyState) {
		case "entombed at site":
			return hf + " was entombed" + location.getLink("within");
		default:
			return hf + " " + bodyState + location.getLink("in");
		}
	}

}
