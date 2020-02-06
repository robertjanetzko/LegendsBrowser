package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity created")
public class EntityCreatedEvent extends Event
		implements EntityRelatedEvent, SiteRelatedEvent, StructureRelatedEvent, HfRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("structure_id")
	private int structureId = -1;
	@Xml("creator_hfid")
	private int creatorHfId = -1;

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

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return creatorHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String entity = creatorHfId == -1 ? String.format("%s formed", World.getEntity(entityId).getLink())
				: String.format("%s formed %s", World.getHistoricalFigure(creatorHfId).getLink(),
						World.getEntity(entityId).getLink());
		if (siteId != -1) {
			String site = World.getSite(siteId).getLink();
			if (structureId != -1)
				return String.format("%s in %s in %s", entity, World.getStructure(structureId, siteId).getLink(), site);
			else
				return String.format("%s in %s", entity, site);
		}
		return entity;

	}

}
