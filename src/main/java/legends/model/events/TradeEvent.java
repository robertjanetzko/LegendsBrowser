package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("trade")
public class TradeEvent extends Event implements HfRelatedEvent, EntityRelatedEvent, SiteRelatedEvent {
	@Xml("trader_hfid")
	private int traderHfId;
	@Xml("trader_entity_id")
	private int traderEntityId = -1;
	@Xml("source_site_id")
	private int sourceSiteId = -1;
	@Xml("dest_site_id")
	private int destSiteId = -1;
	@Xml("account_shift")
	private int accountShift = -1;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return traderHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return traderEntityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return sourceSiteId == siteId || destSiteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String entity = traderEntityId != -1 ? String.format(" of %s", World.getEntity(traderEntityId).getLink()) : "";

		String outcome = accountShift > 1000 ? "did well" : (accountShift > -1000 ? "broke even" : "did poorly");
		return String.format("%s of %s %s trading from %s to %s", World.getHistoricalFigure(traderHfId).getLink(),
				entity, outcome, World.getSite(sourceSiteId).getLink(), World.getSite(destSiteId).getLink());
	}

}
