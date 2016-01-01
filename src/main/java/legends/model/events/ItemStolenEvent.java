package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class ItemStolenEvent extends Event implements SiteRelatedEvent {
	private int calcHfId = -1;
	private int calcSiteId = -1;

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public int getCalcSiteId() {
		return calcSiteId;
	}

	public void setCalcSiteId(int calcSiteId) {
		this.calcSiteId = calcSiteId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		default:
			return super.setProperty(property, value);
		}
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.calcSiteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String site = "UNKNOWN SITE";
		if(calcSiteId != -1)
			site = World.getSite(calcSiteId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if(calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		return "UNKNOWN ITEM was stolen from "+site+" by " + hf;
	}

}
