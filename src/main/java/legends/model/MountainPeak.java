package legends.model;

import java.util.Arrays;
import java.util.List;

import legends.Application;
import legends.model.basic.AbstractObject;
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
	@Xml("is_volcano")
	private boolean isVolcano;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Coords> getCoords() {
		return Arrays.asList(coords);
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

	public boolean getIsVolcano() {
		return isVolcano;
	}

	public void setIsVolcano(boolean isVolcano) {
		this.isVolcano = isVolcano;
	}

	public String getUrl() {
		return Application.getSubUri() + "/mountain/" + id;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN MOUNTAIN PEAK</i>";
		String name = getName();
		if (getIsVolcano()) {
			name += " (volcano)";
		}
		return "<a href=\"" + getUrl() + "\" class=\"mountain\">" + name + "</a>";
	}
	
	public String getMapDescription() {
		return getLink() + "<br/><span>height: " + height + "</span>";
	}

}
