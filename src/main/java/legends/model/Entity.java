package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.helper.EventHelper;
import legends.model.collections.WarCollection;

public class Entity {
	private int id = -1;
	private String name;

	private String race = "unknown";
	private String type = "unknown";
	private List<Site> sites = new ArrayList<>();
	private Entity parent;
	private List<Leader> leaders = new ArrayList<>();
	private String color = "#F0F";
	private List<Integer> children = new ArrayList<>();
	private List<EntityLink> entityLinks = new ArrayList<>();

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

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity parent) {
		this.parent = parent;
	}

	public List<Site> getSites() {
		return sites;
	}

	public List<Leader> getLeaders() {
		return leaders;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getChildren() {
		return children;
	}

	public List<EntityLink> getEntityLinks() {
		return entityLinks;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + getName();
	}

	public String getURL() {
		return "/entity/" + id;
	}
	
	public static String getGlyph(String type) {
		switch (type) {
		case "sitegovernment":
			return "fa fa-balance-scale";
		case "outcast":
			return "glyphicon glyphicon-tent";
		case "nomadicgroup":
			return "glyphicon glyphicon-tree-deciduous";
		case "religion":
			return "fa fa-university";
		case "performancetroupe":
			return "glyphicon glyphicon-cd";
		case "migratinggroup":
			return "glyphicon glyphicon-transfer";

		case "civilization":
		default:
			return "glyphicon glyphicon-asterisk";
		}
	}
	
	private String getIcon() {
		return "<span class=\""+Entity.getGlyph(type)+"\" style=\"color: "+getColor()+"\" aria-hidden=\"true\"></span> ";
	}
	
	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN ENTITY</i>";

		return "<a href=\"" + getURL() + "\" class=\"entity\">"+getIcon() + getName() + "</a>";
	}

	public List<Entity> getWars() {
		return World.getHistoricalEventCollections().stream().filter(e -> e instanceof WarCollection)
				.map(e -> (WarCollection) e)
				.filter(e -> e.getAggressorEntId() == getId() || e.getDefenderEntId() == getId()).map(e -> {
					if (e.getAggressorEntId() == getId())
						return e.getDefenderEntId();
					else
						return e.getAggressorEntId();
				}).map(World::getEntity).collect(Collectors.toList());

	}

}
