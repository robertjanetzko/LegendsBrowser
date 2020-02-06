package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity incorporated")
public class EntityOverthrownEvent extends Event implements EntityRelatedEvent, HfRelatedEvent, SiteRelatedEvent {
	@Xml("joiner_entity_id")
	private int joinerEntityId = -1;
	@Xml("joined_entity_id")
	private int joinedEntityId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("leader_hfid")
	private int leaderHfId = -1;
	@Xml("partial_incorporation")
	private boolean partialIncorporation;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.joinerEntityId == entityId || this.joinedEntityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return leaderHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s %s %s under the leadership of %s", World.getEntity(joinerEntityId).getLink(),
				partialIncorporation ? "began operating at the direction of" : "fully incorporated into",
				World.getEntity(joinedEntityId).getLink(), World.getHistoricalFigure(leaderHfId).getLink());
	}
}
