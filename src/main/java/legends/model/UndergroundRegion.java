package legends.model;

import legends.xml.annotation.Xml;

public class UndergroundRegion extends AbstractObject {
	@Xml("depth")
	private int depth;
	
	@Xml("type")
	private String type;

	@Xml("coords")
	private String coords;

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + depth + " (" + type + ")";
	}

}
