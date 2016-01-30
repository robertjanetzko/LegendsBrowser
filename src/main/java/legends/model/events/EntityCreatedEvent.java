package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity created")
public class EntityCreatedEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, StructureRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("structure_id")
	private int structureId = -1;

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
	public String getShortDescription() {
		if (siteId != -1)
			if (structureId != -1)
				return World.getEntity(entityId).getLink() + " formed in "
						+ World.getStructure(structureId, siteId).getLink() + " in " + World.getSite(siteId).getLink();
			else
				return World.getEntity(entityId).getLink() + " formed in " + World.getSite(siteId).getLink();
		else
			return World.getEntity(entityId).getLink() + " formed";
	}

}
