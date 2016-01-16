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

@XmlSubtype("created site")
public class CreatedSiteEvent extends Event implements HfRelatedEvent, SiteRelatedEvent, EntityRelatedEvent {
	@Xml("civ_id")
	int civId = -1;
	@Xml("site_id")
	int siteId = -1;
	@Xml("site_civ_id")
	int siteCivId = -1;
	@Xml("builder_hfid")
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

		if (siteCivId != -1) {
			Entity siteCiv = World.getEntity(siteCivId);
			siteCiv.getSites().add(site);
			siteCiv.setParent(civ);
			if (siteCiv.getType().equals("unknown"))
				siteCiv.setType("sitegovernment");
			if (siteCiv.getRace().equals("unknown"))
				siteCiv.setRace(civ.getRace());
		} else {
			if (civ.getType().equals("unknown"))
				civ.setType("sitegovernment");
		}
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
