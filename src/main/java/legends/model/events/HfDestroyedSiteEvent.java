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

@XmlSubtype("hf destroyed site")
public class HfDestroyedSiteEvent extends Event implements HfRelatedEvent, EntityRelatedEvent, SiteRelatedEvent {
	@Xml("attacker_hfid")
	private int attackerHfId = -1;
	@Xml("defender_civ_id")
	private int defenderCivId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("site_id")
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
	public void process() {
		Entity defender = World.getEntity(defenderCivId);
		Site site = World.getSite(siteId);
		site.getEvents().add(this);
		site.setOwner(null);
		defender.getSites().add(site);

		Entity siteCiv = World.getEntity(siteCivId);
		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
		if (siteCiv.getRace().equals("unknown"))
			siteCiv.setRace(defender.getRace());
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getHistoricalFigure(attackerHfId).getLink();
		String defender = defenderCivId != -1 ? World.getEntity(defenderCivId).getLink() : "an unknown civilization";
		String loc = World.getSite(siteId).getLink();
		return attacker + " routed " + World.getEntity(siteCivId).getLink() + " of " + defender + " and destroyed "
				+ loc;
	}

}
