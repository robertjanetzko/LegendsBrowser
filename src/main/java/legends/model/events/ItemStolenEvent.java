package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;

public class ItemStolenEvent extends Event implements HfRelatedEvent, SiteRelatedEvent, StructureRelatedEvent {
	private int calcHfId = -1;
	private int calcSiteId = -1;

	private String mat;
	private int matIndex = -1;
	private int matType = -1;
	private String itemType;
	private String itemSubType;
	private int structureId = -1;
	private int entityId = -1;

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public int getCalcSiteId() {
		return calcSiteId;
	}

	public void setCalcSiteId(int calcSiteId) {
		this.calcSiteId = calcSiteId;
	}

	public String getMat() {
		return mat;
	}

	public void setMat(String mat) {
		this.mat = mat;
	}

	public int getMatIndex() {
		return matIndex;
	}

	public void setMatIndex(int matIndex) {
		this.matIndex = matIndex;
	}

	public int getMatType() {
		return matType;
	}

	public void setMatType(int matType) {
		this.matType = matType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemSubType() {
		return itemSubType;
	}

	public void setItemSubType(String itemSubType) {
		this.itemSubType = itemSubType;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "histfig":
			setCalcHfId(Integer.parseInt(value));
			break;
		case "mat":
			setMat(value);
			break;
		case "matindex":
			setMatIndex(Integer.parseInt(value));
			break;
		case "mattype":
			setMatType(Integer.parseInt(value));
			break;
		case "item_type":
			setItemType(value);
			break;
		case "item_subtype":
			setItemSubType(value);
			break;
		case "site":
			setCalcSiteId(Integer.parseInt(value));
			break;
		case "structure":
			setStructureId(Integer.parseInt(value));
			break;
		case "entity":
			setEntityId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.calcSiteId == siteId;
	}
	
	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.calcSiteId == siteId;
	}
	
	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.calcHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String structure = "";
		if(structureId != -1)
			structure = World.getStructure(structureId, calcSiteId).getLink()+" in ";
			
		String site = "UNKNOWN SITE";
		if (calcSiteId != -1)
			site = World.getSite(calcSiteId).getLink();
		
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		
		return mat + " " + itemType + " was stolen from "+structure + site + " by " + hf;
	}

}
