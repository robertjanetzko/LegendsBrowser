package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf performed horrible experiments")
public class HfPerformedHorribleExperimentsEvent extends Event implements HfRelatedEvent, LocalEvent {
	@Xml("group_hfid")
	private int hfId = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.hfId == hfId;
	}

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public EventLocation getLocation() {
		return location;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s performed horrible experiments %s", World.getHistoricalFigure(hfId).getLink(),
				location.getLink("in"));
	}

}
