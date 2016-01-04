package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.EventLocation;

public class TheftCollection extends EventCollection {
	private int attackingEnId = -1;
	private int defendingEnId = -1;

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
	
	public String getLink() {
		String loc = location.getLink("at");
		return "the <a href=\"/collection/"+getId()+"\" class=\"theft\">Theft</a>" + loc;
	}

	@Override
	public String getShortDescription() {
//		String attacker = World.getEntity(attackingEnId).getLink();
//		String defender = World.getEntity(defendingEnId).getLink();
		String loc = location.getLink("at");
		return "the Theft" + loc+" occurred";
	}
}
