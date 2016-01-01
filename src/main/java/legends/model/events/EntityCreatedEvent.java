package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class EntityCreatedEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	private int entityId = -1;
	private int siteId = -1;
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
		try {
			if (siteId != -1)
				return World.getEntity(entityId).getLink() + " formed in " + structureId + " in "
						+ World.getSite(siteId).getLink();
			else
				return World.getEntity(entityId).getLink() + " formed";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
