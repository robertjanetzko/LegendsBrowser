package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf ransomed") /// TODO no type in export
public class HfRansomedEvent extends Event implements HfRelatedEvent, SiteRelatedEvent {
	@Xml("ransomed_hfid")
	private int ransomedHfId = -1;
	@Xml("ransomer_hfid")
	private int ransomerHfId = -1; /// TODO wrong value in export
	@Xml("payer_hfid")
	private int payerHfId = -1;
	@Xml("moved_to_site_id")
	private int movedToSiteId = -1;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return ransomedHfId == hfId || ransomerHfId == hfId || ransomerHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return movedToSiteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s ransomed %s to %s. %s was sent to %s", World.getHistoricalFigure(ransomerHfId).getLink(),
				World.getHistoricalFigure(ransomedHfId).getLink(), World.getHistoricalFigure(payerHfId).getLink(),
				World.getHistoricalFigure(ransomedHfId).getShortLink(), World.getSite(movedToSiteId).getLink());
	}

}
