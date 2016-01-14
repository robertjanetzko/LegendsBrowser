package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.InspiredEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("written content composed")
public class WrittenContentComposedEvent extends InspiredEvent implements LocalEvent {
	@Xml("wc_id")
	private int wcId = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	public int getWcId() {
		return wcId;
	}

	public void setWcId(int wcId) {
		this.wcId = wcId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String loc = location.getLink("in");
		return World.getWrittenContent(wcId).getLink() + " was authored by " + hf + loc + getReasonString()
				+ getCircumstanceString();
	}

}
