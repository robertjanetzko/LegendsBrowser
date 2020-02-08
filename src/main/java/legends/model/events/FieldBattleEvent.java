package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("field battle")
public class FieldBattleEvent extends Event implements LocalEvent, EntityRelatedEvent, HfRelatedEvent {
	@Xml("attacker_civ_id")
	int attackerCivId = -1;
	@Xml("defender_civ_id")
	int defenderCivId = -1;
	@Xml("attacker_general_hfid")
	int attackerGeneralHfId = -1;
	@Xml("defender_general_hfid")
	int defenderGeneralHfId = -1;
	@Xml("attacker_merc_enid")
	int attackerMercEnid = -1;
	@Xml("defender_merc_enid")
	int defenderMercEnid = -1;

	@XmlComponent
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
				? ". " + World.getHistoricalFigure(attackerGeneralHfId).getLink() + " led the attack"
				: "";
		String defenderGeneral = defenderGeneralHfId != -1
				? " the defenders were led by " + World.getHistoricalFigure(defenderGeneralHfId).getLink()
				: "";
		String generals = attackerGeneral + (attackerGeneralHfId != -1 && defenderGeneralHfId != -1 ? ", and" : "")
				+ defenderGeneral;
		String mercs = "";
		if (attackerMercEnid != -1)
			mercs += String.format(". %s were hired by the attackers", World.getEntity(attackerMercEnid).getLink());
		if (defenderMercEnid != -1)
			mercs += String.format(". The defenders hired %s", World.getEntity(defenderMercEnid).getLink());

		return attacker + " attacked " + defender + location.getLink("in") + generals + mercs;
	}
}
