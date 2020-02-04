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
	@Xml("actor_hfid")
	int actorHfId = -1;
	@Xml("ghost")
	private String ghost;
	@XmlComponent
	private EventLocation location = new EventLocation();
	@Xml("disturbance")
	private boolean disturbance;
	
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
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || hfId == actorHfId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String loc = location.getLink("in");
		if (actorHfId != -1)
			if(disturbance)
				return String.format("%s was disturbed from eternal rest by %s as a %s%s", hf, World.getHistoricalFigure(actorHfId).getLink(), ghost, loc);
			else
			return String.format("%s was brought back from the dead by %s as a %s%s", hf, World.getHistoricalFigure(actorHfId).getLink(), ghost, loc);
		return String.format("%s came back from the dead as a %s%s", hf, ghost, loc);
	}

}
