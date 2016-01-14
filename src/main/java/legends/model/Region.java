package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.events.basic.Coords;
import legends.xml.annotation.XmlConverter;
import legends.xml.annotation.Xml;
import legends.xml.converter.CoordListConverter;

public class Region extends AbstractObject {
	@Xml("name")
	private String name;

	@Xml("type")
	private String type;

	@Xml("coords")
	@XmlConverter(CoordListConverter.class)
	private List<Coords> coords = new ArrayList<>();

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
