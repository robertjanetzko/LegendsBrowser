package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;

public class Site {
	private int id;
	private String name;
	private String type;
	private int x;
	private int y;

	private List<Structure> structures = new ArrayList<>();

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
		this.structures = structures;
	}

	public List<Structure> getStructures() {
		return structures;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")" + " " + x + "," + y;
	}

	public String getLink() {
		return "<a href=\"/site/" + id + "\" class=\"site\">" + getName() + "</a>";
	}
}
