package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("remove hf site link")
public class RemoveHfSiteLinkEvent extends HfEvent
		implements SiteRelatedEvent, StructureRelatedEvent, EntityRelatedEvent {
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("structure")
	private int calcBuildingId = -1;
	@Xml(value = "link_type", track = true)
	private String linkType = "";
	@Xml("civ")
	private int civId = -1;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getCalcBuildingId() {
		return calcBuildingId;
	}

	public void setCalcBuildingId(int calcBuildingId) {
		this.calcBuildingId = calcBuildingId;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	private static Set<String> linkTypes = new HashSet<>();

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.calcBuildingId == structureId && this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public void process() {
		Event prev = World.getHistoricalEvent(getId() - 1);
		if (prev instanceof HfAbductedEvent) {
			HfAbductedEvent event = (HfAbductedEvent) prev;
			if (hfId == -1)
				setHfId(event.getTargetHfId());
		}

		Event next = World.getHistoricalEvent(getId() + 1);
		while (next instanceof AddHfSiteLinkEvent)
			next = World.getHistoricalEvent(next.getId() + 1);
		if (next instanceof ChangeHfStateEvent) {
			ChangeHfStateEvent event = (ChangeHfStateEvent) next;
			if (hfId == -1)
				setHfId(event.getHfId());
		} else if (next instanceof ReplacedStructureEvent) {
			ReplacedStructureEvent event = (ReplacedStructureEvent) next;
			if (calcBuildingId == -1 && siteId == event.getSiteId())
				calcBuildingId = event.getOldAbId();
		}
	}

	@Override
	public String getShortDescription() {
		String civ = "";
		if (civId != -1)
			civ = " of " + World.getEntity(civId).getLink();
		String site = World.getSite(siteId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (hfId != -1)
			hf = World.getHistoricalFigure(hfId).getLink();
		String building = "UNKNOWN BUILDING";
		if (calcBuildingId != -1)
			building = World.getStructure(calcBuildingId, siteId).getLink();

		switch (linkType) {
		case "ruler":
		case "hangout":
			return hf + " stopped ruling from " + building + civ + " in " + site;
		case "seat_of_power":
			return hf + " stopped working at " + building + civ + " in " + site;
		case "home_site_realization_building":
			return hf + " moved out of " + building + civ + " in " + site;
		default:
			return hf + " unlinked (" + linkType + ") from site " + building + civ + " in " + site;
		}
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("ruler");
		linkTypes.remove("hangout");
		linkTypes.remove("seat_of_power");
		linkTypes.remove("home_site_realization_building");

		if (linkTypes.size() > 0)
			LOG.debug("unknown hf site link types: " + linkTypes);
	}

}
