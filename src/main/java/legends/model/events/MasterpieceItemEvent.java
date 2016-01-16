package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.Item;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece item")
public class MasterpieceItemEvent extends HfEvent implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id,maker_entity")
	private int entityId = -1;
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("skill_used,skill_at_time")
	private int skillAtTime = -1;
	@XmlComponent
	private Item item = new Item();

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
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "";
		if (siteId != -1)
			site = " in " + World.getSite(siteId).getLink();
		String civ = "";
		if (entityId != -1)
			civ = " for " + World.getEntity(entityId).getLink();

		return hf + " created a masterful " + item.getText() + civ + site;
	}

}
