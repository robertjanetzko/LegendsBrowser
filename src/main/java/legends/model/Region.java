package legends.model;

import legends.helper.EventHelper;

public class Region {
	private int id;
	private String name;
	private String type;

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
	
	@Override
	public String toString() {
		return "["+id+"] "+name+" ("+type+")";
	}
	
	public String getLink() {
		return "<a href=\"/region/" + id + "\" class=\"region\">" + getName() + "</a>";
	}

}
