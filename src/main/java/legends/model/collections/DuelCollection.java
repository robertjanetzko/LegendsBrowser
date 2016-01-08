package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.EventLocation;

public class DuelCollection extends EventCollection {
	private int attackingHfId = -1;
	private int defendingHfId = -1;

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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "attacking_hfid":
			setAttackingHfId(Integer.parseInt(value));
			break;
		case "defending_hfid":
			setDefendingHfId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}
	
	@Override
	public String getLink() {
		String attacker = World.getHistoricalFigure(attackingHfId).getLink();
		String defender = World.getHistoricalFigure(defendingHfId).getLink();
		return "the <a href=\"/collection/"+getId()+"\" class=\"duel\">"+getOrdinalString()+"Duel</a> of " + attacker + " and " + defender;
	}


	@Override
	public String getShortDescription() {
		String attacker = World.getHistoricalFigure(attackingHfId).getLink();
		String defender = World.getHistoricalFigure(defendingHfId).getLink();
		return "the "+getOrdinalString()+"Duel of " + attacker + " and " + defender + " occurred";
	}
}
