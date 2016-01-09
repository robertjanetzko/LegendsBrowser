package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class NewSiteLeaderEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, HfRelatedEvent {
	private int attackerCivId = -1;
	private int newSiteCivId = -1;
	private int defenderCivId = -1;
	private int siteCivId = -1;
	private int siteId = -1;
	private int newLeaderHfId = -1;

	public int getAttackerCivId() {
		return attackerCivId;
	}

	public void setAttackerCivId(int attackerCivId) {
		this.attackerCivId = attackerCivId;
	}

	public int getNewSiteCivId() {
		return newSiteCivId;
	}

	public void setNewSiteCivId(int newSiteCivId) {
		this.newSiteCivId = newSiteCivId;
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

	public int getNewLeaderHfId() {
		return newLeaderHfId;
	}

	public void setNewLeaderHfId(int newLeaderHfId) {
		this.newLeaderHfId = newLeaderHfId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_civ_id":
			setAttackerCivId(Integer.parseInt(value));
			break;
		case "new_site_civ_id":
			setNewSiteCivId(Integer.parseInt(value));
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
		case "new_leader_hfid":
			setNewLeaderHfId(Integer.parseInt(value));
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
		return newLeaderHfId == hfId;
	}

	@Override
	public void process() {
		Site site = World.getSite(siteId);
		site.getEvents().add(this);

		Entity attacker = World.getEntity(attackerCivId);
		site.setOwner(attacker);
		Entity defender = World.getEntity(defenderCivId);
		Entity siteCiv = World.getEntity(siteCivId);
		Entity newSiteCiv = World.getEntity(newSiteCivId);
		newSiteCiv.setParent(attacker);

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
		if (defenderCivId != siteCivId)
			defender = World.getEntity(siteCivId).getLink() + " of " + defender;
		String site = World.getSite(siteId).getLink();
		String leader = World.getHistoricalFigure(newLeaderHfId).getLink();
		String newSiteCiv = World.getEntity(newSiteCivId).getLink();

		return attacker + " defeated " + defender + " and placed " + leader + " in charge of " + site
				+ ". The new government was called " + newSiteCiv;
	}
}
