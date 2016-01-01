package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class EntityReloacateEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	int entityId = -1;
	int siteId = -1;
	int structureId = -1;

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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "entity_id":
			setEntityId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "structure_id":
			setStructureId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
	public String getShortDescription() {
		String entity = World.getEntity(entityId).getLink();
		String site = World.getSite(siteId).getLink();

		return entity + " moved to " + structureId + " in " + site;
	}
}
