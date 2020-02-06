package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf acquired building") /// TODO no type in export
public class HfAcquiredBuildingEvent extends Event implements SiteRelatedEvent, HfRelatedEvent, StructureRelatedEvent {
	@Xml("site_id")
	private int siteId = -1;
	@Xml("building_profile_id")
	private int buildingProfileId = -1;
	@Xml("acquirer_hfid")
	private int acquirerHfId = -1;
	@Xml("last_owner_hfid")
	private int lastOwnerHfid = -1;
	@Xml("purchased_unowned")
	private boolean purchasedUnowned;
	@Xml("inherited")
	private boolean inherited;
	@Xml("rebuilt_ruined")
	private boolean rebuiltRuined;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return acquirerHfId == hfId || lastOwnerHfid == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}
	
	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.siteId == siteId && buildingProfileId == structureId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s %s %s in %s%s", World.getHistoricalFigure(acquirerHfId).getLink(),
				purchasedUnowned ? "purchased" : "inherited", World.getSiteProperty(siteId, buildingProfileId).getType(), World.getSite(siteId).getLink(),
				lastOwnerHfid != -1
						? String.format(" formerly owned by %s", World.getHistoricalFigure(lastOwnerHfid).getLink())
						: "");
	}
}
