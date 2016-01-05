package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class AddHfEntityLinkEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	private int civId = -1;

	private int calcHfId = -1;
	private String calcLinkType = "";
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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "civ_id":
		case "civ":
			setCivId(Integer.parseInt(value));
			break;

		case "histfig":
			setCalcHfId(Integer.parseInt(value));
			break;
		case "link_type":
			linkTypes.add(value);
			setCalcLinkType(value);
			break;
		case "position":
			setPosition(value);
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
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public void process() {
		Event prev = World.getHistoricalEvent(getId() - 1);
		if (prev instanceof CreatedStructureEvent) {
			CreatedStructureEvent event = (CreatedStructureEvent) prev;
			if (getCalcHfId() == -1) {
				setCalcHfId(event.getBuilderHfId());
				setCalcLinkType("master");
			}
		}
	}

	public String getDescription() {
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
			return hf + " became " + position + " of " + civ;
		case "member":
			return hf + " became a member of " + civ;
		case "slave":
			return hf + " was enslaved by " + civ;
		default:
			return hf + " linked (" + calcLinkType + ") to " + civ;
		}
	}

	@Override
	public String getShortDescription() {
		// String info = "<ul>";
		// Event prev = World.getHistoricalEvent(getId() - 1);
		// if (prev != null)
		// info += "<li>after: " + prev.getDescription()+"</li>";
		// Event next = World.getHistoricalEvent(getId() + 1);
		// if (next != null)
		// info += "<li>before: " + next.getDescription()+"</li>";
		// info += "</ul>";
		return getDescription();// +info;
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("enemy");
		linkTypes.remove("prisoner");
		linkTypes.remove("ruler");
		linkTypes.remove("master");
		linkTypes.remove("position");
		linkTypes.remove("member");
		
		if (linkTypes.size() > 0)
			System.out.println("unknown hf entity link types: " + linkTypes);
	}

}
