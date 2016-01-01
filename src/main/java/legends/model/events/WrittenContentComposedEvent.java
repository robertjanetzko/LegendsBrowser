package legends.model.events;

import legends.model.World;
import legends.model.events.basic.InspiredEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class WrittenContentComposedEvent extends InspiredEvent implements SiteRelatedEvent {
	private int wcId = -1;
	private int siteId = -1;

	public int getWcId() {
		return wcId;
	}

	public void setWcId(int wcId) {
		this.wcId = wcId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "wc_id":
			setWcId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "UNKNOWN SITE";
		if (siteId != -1)
			site = World.getSite(siteId).getLink();
		return wcId + " was authored by " + hf + " in " + site + getReasonString() + getCircumstanceString();
	}

}
