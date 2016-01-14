package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("site retired")
public class SiteRetiredEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("civ_id")
	private int civId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("first")
	private boolean first = false;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
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

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.civId == entityId || this.siteCivId == entityId;
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
		Entity siteCiv = World.getEntity(siteCivId);
		if (siteCiv.getType().equals("unknown"))
			siteCiv.setType("sitegovernment");
		if (siteCiv.getRace().equals("unknown"))
			siteCiv.setRace(civ.getRace());
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site = World.getSite(siteId).getLink();

		return siteCiv + " of " + civ + " retired " + site;
	}

}
