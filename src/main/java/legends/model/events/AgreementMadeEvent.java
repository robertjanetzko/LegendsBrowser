package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("agreement made")
public class AgreementMadeEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("source")
	private int sourceId = -1;
	@Xml("destination")
	private int destinatioId = -1;
	@Xml("topic")
	private String topic = "";

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.sourceId == entityId || this.destinatioId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String s = topic;
		switch (topic) {
		case "becomelandholder":
			s = "the establishment of landed nobility";
			break;
		case "promotelandholder":
			s = "the elevation of landed nobility";
			break;
		case "treequota":
			s = "a lumber agreement";
			break;
		}
		return s+" proposed by "+World.getEntity(sourceId).getLink() + " was accepted by " + World.getEntity(destinatioId).getLink() + " in "
				+ World.getSite(siteId).getLink();
	}
}
