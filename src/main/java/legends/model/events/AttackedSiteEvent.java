package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("attacked site")
public class AttackedSiteEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, HfRelatedEvent {
	@Xml("attacker_civ_id")
	int attackerCivId = -1;
	@Xml("defender_civ_id")
	int defenderCivId = -1;
	@Xml("site_civ_id")
	int siteCivId = -1;
	@Xml("site_id")
	int siteId = -1;
	@Xml("attacker_general_hfid")
	int attackerGeneralHfId = -1;
	@Xml("defender_general_hfid")
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
