package legends.model.collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.CreatureDevouredEvent;
import legends.model.events.HfAttackedSiteEvent;
import legends.model.events.HfDestroyedSiteEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfSimpleBattleEvent;
import legends.model.events.ItemStolenEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("beast attack")
public class BeastAttackCollection extends EventCollection {
	@Xml("defending_enid")
	private int defendingEnId = -1;
	@XmlComponent
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
	public void process() {
		super.process();

		List<Event> events = getAllHistoricalEvents();
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			int attacker = -1;
			if (event instanceof HfSimpleBattleEvent)
				attacker = ((HfSimpleBattleEvent) event).getGroup1HfId();
			else if (event instanceof HfAttackedSiteEvent)
				attacker = ((HfAttackedSiteEvent) event).getAttackerHfId();
			else if (event instanceof HfDestroyedSiteEvent)
				attacker = ((HfDestroyedSiteEvent) event).getAttackerHfId();
			else if (event instanceof AddHfEntityLinkEvent) {
				AddHfEntityLinkEvent e = (AddHfEntityLinkEvent) event;
				if (e.getCalcHfId() != -1)
					attacker = e.getCalcHfId();
			} else if (event instanceof CreatureDevouredEvent) {
				CreatureDevouredEvent e = (CreatureDevouredEvent) event;
				if (e.getCalcSlayerHfId() != -1)
					attacker = e.getCalcSlayerHfId();
			} else if (event instanceof ItemStolenEvent) {
				ItemStolenEvent e = (ItemStolenEvent) event;
				if (e.getHfId() != -1)
					attacker = e.getHfId();
			}

			if (attacker == -1)
				continue;

			attackers.add(attacker);
			for (int j = i - 1; j >= 0; j--) {
				Event e = events.get(j);
				if (e instanceof AddHfEntityLinkEvent) {
					AddHfEntityLinkEvent ae = (AddHfEntityLinkEvent) e;
					ae.setCalcHfId(attacker);
					ae.setCalcLinkType("enemy");
				} else
					break;
			}
		}

		getAllHistoricalEvents().stream().filter(e -> e instanceof HfSimpleBattleEvent)
				.map(e -> ((HfSimpleBattleEvent) e)).map(HfSimpleBattleEvent::getGroup1HfId).forEach(attackers::add);
		getAllHistoricalEvents().stream().filter(e -> e instanceof HfDestroyedSiteEvent)
				.map(e -> ((HfDestroyedSiteEvent) e)).map(HfDestroyedSiteEvent::getAttackerHfId)
				.forEach(attackers::add);

		// getHistoricalEvents().stream().filter(e -> e instanceof
		// AddHfEntityLinkEvent)
		// .map(e -> ((AddHfEntityLinkEvent) e)).filter(e -> e.getCalcHfId() ==
		// -1)
		// .forEach(e -> e.setCalcHfId(calcBeastHfId));
		getAllHistoricalEvents().stream().filter(e -> e instanceof AddHfEntityLinkEvent)
				.map(e -> ((AddHfEntityLinkEvent) e)).filter(e -> e.getCalcLinkType().equals(""))
				.forEach(e -> e.setCalcLinkType("enemy"));
		if (attackers.size() == 1)
			getAllHistoricalEvents().stream().filter(e -> e instanceof ItemStolenEvent).map(e -> ((ItemStolenEvent) e))
					.filter(e -> e.getHfId() == -1).forEach(e -> e.setHfId((Integer) attackers.toArray()[0]));
		getAllHistoricalEvents().stream().filter(e -> e instanceof ItemStolenEvent).map(e -> ((ItemStolenEvent) e))
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

			if (events.get(i) instanceof AddHfEntityLinkEvent && events.get(i - 1) instanceof AddHfEntityLinkEvent) {
				AddHfEntityLinkEvent e1 = (AddHfEntityLinkEvent) events.get(i);
				AddHfEntityLinkEvent e2 = (AddHfEntityLinkEvent) events.get(i - 1);
				Entity civ1 = World.getEntity(e1.getCivId());
				if (civ1.getType().equals("civilization")) {
					Entity civ2 = World.getEntity(e2.getCivId());
					if (civ2.getType().equals("unknown"))
						civ2.setType("sitegovernment");
					if (civ2.getRace().equals("unknown"))
						civ2.setRace(civ1.getRace());
					if (civ2.getParent() == null)
						civ2.setParent(civ1);

					if (location.getSiteId() != -1) {
						Site site = World.getSite(location.getSiteId());
						civ1.getSites().add(site);
						civ2.getSites().add(site);
					}
				}
			}
		}

	}

	@Override
	public String getLink() {
		String loc = location.getLink("in");
		String beast = "UNKNOWN BEAST";

		if (attackers.size() == 1) {
			beast = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getLink();
			return "the <a href=\"" + getUrl() + "\" class=\"collection beast-attack\">rampage</a> of "
					+ beast + loc;
		} else if (attackers.size() > 0) {
			String race = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getRace().toLowerCase();
			return "the " + race + " <a href=\"" + getUrl()
					+ "\" class=\"collection beast-attack\">rampage</a>" + loc;
		} else
			return "the <a href=\"" + getUrl() + "\" class=\"collection beast-attack\">rampage of " + beast
					+ "</a>" + loc;
	}

	@Override
	public String getShortDescription() {
		String loc = location.getLink("in");
		String beast = "UNKNOWN BEAST";
		if (attackers.size() == 1) {
			beast = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getLink();
			return "the rampage of " + beast + loc + " occurred";
		} else if (attackers.size() > 0) {
			String race = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getRace().toLowerCase();
			return "the " + race + " rampage" + loc + " occurred";
		} else
			return "the rampage of " + beast + loc + " occurred";
	}

	public String getName() {
		String loc = " in " + World.getSite(location.getSiteId()).getName();
		String beast = "UNKNOWN BEAST";
		if (attackers.size() == 1) {
			beast = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getName();
			return "The rampage of " + beast + loc;
		} else if (attackers.size() > 0) {
			String race = World.getHistoricalFigure((Integer) attackers.toArray()[0]).getRace().toLowerCase();
			return "The " + race + " rampage" + loc;
		} else
			return "The rampage of " + beast + loc;
	}
}
