package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.Entity;
import legends.model.EntityPositionLink;
import legends.model.Structure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("add hf site link")
public class AddHfSiteLinkEvent extends HfEvent
		implements HfRelatedEvent, SiteRelatedEvent, StructureRelatedEvent, EntityRelatedEvent {
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
		boolean setBuilding = false;

		Event prev = World.getHistoricalEvent(getId() - 1);

		if (prev instanceof AddHfEntityLinkEvent) {
			AddHfEntityLinkEvent event = (AddHfEntityLinkEvent) prev;
			if (hfId == -1) {
				Entity entity = World.getEntity(event.getCivId());
				for (EntityPositionLink l : entity.getHfPositions().keySet())
					if (l.getStartYear() == year) {
						hfId = entity.getHfPositions().get(l);
						linkType = "ruler";
						World.getSite(siteId).getStructures().stream().filter(Structure::canRuleFrom)
								.filter(s -> s.getConstructionYear() <= year).forEach(s -> calcBuildingId = s.getId());
						setBuilding = true;
					}
			}
		}

		while (prev instanceof AddHfSiteLinkEvent || prev instanceof AddHfEntityLinkEvent
				|| prev instanceof EntityPrimaryCriminalsEvent)
			prev = World.getHistoricalEvent(prev.getId() - 1);
		if (prev instanceof CreatedStructureEvent) {
			CreatedStructureEvent event = (CreatedStructureEvent) prev;
			if ((calcBuildingId == -1 || setBuilding) && siteId == event.getSiteId())
				calcBuildingId = event.getStructureId();
			if (hfId == -1) {
				Entity entity = World.getEntity(event.getSiteCivId());
				for (EntityPositionLink l : entity.getHfPositions().keySet())
					if (l.getStartYear() <= year && (year <= l.getEndYear() || l.getEndYear() == -1)) {
						hfId = entity.getHfPositions().get(l);
						linkType = "ruler";
					}
			}
		} else if (prev instanceof EntityCreatedEvent) {
			EntityCreatedEvent event = (EntityCreatedEvent) prev;
			if ((calcBuildingId == -1 || setBuilding) && siteId == event.getSiteId())
				calcBuildingId = event.getStructureId();
			Entity entity = World.getEntity(civId);
			if (hfId == -1)
				for (EntityPositionLink l : entity.getHfPositions().keySet())
					if (l.getStartYear() == year) {
						hfId = entity.getHfPositions().get(l);
						linkType = "ruler";
					}

		} else if (prev instanceof ChangeHfStateEvent) {
			ChangeHfStateEvent event = (ChangeHfStateEvent) prev;
			if (hfId == -1 && siteId == event.getLocation().getSiteId())
				hfId = event.getHfId();
		}

		Event next = World.getHistoricalEvent(getId() + 1);
		while (next instanceof AddHfSiteLinkEvent)
			next = World.getHistoricalEvent(next.getId() + 1);

		if (next instanceof ReplacedStructureEvent) {
			ReplacedStructureEvent event = (ReplacedStructureEvent) next;
			calcBuildingId = event.getNewAbId();
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
			return hf + " ruled from " + building + civ + " in " + site;
		case "seat_of_power":
			if (calcBuildingId != -1 && World.getStructure(calcBuildingId, siteId).canRuleFrom())
				return hf + " ruled from " + building + civ + " in " + site;
			return hf + " started working at " + building + civ + " in " + site;
		case "occupation":
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
