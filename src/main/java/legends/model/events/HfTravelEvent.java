package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf travel")
public class HfTravelEvent extends HfEvent implements LocalEvent {
	@Xml("return")
	private boolean returned;
	@XmlComponent
	private EventLocation location = new EventLocation("the depths of the world");

	@Override
	@Xml("group_hfid")
	public void setHfId(int hfId) {
		super.setHfId(hfId);
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("to");
		if (!returned)
			return hf + " made a journey" + loc;
		else
			return hf + " returned" + loc;
	}

}
