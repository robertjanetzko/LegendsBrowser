package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class CreatedSiteEvent extends Event implements HfRelatedEvent, SiteRelatedEvent, EntityRelatedEvent {
	int civId = -1;
	int siteId = -1;
	int siteCivId = -1;
	int builderHfId = -1;

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

	public int getBuilderHfId() {
		return builderHfId;
	}

	public void setBuilderHfId(int builderHfId) {
		this.builderHfId = builderHfId;
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
		case "builder_hfid":
			setBuilderHfId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return builderHfId == hfId;
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
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		if (builderHfId != -1)
			return World.getHistoricalFigure(builderHfId).getLink() + " created " + site;
		else if (siteCivId != -1)
			return World.getEntity(siteCivId).getLink() + " of " + World.getEntity(civId).getLink() + " founded " + site
					+ ".";
		else
			return World.getEntity(civId).getLink() + " created " + site + ".";
	}
}
