package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class SiteTakenOverEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	private int attackerCivId = -1;
	private int defenderCivId = -1;
	private int siteCivId = -1;
	private int newSiteCivId = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_civ_id":
			setAttackerCivId(Integer.parseInt(value));
			break;
		case "defender_civ_id":
			setDefenderCivId(Integer.parseInt(value));
			break;
		case "new_site_civ_id":
			setNewSiteCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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

		World.getEntity(attackerCivId).getSites().add(site);
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
