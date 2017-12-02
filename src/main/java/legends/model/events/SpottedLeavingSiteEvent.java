package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("spotted leaving site")
public class SpottedLeavingSiteEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("spotter_hfid")
	private int spotterHfId = -1;
	@Xml("leaver_civ_id")
	private int leaverCivId = -1;
	@Xml("site_civ_id")
	private int siteCivId = -1;
	@Xml("site_id")
	private int siteId = -1;

	public int getSpotterHfId() {
		return spotterHfId;
	}

	public void setSpotterHfId(int spotterHfId) {
		this.spotterHfId = spotterHfId;
	}

	public int getLeaverCivId() {
		return leaverCivId;
	}

	public void setLeaverCivId(int leaverCivId) {
		this.leaverCivId = leaverCivId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return leaverCivId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return World.getHistoricalFigure(spotterHfId).getLink() + " spotted the forces of "
				+ World.getEntity(leaverCivId).getLink() + " slipping out of " + World.getSite(siteId).getLink();
	}
}
