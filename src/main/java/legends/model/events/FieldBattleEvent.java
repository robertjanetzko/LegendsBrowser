package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class FieldBattleEvent extends Event implements LocalEvent, EntityRelatedEvent, HfRelatedEvent {
	int attackerCivId = -1;
	int defenderCivId = -1;
	int attackerGeneralHfId = -1;
	int defenderGeneralHfId = -1;

	private EventLocation location = new EventLocation();

	public int getAttackerCivId() {
		return attackerCivId;
	}

	public void setAttackerCivId(int attackerCivId) {
		this.attackerCivId = attackerCivId;
	}

	public int getDefenderCivId() {
		return defenderCivId;
	}

	public void setDefenderCivId(int defenderCivId) {
		this.defenderCivId = defenderCivId;
	}

	public int getAttackerGeneralHfId() {
		return attackerGeneralHfId;
	}

	public void setAttackerGeneralHfId(int attackerGeneralHfId) {
		this.attackerGeneralHfId = attackerGeneralHfId;
	}

	public int getDefenderGeneralHfId() {
		return defenderGeneralHfId;
	}

	public void setDefenderGeneralHfId(int defenderGeneralHfId) {
		this.defenderGeneralHfId = defenderGeneralHfId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_civ_id":
			setAttackerCivId(Integer.parseInt(value));
			break;
		case "defender_civ_id":
			setDefenderCivId(Integer.parseInt(value));
			break;
		case "attacker_general_hfid":
			setAttackerGeneralHfId(Integer.parseInt(value));
			break;
		case "defender_general_hfid":
			setDefenderGeneralHfId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return defenderCivId == entityId || attackerCivId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return attackerGeneralHfId == hfId || defenderGeneralHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		

		String attackerGeneral = attackerGeneralHfId != -1
				? ". " + World.getHistoricalFigure(attackerGeneralHfId).getLink() + " led the attack" : "";
		String defenderGeneral = defenderGeneralHfId != -1
				? " the defenders were led by " + World.getHistoricalFigure(defenderGeneralHfId).getLink() : "";
		String generals = attackerGeneral + (attackerGeneralHfId != -1 && defenderGeneralHfId != -1 ? ", and" : "")
				+ defenderGeneral;

		return attacker + " attacked " + defender + location.getLink("in") + generals;
	}
}
