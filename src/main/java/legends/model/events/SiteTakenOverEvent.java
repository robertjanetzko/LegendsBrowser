package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("site taken over")
public class SiteTakenOverEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("attacker_civ_id")
	private int attackerCivId = -1;
	@Xml("defender_civ_id")
	private int defenderCivId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("new_site_civ_id")
	private int newSiteCivId = -1;
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

	public int getNewSiteCivId() {
		return newSiteCivId;
	}

	public void setNewSiteCivId(int newSiteCivId) {
		this.newSiteCivId = newSiteCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.attackerCivId == entityId || this.defenderCivId == entityId || this.newSiteCivId == entityId
				|| this.siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public void process() {
		Site site = World.getSite(siteId);
		site.getEvents().add(this);

		Entity attacker = World.getEntity(attackerCivId);
		attacker.getSites().add(site);
		site.setOwner(attacker);

		Entity newSiteCiv = World.getEntity(newSiteCivId);
		newSiteCiv.getSites().add(site);
		newSiteCiv.setParent(attacker);

		Entity defender = World.getEntity(defenderCivId);
		Entity siteCiv = World.getEntity(siteCivId);

		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
		if (siteCiv.getRace().equals("unknown"))
			siteCiv.setRace(defender.getRace());

		if (newSiteCiv.getType().equals("unknown"))
			newSiteCiv.setType("sitegovernment");
		if (newSiteCiv.getRace().equals("unknown"))
			newSiteCiv.setRace(attacker.getRace());

	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String defender = World.getEntity(defenderCivId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String newSiteCiv = World.getEntity(newSiteCivId).getLink();
		String site = World.getSite(siteId).getLink();

		return attacker + " defeated " + siteCiv + " of " + defender + " and took over " + site
				+ ". The new government was called " + newSiteCiv;
	}
}
