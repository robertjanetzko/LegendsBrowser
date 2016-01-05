package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class EntityPrimaryCriminalsEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	private int entityId = -1;
	private int siteId = -1;
	private int structureId = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "entity":
		case "entity_id":
			setEntityId(Integer.parseInt(value));
			break;
		case "site":
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "structure":
		case "structure_id":
			setStructureId(Integer.parseInt(value));
			break;
		case "action":
			setAction(Integer.parseInt(value));
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
		return entity + " became the primary criminal organization in " + site;
	}

}
