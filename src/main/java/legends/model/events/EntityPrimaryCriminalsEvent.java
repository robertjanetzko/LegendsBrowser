package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity primary criminals")
public class EntityPrimaryCriminalsEvent extends Event
		implements EntityRelatedEvent, SiteRelatedEvent, StructureRelatedEvent {
	@Xml("entity,entity_id")
	private int entityId = -1;
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("structure,structure_id")
	private int structureId = -1;
	@Xml("action")
	private int action = -1;

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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
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
	public String getShortDescription() {
		String entity = World.getEntity(entityId).getLink();
		String site = World.getSite(siteId).getLink();
		return entity + " became the primary criminal organization in " + site;
	}

}
