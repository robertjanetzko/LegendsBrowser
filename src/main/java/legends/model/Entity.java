package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;

public class Entity {
	private int id;
	private String name;

	private List<Site> sites = new ArrayList<>();
	
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
	
	public List<Site> getSites() {
		return sites;
	}


	@Override
	public String toString() {
		return "[" + id + "] " + getName();
	}

	public String getLink() {
		return "<a href=\"/entity/" + id + "\" class=\"entity\">" + getName() + "</a>";
	}


}
