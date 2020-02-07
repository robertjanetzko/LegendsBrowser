package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity breach feature layer")
public class EntityBreachFeatureLayerEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("site_entity_id")
	private int siteEntityId = -1;
	@Xml("civ_entity_id")
	private int civEntityId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("feature_layer_id")
	private int featureLayerId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return siteEntityId == entityId || civEntityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s of %s breached the Underworld at %s", World.getEntity(siteEntityId).getLink(),
				World.getEntity(civEntityId).getLink(), World.getSite(siteId).getLink());
	}

}
