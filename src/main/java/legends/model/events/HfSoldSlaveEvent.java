package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf sold slave") /// TODO no type in export
public class HfSoldSlaveEvent extends Event implements HfRelatedEvent, SiteRelatedEvent, EntityRelatedEvent {
	@Xml("enslaved_hfid")
	private int enslavedHfId = -1;
	@Xml("seller_hfid")
	private int sellerHfId = -1;
	@Xml("payer_entity_id")
	private int payerEntityId = -1;
	@Xml("moved_to_site_id")
	private int movedToSiteId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return payerEntityId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return enslavedHfId == hfId || sellerHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return movedToSiteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s sold %s to %s in %s", World.getHistoricalFigure(sellerHfId).getLink(),
				World.getHistoricalFigure(enslavedHfId).getLink(), World.getEntity(payerEntityId).getLink(),
				World.getSite(movedToSiteId).getLink());
	}

}
