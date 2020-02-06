package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf preached") /// TODO no type in export
public class HfPreached extends Event implements HfRelatedEvent, SiteRelatedEvent, EntityRelatedEvent {
	@Xml("speaker_hfid")
	private int speakerHfId = -1;
	@Xml("site_hfid")
	private int siteId = -1;
	@Xml(value = "topic", track = true)
	private String topic;
	@Xml("entity_1")
	private int entity1 = -1;
	@Xml("entity_2")
	private int entity2 = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return entity1 == entityId || entity2 == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return speakerHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String t = "";
		switch (topic) {
		case "entity 1 should love entity 2":
			t = ", urging love to be shown to ";
			break;
		case "set entity 1 against entity 2":
			t = ", inveighing against ";
			break;
		default:
			t = "about UNKNOWN TOPIC ";
			break;
		}
		return String.format("%s preached to %s%s %s at %s", World.getHistoricalFigure(speakerHfId).getLink(),
				World.getEntity(entity1).getLink(), t, World.getEntity(entity2).getLink(),
				World.getSite(siteId).getLink());
	}

}
