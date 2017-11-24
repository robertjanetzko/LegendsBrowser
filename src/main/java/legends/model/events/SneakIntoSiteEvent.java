package legends.model.events;

import legends.model.Entity;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("sneak into site")
public class SneakIntoSiteEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("attacker_civ_id")
	private int attackerCivId = -1;
	@Xml("defender_civ_id")
	private int defenderCivId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("site_id")
	private int siteId = -1;

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
		String attacker = "an unknown civilization";
		if (attackerCivId != -1)
			attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		if (defenderCivId != siteCivId)
			defender = World.getEntity(siteCivId).getLink() + " of " + defender;
		String site = World.getSite(siteId).getLink();

		return attacker + " slipped into " + site + (defenderCivId != -1 ? ", undetected by " + defender : "");
	}
}
