package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.events.basic.Coords;

public class Region {
	private int id;
	private String name;
	private String type;

	private List<Coords> coords = new ArrayList<>();

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

	public List<Coords> getCoords() {
		return coords;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")";
	}

	public String getURL() {
		return "/region/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"region\">" + getName() + "</a>";
	}

}
