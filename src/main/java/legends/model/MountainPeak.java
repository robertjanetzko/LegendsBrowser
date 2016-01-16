package legends.model;

import legends.model.events.basic.Coords;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordsConverter;

public class MountainPeak extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("coords")
	@XmlConverter(CoordsConverter.class)
	private Coords coords;
	@Xml("height")
	private int height;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getUrl() {
		return "/mountain/" + id;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN MOUNTAIN PEAK</i>";
		return "<a href=\"" + getUrl() + "\" class=\"mountain\">" + getName() + "</a>";
	}

}
