package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;

public class MasterpieceEvent extends HfEvent implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id,maker_entity")
	protected int entityId = -1;
	@Xml("site,site_id")
	protected int siteId = -1;
	@Xml("skill_used,skill_at_time,skill_rating")
	protected int skillAtTime = -1;

	@Override
	@Xml("maker")
	public void setHfId(int hfId) {
		super.setHfId(hfId);
	}

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
		return World.getHistoricalFigure(hfId).getLink() + " " + getAction() + " for "
				+ World.getEntity(entityId).getLink() + " in " + World.getSite(siteId).getLink();
	}

	public String getAction() {
		return "created " + getCreation();
	}

	public String getCreation() {
		return "a " + type;
	}

}
