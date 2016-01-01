package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class HfSiteEvent extends Event implements HfRelatedEvent, EntityRelatedEvent, SiteRelatedEvent {
	private int attackerHfId = -1;
	private int defenderCivId = -1;
	private int siteCivId = -1;
	private int siteId = -1;

	public int getAttackerHfId() {
		return attackerHfId;
	}

	public void setAttackerHfId(int attackerHfId) {
		this.attackerHfId = attackerHfId;
	}

	public int getDefenderCivId() {
		return defenderCivId;
	}

	public void setDefenderCivId(int defenderCivId) {
		this.defenderCivId = defenderCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_hfid":
			setAttackerHfId(Integer.parseInt(value));
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
		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return attackerHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return defenderCivId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getHistoricalFigure(attackerHfId).getLink();
		String defender = defenderCivId != -1 ? World.getEntity(defenderCivId).getLink() : "an unknown civilization";
		String loc = World.getSite(siteId).getLink();
		switch (type) {
		case "hf attacked site":
			return attacker + " attacked " + defender + " in " + loc;
		case "hf destroyed site":
			return attacker + " routed " + World.getEntity(siteCivId).getLink() + " of " + defender + " and destroyed "
					+ loc;
		default:
			return super.getShortDescription();
		}
	}

}
