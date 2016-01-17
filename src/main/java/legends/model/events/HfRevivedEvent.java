package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf revived")
public class HfRevivedEvent extends HfEvent implements LocalEvent {
	@Xml("ghost")
	private String ghost;
	@XmlComponent
	private EventLocation location = new EventLocation();

	public String getGhost() {
		return ghost;
	}

	public void setGhost(String ghost) {
		this.ghost = ghost;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		return World.getHistoricalFigure(hfId).getLink()+" came back from the dead as a "+ghost+location.getLink("in");
	}

}
