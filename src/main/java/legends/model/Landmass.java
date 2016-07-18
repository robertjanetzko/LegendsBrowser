package legends.model;

import legends.Application;
import legends.model.basic.AbstractObject;
import legends.model.events.basic.Coords;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordsConverter;

public class Landmass extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("coord_1")
	@XmlConverter(CoordsConverter.class)
	private Coords coord1;
	@Xml("coord_2")
	@XmlConverter(CoordsConverter.class)
	private Coords coord2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coords getCoord1() {
		return coord1;
	}

	public void setCoord1(Coords coord1) {
		this.coord1 = coord1;
	}

	public Coords getCoord2() {
		return coord2;
	}

	public void setCoord2(Coords coord2) {
		this.coord2 = coord2;
	}

	public String getUrl() {
		return Application.getSubUri() + "/landmass/" + id;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN LANDMASS</i>";
		return "<a href=\"" + getUrl() + "\" class=\"landmass\">" + getName() + "</a>";
	}
}
