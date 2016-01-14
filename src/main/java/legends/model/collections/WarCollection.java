package legends.model.collections;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.PeaceAcceptedEvent;

public class WarCollection extends EventCollection {
	private String name;
	private int aggressorEntId = -1;
	private int defenderEntId = -1;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAggressorEntId() {
		return aggressorEntId;
	}

	public void setAggressorEntId(int aggressorEntId) {
		this.aggressorEntId = aggressorEntId;
	}

	public int getDefenderEntId() {
		return defenderEntId;
	}

	public void setDefenderEntId(int defenderEntId) {
		this.defenderEntId = defenderEntId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "name":
			setName(value);
			break;
		case "aggressor_ent_id":
			setAggressorEntId(Integer.parseInt(value));
			break;
		case "defender_ent_id":
			setDefenderEntId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public void process() {
		super.process();

		getAllHistoricalEvents().stream().filter(e -> e instanceof PeaceAcceptedEvent).map(e -> ((PeaceAcceptedEvent) e))
				.filter(e -> e.getCalcOfferedCivId() == -1).forEach(e -> e.setCalcOfferedCivId(aggressorEntId));
		getAllHistoricalEvents().stream().filter(e -> e instanceof PeaceAcceptedEvent).map(e -> ((PeaceAcceptedEvent) e))
				.filter(e -> e.getCalcOffererCivId() == -1).forEach(e -> e.setCalcOffererCivId(defenderEntId));

	}

	@Override
	public String getShortDescription() {
		String aggressor = World.getEntity(aggressorEntId).getLink();
		String defender = World.getEntity(defenderEntId).getLink();
		return getLink()+" was waged by "+aggressor+" on "+defender;
	}
	
	@Override
	public String getLink() {
		return "<a href=\"/collection/"+getId()+"\" class=\"collection war\">"+getName()+"</a>";
	}
}
