package legends.model.collections;

import java.util.ArrayList;
import java.util.List;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.HfAbductedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.Filters;

public class AbductionCollection extends EventCollection {
	private int attackingEnId = -1;
	private int defendingEnId = -1;

	private List<Integer> abductedHfIds = new ArrayList<>();

	private EventLocation location = new EventLocation();

	public int getAttackingEnId() {
		return attackingEnId;
	}

	public void setAttackingEnId(int attackingEnId) {
		this.attackingEnId = attackingEnId;
	}

	public int getDefendingEnId() {
		return defendingEnId;
	}

	public void setDefendingEnId(int defendingEnId) {
		this.defendingEnId = defendingEnId;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacking_enid":
			setAttackingEnId(Integer.parseInt(value));
			break;
		case "defending_enid":
			setDefendingEnId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public void process() {
		List<Event> events = getHistoricalEvents();
		for (int i = 1; i < events.size(); i++) {
			if (events.get(i) instanceof AddHfEntityLinkEvent && events.get(i - 1) instanceof HfAbductedEvent) {
				AddHfEntityLinkEvent e1 = (AddHfEntityLinkEvent) events.get(i);
				HfAbductedEvent e2 = (HfAbductedEvent) events.get(i - 1);
				if (e1.getCalcHfId() == -1)
					e1.setCalcHfId(e2.getTargetHfId());
				if (e1.getCalcLinkType().equals(""))
					e1.setCalcLinkType("prisoner");
			}
		}
		events.stream().collect(Filters.get(HfAbductedEvent.class)).map(HfAbductedEvent::getTargetHfId)
				.forEach(abductedHfIds::add);
	}

	public String getLink() {
		String loc = location.getLink("at");
		switch (abductedHfIds.size()) {
		case 0:
			return "the <a href=\"/collection/" + getId() + "\" class=\"abduction\">" + getOrdinalString()
					+ "Attempted Abduction</a>" + loc;
		case 1:
			return "the <a href=\"/collection/" + getId() + "\" class=\"abduction\">" + getOrdinalString()
					+ "Abduction</a> of "+World.getHistoricalFigure(abductedHfIds.get(0)).getLink() + loc;
		default:
			return "the <a href=\"/collection/" + getId() + "\" class=\"abduction\">" + getOrdinalString()
					+ "Abduction</a>" + loc;
		}
	}

	@Override
	public String getShortDescription() {
		String loc = location.getLink("at");
		switch (abductedHfIds.size()) {
		case 0:
			return "the " + getOrdinalString() + "Attempted Abduction" + loc + " occurred";
		case 1:
			return "the " + getOrdinalString() + "Abduction of "+World.getHistoricalFigure(abductedHfIds.get(0)).getLink() + loc + " occurred";
		default:
			return "the " + getOrdinalString() + "Abduction" + loc + " occurred";
		}
	}
}
