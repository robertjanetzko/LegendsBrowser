package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class ArtForm extends AbstractObject {
	@Xml("name")
	protected String name;
	@Xml("origin")
	protected int originEnId = -1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOriginEnId() {
		return originEnId;
	}

	public void setOriginEnId(int originEnId) {
		this.originEnId = originEnId;
	}

}
