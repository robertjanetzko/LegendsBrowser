package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class BodyAbusedEvent extends Event implements EntityRelatedEvent, HfRelatedEvent, LocalEvent {
	private EventLocation location = new EventLocation("");

	private List<Integer> bodies = new ArrayList<>();
	private int civId = -1;
	private int hfId = -1;
	private int abuseType = -1;
	private String propsItemType;
	private String propsItemSubType;
	private String propsItemMat;
	private int propsItemMatType = -1;
	private int propsItemMatIndex = -1;
	private int propsPileType = -1;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public int getAbuseType() {
		return abuseType;
	}

	public void setAbuseType(int abuseType) {
		this.abuseType = abuseType;
	}

	public String getPropsItemType() {
		return propsItemType;
	}

	public void setPropsItemType(String propsItemType) {
		this.propsItemType = propsItemType;
	}

	public String getPropsItemSubType() {
		return propsItemSubType;
	}

	public void setPropsItemSubType(String propsItemSubType) {
		this.propsItemSubType = propsItemSubType;
	}

	public String getPropsItemMat() {
		return propsItemMat;
	}

	public void setPropsItemMat(String propsItemMat) {
		this.propsItemMat = propsItemMat;
	}

	public int getPropsItemMatType() {
		return propsItemMatType;
	}

	public void setPropsItemMatType(int propsItemMatType) {
		this.propsItemMatType = propsItemMatType;
	}

	public int getPropsItemMatIndex() {
		return propsItemMatIndex;
	}

	public void setPropsItemMatIndex(int propsItemMatIndex) {
		this.propsItemMatIndex = propsItemMatIndex;
	}

	public int getPropsPileType() {
		return propsPileType;
	}

	public void setPropsPileType(int propsPileType) {
		this.propsPileType = propsPileType;
	}

	public List<Integer> getBodies() {
		return bodies;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "bodies":
			getBodies().add(Integer.parseInt(value));
			break;
		case "civ":
			setCivId(Integer.parseInt(value));
			break;
		case "histfig":
			setHfId(Integer.parseInt(value));
			break;
		case "abuse_type":
			setAbuseType(Integer.parseInt(value));
			break;
		case "props_pile_type":
			setPropsPileType(Integer.parseInt(value));
			break;
		case "props_item_type":
			setPropsItemType(value);
			break;
		case "props_item_subtype":
			setPropsItemSubType(value);
			break;
		case "props_item_mat":
			setPropsItemMat(value);
			break;
		case "props_item_mat_type":
			setPropsItemMatType(Integer.parseInt(value));
			break;
		case "props_item_mat_index":
			setPropsItemMatIndex(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}
	
	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}
	
	@Override
	public boolean isRelatedToHf(int hfId) {
		return bodies.contains(hfId) || this.hfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		String body = bodies.stream().map(World::getHistoricalFigure).collect(EventHelper.hfList());

		String item = "UNKNOWN ITEM";
		if (propsItemSubType != null)
			item = propsItemSubType;
		else if (propsItemType != null)
			item = propsItemType;
		if (propsItemMat != null)
			item = propsItemMat + " " + item;

		String s1 = "body";
		String s2 = " was";
		if (bodies.size() > 1) {
			s1 = "bodies";
			s2 = " were";
		}

		switch (abuseType) {
		case 0:
			return "the " + s1 + " of " + body + s2 + " impaled on " + item + " by " + civ + location.getLink("in");
		case 1:
			return "the " + s1 + " of " + body + s2 + " added to a gruesome sculpture by " + civ
					+ location.getLink("in");
		case 3:
			return "the " + s1 + " of " + body + s2 + " hung from a tree by " + civ + location.getLink("in");
		case 4:
			return "the " + s1 + " of " + body + s2 + " horribly mutilated by " + civ + location.getLink("in");
		case 5:
			return "the " + s1 + " of " + body + s2 + " animated by " + hf + location.getLink("in");
		default:
		}

		return body + " by " + civ + location.getLink("in") + " == abuse: " + abuseType + " == pile: " + propsPileType
				+ " itemType: " + propsItemType + " sub: " + propsItemSubType;
	}

}
