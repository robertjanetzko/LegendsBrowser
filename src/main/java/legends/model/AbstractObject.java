package legends.model;

import legends.xml.annotation.Xml;

public class AbstractObject {
	@Xml("id")
	protected int id = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
