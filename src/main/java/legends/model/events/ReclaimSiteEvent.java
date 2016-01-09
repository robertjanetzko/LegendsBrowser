package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class ReclaimSiteEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	int civId = -1;
	int siteId = -1;
	int siteCivId = -1;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
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
		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public void process() {
		Site site = World.getSite(siteId);
		site.getEvents().add(this);

		Entity civ = World.getEntity(civId);
		civ.getSites().add(site);
		site.setOwner(civ);
		Entity siteCiv = World.getEntity(siteCivId);
		siteCiv.getSites().add(site);
		siteCiv.setParent(civ);
		if(siteCiv.getType() .equals("unknown"))
			siteCiv.setType("sitegovernment");
		if(siteCiv.getRace() .equals("unknown"))
			siteCiv.setRace(civ.getRace());
	}

	@Override
	public String getShortDescription() {
		String siteCiv = World.getEntity(siteCivId).getLink();
		String civ = World.getEntity(civId).getLink();
		String site = World.getSite(siteId).getLink();
		return siteCiv + " of " + civ + " launched an expedition to reclaim " + site;
	}
}
