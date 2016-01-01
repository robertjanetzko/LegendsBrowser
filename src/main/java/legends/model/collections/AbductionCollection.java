package legends.model.collections;

import java.util.List;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.CreatureDevouredEvent;
import legends.model.events.HfAbductedEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfLearnsSecretEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;

public class AbductionCollection extends EventCollection {
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
	}

	@Override
	public String getShortDescription() {
//		String attacker = World.getEntity(attackingEnId).getLink();
//		String defender = World.getEntity(defendingEnId).getLink();
		String loc = location.getLink("at");
		return "the Abduction"+ loc+" occurred";
	}
}
