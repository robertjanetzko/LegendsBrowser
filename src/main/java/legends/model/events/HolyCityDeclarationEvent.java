package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("holy city declaration")
public class HolyCityDeclarationEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("religion_id")
	private int religionId = -1;
	@Xml("site_id")
	private int siteId = -1;

	public int getReligionId() {
		return religionId;
	}

	public void setReligionId(int religionId) {
		this.religionId = religionId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return religionId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		return World.getEntity(religionId).getLink() + " declared " + site + " to be a holy city";
	}
}
