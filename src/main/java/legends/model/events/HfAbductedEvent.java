package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf abducted")
public class HfAbductedEvent extends Event implements LocalEvent, HfRelatedEvent {
	@Xml("target_hfid")
	private int targetHfId;
	@Xml("snatcher_hfid")
	private int snatcherHfId;
	@XmlComponent
	private EventLocation location = new EventLocation();

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public int getSnatcherHfId() {
		return snatcherHfId;
	}

	public void setSnatcherHfId(int snatcherHfId) {
		this.snatcherHfId = snatcherHfId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return targetHfId == hfId || snatcherHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String target = World.getHistoricalFigure(getTargetHfId()).getLink();
		String snatcher = World.getHistoricalFigure(getSnatcherHfId()).getLink();
		String loc = location.getLink("from");
		return target + " was abducted" + loc + " by " + snatcher;
	}

}
