package legends.model;

import legends.helper.EventHelper;

public class Entity {
	private int id;
	private String name;

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

	@Override
	public String toString() {
		return "[" + id + "] " + getName();
	}

	public String getLink() {
		return "<a href=\"/entity/" + id + "\" class=\"entity\">" + getName() + "</a>";
	}

}
