package legends.model;

import legends.xml.annotation.Xml;

public class Reference {
	@Xml("type")
	private String type;
	@Xml("id")
	private int id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
