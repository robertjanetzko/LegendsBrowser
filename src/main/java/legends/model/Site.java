package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.model.events.CreatedSiteEvent;
import legends.model.events.DestroyedSiteEvent;
import legends.model.events.HfDestroyedSiteEvent;
import legends.model.events.basic.Coords;
import legends.model.events.basic.Event;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordsConverter;

public class Site extends AbstractObject {
	@Xml("name")
	private String name;

	@Xml("type")
	private String type = "";

	@Xml("coords")
	@XmlConverter(CoordsConverter.class)
	private Coords coords;

	private List<Structure> structures = new ArrayList<>();
	private List<SiteProperty> siteProperties = new ArrayList<>();
	private List<Population> populations = new ArrayList<>();

	@Xml("civ_id")
	private int civId = -1;
	@Xml("cur_owner_id")
	private int curOwnerId = -1;

	private List<Event> events = new ArrayList<>();

	private Entity owner;
	private boolean ruin = false;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		if(coords == null)
			return -1;
		return coords.getX();
	}

	public int getY() {
		if(coords == null)
			return -1;
		return coords.getY();
	}

	@Xml(value = "structures", element = "structure", elementClass = Structure.class)
	public void setStructures(List<Structure> structures) {
		this.structures = structures;
		for (int i = 0; i < structures.size(); i++) {
			Structure s = structures.get(i);
			s.setId(i);
			s.setSiteId(id);
		}
	}
	
	public List<Structure> getStructures() {
		return structures;
	}

	@Xml(value = "site_properties", element = "site_property", elementClass = SiteProperty.class)
	public void setSiteProperties(List<SiteProperty> properties) {
		this.siteProperties = properties;
		for (SiteProperty p: properties) {
			p.setSiteId(id);
		}
	}
	
	public List<SiteProperty> getSiteProperties() {
		return siteProperties;
	}

	public List<Population> getPopulations() {
		return populations;
	}

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getCurOwnerId() {
		return curOwnerId;
	}

	public void setCurOwnerId(int curOwnerId) {
		this.curOwnerId = curOwnerId;
	}

	public List<Event> getEvents() {
		return events;
	}

	public Entity getOwner() {
		return owner;
	}

	public void setOwner(Entity owner) {
		this.owner = owner;
		ruin = owner == null;
	}

	public boolean isRuin() {
		return ruin;
	}

	public void setRuin(boolean ruin) {
		this.ruin = ruin;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")";
	}

	public String getURL() {
		return Application.getSubUri() + "/site/" + id;
	}

	public static String getGlyph(String type) {
		switch (type) {
		case "forest retreat":
			return "glyphicon glyphicon-tree-deciduous";
		case "camp":
			return "glyphicon glyphicon-tent";
		case "tower":
		case "mountain halls":
			return "glyphicon glyphicon-tower";
		case "fortress":
		case "dark fortress":
		case "fort":
		case "castle":
			return "fa fa-fort-awesome";
		case "dark pits":
			return "glyphicon glyphicon-oil";
		case "hamlet":
		case "town":
		case "hillocks":
			return "glyphicon glyphicon-home";
		case "vault":
			return "glyphicon glyphicon-lock";
		case "shrine":
			return "glyphicon glyphicon-pawn";
		case "cave":
		case "lair":
			return "fa fa-square";
		case "tomb":
			return "fa fa-stop-circle-o";
		case "labyrinth":
			return "fa fa-gg";
		case "monastery":
			return "fa fa-university";

		default:
			return "";
		}
	}

	public String getIcon() {
		return "<span class=\"" + Site.getGlyph(type) + (isRuin() ? " ruin" : "") + "\" aria-hidden=\"true\"></span> ";
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"site\">" + getIcon() + getName() + "</a>";
	}

	public String getFounded() {
		;
		return events.stream().collect(Filters.filterEvent(CreatedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
			return e.getYear() + " by "
					+ World.getEntity(e.getSiteCivId() != -1 ? e.getSiteCivId() : e.getCivId()).getLink();
		}).findFirst().orElse("");
	}

	public String getDestroyed() {
		return events.stream().collect(Filters.filterEvent(DestroyedSiteEvent.class, e -> e.getSiteId() == id))
				.map(e -> {
					return e.getYear() + " by " + World.getEntity(e.getAttackerCivId()).getLink();
				}).findFirst().orElse(World.getHistoricalEvents().stream()
						.collect(Filters.filterEvent(HfDestroyedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
							return e.getYear() + " by " + World.getHistoricalFigure(e.getAttackerHfId()).getLink();
						}).findFirst().orElse(""));
	}

	public String getHistory() {
		return events.stream().map(e -> "In " + e.getYear() + ", " + e.getShortDescription())
				.collect(Collectors.joining("<br/>"));
	}
}
