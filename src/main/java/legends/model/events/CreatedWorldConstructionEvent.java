package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.WorldConstructionRelatedEvent;

public class CreatedWorldConstructionEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, WorldConstructionRelatedEvent {
	private int civId = -1;
	private int siteCivId = -1;
	private int wcId = -1;
	private int masterWcId = -1;
	private int siteId1 = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "wcid":
			setWcId(Integer.parseInt(value));
			break;
		case "master_wcid":
			setMasterWcId(Integer.parseInt(value));
			break;
		case "site_id1":
			setSiteId1(Integer.parseInt(value));
			break;
		case "site_id2":
			setSiteId2(Integer.parseInt(value));
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
		return this.siteId1 == siteId || this.siteId2 == siteId;
	}
	
	@Override
	public boolean isRelatedToWorldConstruction(int wcId) {
		return this.wcId == wcId || this.masterWcId == wcId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site1 = World.getSite(siteId1).getLink();
		String site2 = World.getSite(siteId2).getLink();
		String wc = World.getWorldConstruction(wcId).getLink();
		if(masterWcId != -1)
			wc += " as part of "+World.getWorldConstruction(masterWcId).getLink();;
		return siteCiv+" of "+civ+" finished the contruction of "+wc+" connecting "+site1+" and "+site2;
	}

}
