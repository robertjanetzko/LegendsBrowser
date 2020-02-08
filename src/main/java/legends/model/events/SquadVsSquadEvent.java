
package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("squad vs squad")
public class SquadVsSquadEvent extends Event implements HfRelatedEvent, LocalEvent {
	@Xml("a_hfid")
	private int aHfId = -1;
	@Xml("d_race")
	private int dRace = -1;
	@Xml("d_number")
	private int dNumber = -1;
	@Xml("d_slain")
	private int dSlain = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return aHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String slay = "";
		if (dSlain > 0)
			if (dNumber == dSlain)
				slay = ", slaying them";
			else
				slay = String.format(", slaying %d", dSlain);
		return String.format("%s clashed with %d %s%s%s", World.getHistoricalFigure(aHfId).getLink(), dNumber,
				"UNKNOWN RACE", location.getLink("in"), slay);
	}

}
