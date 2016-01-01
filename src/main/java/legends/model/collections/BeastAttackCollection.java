package legends.model.collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.CreatureDevouredEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfSimpleBattleEvent;
import legends.model.events.HfSiteEvent;
import legends.model.events.ItemStolenEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;

public class BeastAttackCollection extends EventCollection {
	private int defendingEnId = -1;

	private EventLocation location = new EventLocation();

	private Set<Integer> attackers = new HashSet<>();

	public int getDefendingEnId() {
		return defendingEnId;
	}

	public void setDefendingEnId(int defendingEnId) {
		this.defendingEnId = defendingEnId;
	}

	public EventLocation getLocation() {
		return location;
	}

	public Set<Integer> getAttackers() {
		return attackers;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

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
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			int attacker = -1;
			if (event instanceof HfSimpleBattleEvent)
				attacker = ((HfSimpleBattleEvent) event).getGroup1HfId();
			if (event instanceof HfSiteEvent)
				attacker = ((HfSiteEvent) event).getAttackerHfId();

			if (attacker == -1)
				continue;

			attackers.add(attacker);
			for (int j = i - 1; j >= 0; j--) {
				Event e = events.get(j);
				if (events.get(j) instanceof AddHfEntityLinkEvent) {
					AddHfEntityLinkEvent ae = (AddHfEntityLinkEvent) events.get(j);
					ae.setCalcHfId(attacker);
					ae.setCalcLinkType("enemy");
				} else
					break;
			}
		}

		getHistoricalEvents().stream().filter(e -> e instanceof HfSimpleBattleEvent).map(e -> ((HfSimpleBattleEvent) e))
				.map(HfSimpleBattleEvent::getGroup1HfId).forEach(attackers::add);
		getHistoricalEvents().stream().filter(e -> e instanceof HfSiteEvent).map(e -> ((HfSiteEvent) e))
				.map(HfSiteEvent::getAttackerHfId).forEach(attackers::add);

		// getHistoricalEvents().stream().filter(e -> e instanceof
		// AddHfEntityLinkEvent)
		// .map(e -> ((AddHfEntityLinkEvent) e)).filter(e -> e.getCalcHfId() ==
		// -1)
		// .forEach(e -> e.setCalcHfId(calcBeastHfId));
		getHistoricalEvents().stream().filter(e -> e instanceof AddHfEntityLinkEvent)
				.map(e -> ((AddHfEntityLinkEvent) e)).filter(e -> e.getCalcLinkType().equals(""))
				.forEach(e -> e.setCalcLinkType("enemy"));
		if (attackers.size() == 1)
			getHistoricalEvents().stream().filter(e -> e instanceof ItemStolenEvent).map(e -> ((ItemStolenEvent) e))
					.filter(e -> e.getCalcHfId() == -1).forEach(e -> e.setCalcHfId((Integer) attackers.toArray()[0]));
		getHistoricalEvents().stream().filter(e -> e instanceof ItemStolenEvent).map(e -> ((ItemStolenEvent) e))
				.filter(e -> e.getCalcSiteId() == -1).forEach(e -> e.setCalcSiteId(location.getSiteId()));

		for (int i = 1; i < events.size(); i++) {
			if (events.get(i) instanceof CreatureDevouredEvent && events.get(i - 1) instanceof HfDiedEvent) {
				CreatureDevouredEvent e1 = (CreatureDevouredEvent) events.get(i);
				HfDiedEvent e2 = (HfDiedEvent) events.get(i - 1);
				if (e1.getCalcSlayerHfId() == -1)
					e1.setCalcSlayerHfId(e2.getSlayerHfId());
				if (e1.getCalcDevouredHfId() == -1)
					e1.setCalcDevouredHfId(e2.getHfId());
			}
		}

	}

	@Override
	public String getShortDescription() {
		String loc = location.getLink("in");
		String beast = "UNKNOWN BEAST";
		if (attackers.size() == 1) {
			beast = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getLink();
			return "the rampage of " + beast + " in " + loc + " occurred";
		} else if (attackers.size() > 0) {
			String race = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getRace().toLowerCase();
			return "the " + race + " rampage in " + loc + " occurred";
		} else
			return "the rampage of " + beast + " in " + loc + " occurred";
	}
}
