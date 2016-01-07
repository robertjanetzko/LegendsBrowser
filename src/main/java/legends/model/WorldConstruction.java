package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.model.events.basic.Coords;

public class WorldConstruction {
	private int id = -1;
	private String name;
	private String type;
	private List<Coords> coords = new ArrayList<>();

	private List<Site> sites = new ArrayList<>();
	private List<WorldConstruction> parts = new ArrayList<>();
	private WorldConstruction master;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
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

	public List<Site> getSites() {
		return sites;
	}

	public List<WorldConstruction> getParts() {
		return parts;
	}

	public WorldConstruction getMaster() {
		return master;
	}

	public void setMaster(WorldConstruction master) {
		this.master = master;
	}

	public String getURL() {
		return "/worldconstruction/" + id;
	}

	public static String getGlyph(String type) {
		switch (type) {
		case "road":
			return "glyphicon glyphicon-road";
		case "bridge":
			return "fa fa-chevron-up";
		case "tunnel":
			return "glyphicon glyphicon-minus-sign";

		default:
			return "fa fa-share-alt";
		}
	}

	public String getIcon() {
		return "<span class=\"" + WorldConstruction.getGlyph(type) + "\" aria-hidden=\"true\"></span> ";
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN WORLD CONSTRUCTION</i>";
		return "<a href=\"" + getURL() + "\" class=\"worldconstruction\">" + getIcon() + getName() + "</a>";

	}

}
