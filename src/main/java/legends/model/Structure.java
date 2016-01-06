package legends.model;

import legends.helper.EventHelper;

public class Structure {
	private int id = -1;
	private int siteId;
	private String type;
	private String name;
	private String name2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getURL() {
		return "/structure/" + (siteId * 100 + id);
	}

	public static String getGlyph(String type) {
		switch (type) {
		case "mead_hall":
			return "glyphicon glyphicon-home";
		case "inn_tavern":
			return "glyphicon glyphicon-cutlery";
		case "temple":
			return "fa fa-university";
		case "market":
			return "glyphicon glyphicon-apple";
		case "dungeon":
			return "glyphicon glyphicon-oil";
		case "keep":
			return "fa fa-fort-awesome";
		case "library":
			return "glyphicon glyphicon-book";
		case "underworld_spire":
			return "glyphicon glyphicon-tower";
		case "tomb":
			return "fa fa-stop-circle-o";

		default:
			return "";
		}
	}

	public String getIcon() {
		return "<span class=\"" + Structure.getGlyph(type) + "\" aria-hidden=\"true\"></span> ";
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN STRUCTURE</i>";
		return "<a href=\"" + getURL() + "\" class=\"structure\">" + getIcon() + getName() + "</a>";
	}

}
