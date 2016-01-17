package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("first contact")
public class FirstContactEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("contactor_enid")
	private int contactorEnId = -1;
	@Xml("contacted_enid")
	private int contactedEnId = -1;
	@Xml("site_id")
	private int siteId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.contactorEnId == entityId || this.contactedEnId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return World.getEntity(contactorEnId).getLink() + " made contact with " + World.getEntity(contactedEnId).getLink()
				+ " in " + World.getSite(siteId).getLink();
	}
}
