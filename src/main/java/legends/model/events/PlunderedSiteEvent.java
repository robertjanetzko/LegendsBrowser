package legends.model.events;

import legends.model.Entity;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("plundered site")
public class PlunderedSiteEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("attacker_civ_id")
	int attackerCivId = -1;
	@Xml("defender_civ_id")
	int defenderCivId = -1;
	@Xml("site_civ_id")
	int siteCivId = -1;
	@Xml("site_id")
	int siteId = -1;
	@Xml("detected")
	private boolean detected; /// TODO
	
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

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return defenderCivId == entityId || attackerCivId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public void process() {
		Entity civ = World.getEntity(defenderCivId);
		Entity siteCiv = World.getEntity(siteCivId);
		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
		if (siteCiv.getRace().equals("unknown"))
			siteCiv.setRace(civ.getRace());
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		if (defenderCivId != siteCivId)
			defender = World.getEntity(siteCivId).getLink() + " of " + defender;
		String site = World.getSite(siteId).getLink();

		return attacker + " defeated " + defender + " and pillaged " + site;
	}
}
