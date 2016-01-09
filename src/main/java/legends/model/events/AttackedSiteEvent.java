package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class AttackedSiteEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, HfRelatedEvent {
	int attackerCivId = -1;
	int defenderCivId = -1;
	int siteCivId = -1;
	int siteId = -1;
	int attackerGeneralHfId = -1;
	int defenderGeneralHfId = -1;

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

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_civ_id":
			setAttackerCivId(Integer.parseInt(value));
			break;
		case "defender_civ_id":
			setDefenderCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "attacker_general_hfid":
			setAttackerGeneralHfId(Integer.parseInt(value));
			break;
		case "defender_general_hfid":
			setDefenderGeneralHfId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return defenderCivId == entityId || attackerCivId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return attackerGeneralHfId == hfId || defenderGeneralHfId == hfId;
	}

	@Override
	public void process() {
		Site site = World.getSite(siteId);

		Entity defender = World.getEntity(defenderCivId);
		Entity siteCiv = World.getEntity(siteCivId);
		siteCiv.setParent(defender);
		defender.getSites().add(site);
		site.setOwner(defender);

		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
		if (siteCiv.getRace().equals("unknown"))
			siteCiv.setRace(defender.getRace());
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		if (defenderCivId != siteCivId)
			defender = World.getEntity(siteCivId).getLink() + " of " + defender;
		String site = World.getSite(siteId).getLink();

		String attackerGeneral = attackerGeneralHfId != -1
				? ". " + World.getHistoricalFigure(attackerGeneralHfId).getLink() + " led the attack" : "";
		String defenderGeneral = defenderGeneralHfId != -1
				? " the defenders were led by " + World.getHistoricalFigure(defenderGeneralHfId).getLink() : "";
		String generals = attackerGeneral + (attackerGeneralHfId != -1 && defenderGeneralHfId != -1 ? ", and" : "")
				+ defenderGeneral;

		return attacker + " attacked " + defender + " at " + site + generals;
	}
}
