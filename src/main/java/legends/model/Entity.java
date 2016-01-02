package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;

public class Entity {
	private int id;
	private String name;

	private String race = "unknown";
	private List<Site> sites = new ArrayList<>();
	private Entity parent;
	private List<Leader> leaders = new ArrayList<>();

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

	@Override
	public String toString() {
		return "[" + id + "] " + getName();
	}

	public String getLink() {
		return "<a href=\"/entity/" + id + "\" class=\"entity\">" + getName() + "</a>";
	}

}
