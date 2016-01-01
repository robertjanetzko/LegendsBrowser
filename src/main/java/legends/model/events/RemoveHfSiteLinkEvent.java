package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class RemoveHfSiteLinkEvent extends Event implements HfRelatedEvent, SiteRelatedEvent {
	private int siteId = -1;

	private int calcHfId = -1;
	private String calcLinkType = "";
	private int calcBuildingId = -1;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public String getCalcLinkType() {
		return calcLinkType;
	}

	public void setCalcLinkType(String calcLinkType) {
		this.calcLinkType = calcLinkType;
	}

	public int getCalcBuildingId() {
		return calcBuildingId;
	}

	public void setCalcBuildingId(int calcBuildingId) {
		this.calcBuildingId = calcBuildingId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getDescription() {
		String site = World.getSite(siteId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		String building = "UNKNOWN BUILDING";
		if (calcBuildingId != -1)
			building = "" + calcBuildingId;
		
		switch (calcLinkType) {
		default:
			return hf + " removed from site " + site;
		}
	}
	
	@Override
	public String getShortDescription() {
		String info = "<ul>";
		Event prev = World.getHistoricalEvent(getId() - 1);
		if (prev != null)
			info += "<li>after: " + prev.getDescription()+"</li>";
		Event next = World.getHistoricalEvent(getId() + 1);
		if (next != null)
			info += "<li>before: " + next.getDescription()+"</li>";
		info += "</ul>";
		return getDescription()+info;
	}

}
