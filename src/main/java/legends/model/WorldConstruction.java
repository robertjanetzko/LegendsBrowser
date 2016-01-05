package legends.model;

public class WorldConstruction {
	private int id = -1;
	private String name;
	private String type;
	private String coords;

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

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN WORLD CONSTRUCTION</i>";
		return "<a href=\"/worldconstruction/" + id + "\" class=\"worldconstruction\">" + getName() + "</a>";

	}

}
