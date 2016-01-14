package legends.model.collections;

import legends.model.collections.basic.EventCollection;
import legends.model.events.ItemStolenEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("theft")
public class TheftCollection extends EventCollection {
	@Xml("attacking_enid")
	private int attackingEnId = -1;
	@Xml("defending_enid")
	private int defendingEnId = -1;
	@XmlComponent
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
	public void process() {
		super.process();

		getAllHistoricalEvents().stream()
				.collect(Filters.filterEvent(ItemStolenEvent.class, e -> e.getCalcSiteId() == -1))
				.forEach(e -> e.setCalcSiteId(location.getSiteId()));
	}

	@Override
	public String getLink() {
		String loc = location.getLink("at");
		return "the <a href=\"/collection/" + getId() + "\" class=\"collection theft\">" + getOrdinalString()
				+ "Theft</a>" + loc;
	}

	@Override
	public String getShortDescription() {
		// String attacker = World.getEntity(attackingEnId).getLink();
		// String defender = World.getEntity(defendingEnId).getLink();
		String loc = location.getLink("at");
		return "the " + getOrdinalString() + "Theft" + loc + " occurred";
	}
}
