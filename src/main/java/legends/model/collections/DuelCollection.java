package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.EventLocation;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("duel")
public class DuelCollection extends EventCollection {
	@Xml("attacking_hfid")
	private int attackingHfId = -1;
	@Xml("defending_hfid")
	private int defendingHfId = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	public int getAttackingHfId() {
		return attackingHfId;
	}

	public void setAttackingHfId(int attackingHfId) {
		this.attackingHfId = attackingHfId;
	}

	public int getDefendingHfId() {
		return defendingHfId;
	}

	public void setDefendingHfId(int defendingHfId) {
		this.defendingHfId = defendingHfId;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getLink() {
		String attacker = World.getHistoricalFigure(attackingHfId).getLink();
		String defender = World.getHistoricalFigure(defendingHfId).getLink();
		return "the <a href=\"/collection/" + getId() + "\" class=\"collection duel\">" + getOrdinalString()
				+ "Duel</a> of " + attacker + " and " + defender;
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getHistoricalFigure(attackingHfId).getLink();
		String defender = World.getHistoricalFigure(defendingHfId).getLink();
		return "the " + getOrdinalString() + "Duel of " + attacker + " and " + defender + " occurred";
	}
}
