package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class MasterpieceItemEvent extends HfEvent implements SiteRelatedEvent, EntityRelatedEvent {
	private int entityId = -1;
	private int siteId = -1;
	private int skillAtTime = -1;

	private int itemId = -1;
	private String mat;
	private int matIndex = -1;
	private int matType = -1;
	private String itemType;
	private String itemSubType;

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSkillAtTime() {
		return skillAtTime;
	}

	public void setSkillAtTime(int skillAtTime) {
		this.skillAtTime = skillAtTime;
	}

	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "maker":
			setHfId(Integer.parseInt(value));
			break;
		case "entity_id":
		case "maker_entity":
			setEntityId(Integer.parseInt(value));
			break;
		case "site":
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "skill_at_time":
		case "skill_used":
			setSkillAtTime(Integer.parseInt(value));
			break;
		case "item_id":
			setItemId(Integer.parseInt(value));
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

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "";
		if (siteId != -1)
			site = " in " + World.getSite(siteId).getLink();
		String item = "UNKNOWN ITEM";
		String civ = "";
		if (entityId != -1)
			civ = " for " + World.getEntity(entityId).getLink();
		if(mat != null)
			item = mat + " " + itemType;
		
		return hf + " created a masterful " + item + civ + site;
	}

}
