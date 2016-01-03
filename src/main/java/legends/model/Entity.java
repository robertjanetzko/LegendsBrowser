package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.helper.EventHelper;
import legends.model.collections.WarCollection;

public class Entity {
	private int id;
	private String name;

	private String race = "unknown";
	private List<Site> sites = new ArrayList<>();
	private Entity parent;
	private List<Leader> leaders = new ArrayList<>();
	private String color = "#F0F";

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

	public String getLink() {
		return "<a href=\"/entity/" + id + "\" class=\"entity\">" + getName() + "</a>";
	}

	public List<Entity> getWars() {
		return World.getHistoricalEventCollections().stream().filter(e -> e instanceof WarCollection)
				.map(e -> (WarCollection) e).filter(e-> e.getAggressorEntId()==getId() || e.getDefenderEntId()==getId()).map(e -> {
					if (e.getAggressorEntId() == getId())
						return e.getDefenderEntId();
					else 
						return e.getAggressorEntId();
				}).map(World::getEntity).collect(Collectors.toList());

	}

}
