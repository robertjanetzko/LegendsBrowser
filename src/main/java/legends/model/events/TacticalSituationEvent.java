
package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("tactical situation")
public class TacticalSituationEvent extends Event implements HfRelatedEvent, LocalEvent {
	@Xml("a_tactician_hfid")
	private int aTacticianHfId = -1;
	@Xml("d_tactician_hfid")
	private int dTacticianHfId = -1;
	@Xml("a_tactics_roll")
	private int aTacticsRoll = -1;
	@Xml("d_tactics_roll")
	private int dTacticsRoll = -1;
	@Xml(value = "situation", track = true)
	private String situation;
	@Xml("start")
	private boolean start; /// TODO unused?
	@XmlComponent
	private EventLocation location = new EventLocation();

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return aTacticianHfId == hfId || dTacticianHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String a = World.getHistoricalFigure(aTacticianHfId).getLink();
		String d = World.getHistoricalFigure(dTacticianHfId).getLink();
		String l = location.getLink("in");
		String p = String.format("the defenders had a strong positional advandatge",
				"d strongly favored".equals(situation) ? "defenders" : "attackers");
		if (aTacticianHfId == -1 && dTacticianHfId == -1)
			return String.format("the forces shifted, and %s%s", p, l);
		else if (dTacticianHfId != -1) {
			if (aTacticsRoll > dTacticsRoll)
				if ("d strongly favored".equals(situation))
					return String.format("%s's tactical planning was superior to %s's, but %s%s", a, d, p, l);
				else
					return String.format("%s outmatched %s with a cunning plan, and %s%s", a, d, p, l);
			else if ("d strongly favored".equals(situation))
				return String.format("%s's tactical planning was superior to %s's, and %s%s", d, a, p, l);
			else
				return String.format("%s used UNKNOWN TACTICS against %s, and %s%s", a, d, p, l);/// TODO missing text

		} else {
			if (aTacticsRoll > dTacticsRoll)
				if ("d strongly favored".equals(situation))
					return String.format("%s used good tactics, but %s%s", a, p, l);
				else
					return String.format("%s used UNKNOWN TACTICS, and the %s%s", d, p, l);/// TODO missing text
			else if ("d strongly favored".equals(situation))
				return String.format("%s made a poor plan, and %s%s", a, p, l);
			else
				return String.format("%s used UNKOWN TACTICS, and %s%s", a, p, l); /// TODO missing text
		}
	}

}
