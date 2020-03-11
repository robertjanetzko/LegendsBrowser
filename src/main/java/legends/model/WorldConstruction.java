package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.model.events.basic.Coords;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordListConverter;

public class WorldConstruction extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("type")
	private String type;
	@Xml("coords")
	@XmlConverter(CoordListConverter.class)
	private List<Coords> coords = new ArrayList<>();

	private List<Site> sites = new ArrayList<>();
	private List<WorldConstruction> parts = new ArrayList<>();
	private WorldConstruction master;

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

	public String getColor() {
		switch (type) {
		case "road":
			return "#FFF";
		case "bridge":
			return "#FFF";
		case "tunnel":
			return "#000";

		default:
			return "#FFF";
		}
	}

	public String getURL() {
		return Application.getSubUri() + "/worldconstruction/" + id;
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
	
	public String getMapDescription() {
		String description = getLink() + "<br><span>" + type;
		switch (type) {
		case "road":
		case "tunnel":
			description += " connecting " + getSites().stream().map(site -> site.getLink()).collect(EventHelper.stringList());
			break;
		case "bridge":
			description += " on " + getMaster().getLink();
			break;
		}
		return description + "</span>";
	}

}
