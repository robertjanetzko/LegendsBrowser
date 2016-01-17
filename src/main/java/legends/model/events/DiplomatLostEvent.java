package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("diplomat lost")
public class DiplomatLostEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("entity")
	private int entityId = -1;
	@Xml("involved")
	private int involvedEnId = -1;
	@Xml("site,site_id")
	private int siteId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId || this.involvedEnId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return World.getEntity(entityId).getLink() + " lost a diplomat in " + World.getSite(siteId).getLink()
				+ ". They suspected the involvement of " + World.getEntity(involvedEnId).getLink();
	}
}
