package legends.model.basic;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class NamedObject extends AbstractObject {
	@Xml("name")
	protected String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
