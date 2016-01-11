package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.helper.EventHelper;
import legends.model.events.CreatedSiteEvent;
import legends.model.events.DestroyedSiteEvent;
import legends.model.events.HfDestroyedSiteEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.Filters;

public class Site {
	private int id;
	private String name;
	private String type;
	private int x;
	private int y;

	private List<Structure> structures = new ArrayList<>();

	private List<Population> populations = new ArrayList<>();

	private int civId = -1;
	private int curOwnerId = -1;

	private List<Event> events = new ArrayList<>();

	private Entity owner;
	private boolean ruin = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setStructures(List<Structure> structures) {
		for (Structure s : structures)
			s.setSiteId(id);
		this.structures = structures;
	}

	public List<Structure> getStructures() {
		return structures;
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
		return "[" + id + "] " + name + " (" + type + ")" + " " + x + "," + y;
	}

	public String getURL() {
		return "/site/" + id;
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
		return events.stream().collect(Filters.get(CreatedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
			return e.getYear() + " by "
					+ World.getEntity(e.getSiteCivId() != -1 ? e.getSiteCivId() : e.getCivId()).getLink();
		}).findFirst().orElse("");
	}

	public String getDestroyed() {
		return events.stream().collect(Filters.get(DestroyedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
			return e.getYear() + " by " + World.getEntity(e.getAttackerCivId()).getLink();
		}).findFirst().orElse(World.getHistoricalEvents().stream()
				.collect(Filters.get(HfDestroyedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
					return e.getYear() + " by " + World.getHistoricalFigure(e.getAttackerHfId()).getLink();
				}).findFirst().orElse(""));
	}

	public String getHistory() {
		return events.stream().map(e -> "In " + e.getYear() + ", " + e.getShortDescription())
				.collect(Collectors.joining("<br/>"));
	}
}
