package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity relocate")
public class EntityRelocateEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent, StructureRelatedEvent {
	@Xml("entity,entity_id")
	int entityId = -1;
	@Xml("site,site_id")
	int siteId = -1;
	@Xml("structure,structure_id")
	int structureId = -1;
	@Xml(value = "action", track = true)
	private String action = "UNKNOWN ACTION";

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
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

		return entity + " moved to " + World.getStructure(structureId, siteId).getLink() + " in " + site;
	}
}
