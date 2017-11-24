package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf recruited unit type for entity")
public class HfRecruitedUnitTypeForEntityEvent extends HfEvent implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("unit_type")
	private String unitType = "";

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

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == siteId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String entity = World.getEntity(entityId).getLink();
		return hf + " recruited " + unitType + "s into " + entity;
	}

}
