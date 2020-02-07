package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("gamble")
public class GambleEvent extends Event implements HfRelatedEvent, LocalEvent {
	@Xml("gambler_hfid")
	private int gamblerHfId = -1;
	@Xml("old_account")
	private int oldAccount = -1;
	@Xml("new_account")
	private int newAccount = -1;

	@XmlComponent
	private EventLocation location = new EventLocation();

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return gamblerHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		int diff = newAccount - oldAccount;
		String outcome = "" + diff;
		if (diff == -5000)
			outcome = "lost a fortune";
		else if(diff == -1000)
			outcome = "did poorly";
		else if(diff == 1000)
			outcome = "did well";
		else if(diff == 5000)
			outcome = "made a fortune";
		String after = "";
		if (oldAccount >= 0 && newAccount < 0)
			after = " and went into debt";

		return String.format("%s %s gambling%s%s", World.getHistoricalFigure(gamblerHfId).getLink(), outcome,
				location.getLink("in"), after);
	}

}
