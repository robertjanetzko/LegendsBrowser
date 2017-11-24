package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.EntityPositionLink;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("add hf entity link")
public class AddHfEntityLinkEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	@Xml("civ,civ_id")
	private int civId = -1;
	@Xml("histfig")
	private int calcHfId = -1;
	@Xml("link_type")
	private String calcLinkType = "";
	@Xml("position")
	private String position;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private static Set<String> linkTypes = new HashSet<>();

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public void process() {
		Event prev = World.getHistoricalEvent(getId() - 1);
		while (prev instanceof AddHfEntityLinkEvent || prev instanceof AddHfSiteLinkEvent
				|| prev instanceof EntityPrimaryCriminalsEvent)
			prev = World.getHistoricalEvent(prev.getId() - 1);
		if (prev instanceof CreatedStructureEvent) {
			CreatedStructureEvent event = (CreatedStructureEvent) prev;
			if (getCalcHfId() == -1 && event.getCivId() == civId) {
				setCalcHfId(event.getBuilderHfId());
				setCalcLinkType("master");
			}
		} else if (prev instanceof ChangeHfStateEvent) {
			ChangeHfStateEvent event = (ChangeHfStateEvent) prev;
			if (getCalcHfId() == -1) {
				setCalcLinkType("member");
				setCalcHfId(event.getHfId());
			}
		} else {
			Entity entity = World.getEntity(civId);
			if (calcHfId == -1)
				for (EntityPositionLink l : entity.getHfPositions().keySet())
					if (l.getStartYear() == year) {
						calcHfId = entity.getHfPositions().get(l);
						calcLinkType = "ruler";
					}
		}
	}

	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();

		switch (calcLinkType) {
		case "enemy":
			return hf + " became an enemy of " + civ;
		case "prisoner":
			return hf + " was imprisoned by " + civ;
		case "ruler":
			return hf + " became ruler of " + civ;
		case "master":
			return hf + " became master of " + civ;
		case "position":
			return hf + " became " + EventHelper.fixPositionGender(position, World.getHistoricalFigure(calcHfId),
					World.getEntity(civId)) + " of " + civ;
		case "member":
			return hf + " became a member of " + civ;
		case "slave":
			return hf + " was enslaved by " + civ;
		default:
			if (calcLinkType.equals(""))
				return hf + " linked to " + civ;
			else
				return hf + " linked (" + calcLinkType + ") to " + civ;
		}
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("enemy");
		linkTypes.remove("prisoner");
		linkTypes.remove("ruler");
		linkTypes.remove("master");
		linkTypes.remove("position");
		linkTypes.remove("member");
		linkTypes.remove("slave");

		if (linkTypes.size() > 0)
			LOG.debug("unknown hf entity link types: " + linkTypes);
	}

}
