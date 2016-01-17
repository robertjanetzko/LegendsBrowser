package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("merchant")
public class MerchantEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("source")
	private int sourceId = -1;
	@Xml("destination")
	private int destinationId = -1;
	@Xml("site")
	private int siteId = -1;

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.sourceId == entityId || this.destinationId == entityId;
	}
	
	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		final String site = World.getSite(siteId).getLink();
		final String source = World.getEntity(sourceId).getLink();
		final String destination = World.getEntity(destinationId).getLink();
		return "merchants from " + source + " visited " + destination + " at " + site;
	}

}
