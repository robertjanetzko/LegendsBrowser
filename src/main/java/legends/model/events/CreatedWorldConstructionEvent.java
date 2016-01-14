package legends.model.events;

import legends.model.World;
import legends.model.WorldConstruction;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.WorldConstructionRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("created world construction")
public class CreatedWorldConstructionEvent extends Event
		implements EntityRelatedEvent, SiteRelatedEvent, WorldConstructionRelatedEvent {
	@Xml("civ_id")
	private int civId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("wcid")
	private int wcId = -1;
	@Xml("master_wcid")
	private int masterWcId = -1;
	@Xml("site_id1")
	private int siteId1 = -1;
	@Xml("site_id2")
	private int siteId2 = -1;

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

	public int getWcId() {
		return wcId;
	}

	public void setWcId(int wcId) {
		this.wcId = wcId;
	}

	public int getMasterWcId() {
		return masterWcId;
	}

	public void setMasterWcId(int masterWcId) {
		this.masterWcId = masterWcId;
	}

	public int getSiteId1() {
		return siteId1;
	}

	public void setSiteId1(int siteId1) {
		this.siteId1 = siteId1;
	}

	public int getSiteId2() {
		return siteId2;
	}

	public void setSiteId2(int siteId2) {
		this.siteId2 = siteId2;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId1 == siteId || this.siteId2 == siteId;
	}

	@Override
	public boolean isRelatedToWorldConstruction(int wcId) {
		return this.wcId == wcId || this.masterWcId == wcId;
	}

	@Override
	public void process() {
		WorldConstruction wc = World.getWorldConstruction(wcId);
		wc.getSites().add(World.getSite(siteId1));
		wc.getSites().add(World.getSite(siteId2));
		if (masterWcId != -1) {
			WorldConstruction master = World.getWorldConstruction(masterWcId);
			wc.setMaster(master);
			master.getParts().add(wc);
		}
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site1 = World.getSite(siteId1).getLink();
		String site2 = World.getSite(siteId2).getLink();
		String wc = World.getWorldConstruction(wcId).getLink();
		if (masterWcId != -1) {
			wc += " as part of " + World.getWorldConstruction(masterWcId).getLink();
		}
		return siteCiv + " of " + civ + " finished the contruction of " + wc + " connecting " + site1 + " and " + site2;
	}

}
