package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("add hf site link")
public class AddHfSiteLinkEvent extends Event
		implements HfRelatedEvent, SiteRelatedEvent, StructureRelatedEvent, EntityRelatedEvent {
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("histfig")
	private int calcHfId = -1;
	@Xml("structure")
	private int calcBuildingId = -1;
	@Xml("link_type")
	private String linkType = "";
	@Xml("civ")
	private int civId = -1;

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
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId;
	}

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
	public String getShortDescription() {
		String civ = "";
		if (civId != -1)
			civ = " of " + World.getEntity(civId).getLink();
		String site = World.getSite(siteId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		String building = "UNKNOWN BUILDING";
		if (calcBuildingId != -1)
			building = World.getStructure(calcBuildingId, siteId).getLink();

		switch (linkType) {
		case "ruler":
		case "hangout":
			return hf + " ruled from " + building + civ + " in " + site;
		case "seat_of_power":
			return hf + " started working at " + building + civ + " in " + site;
		case "home_site_realization_building":
			return hf + " took up residence in " + building + civ + " in " + site;
		default:
			return hf + " linked (" + linkType + ") to site " + building + civ + " in " + site;
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
