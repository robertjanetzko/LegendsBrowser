package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("destroyed site")
public class DestroyedSiteEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("attacker_civ_id")
	int attackerCivId = -1;
	@Xml("defender_civ_id")
	int defenderCivId = -1;
	@Xml("site_civ_id")
	int siteCivId = -1;
	@Xml("site_id")
	int siteId = -1;

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
		Site site = World.getSite(siteId);
		site.getEvents().add(this);
		site.setOwner(null);

		Entity siteCiv = World.getEntity(siteCivId);
		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		String site = World.getSite(siteId).getLink();
		return attacker + " defeated " + defender + " and destroyed " + site;
	}
}
