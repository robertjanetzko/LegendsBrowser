package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class ArtForm extends AbstractObject {
	@Xml("name")
	protected String name;
	@Xml("origin")
	protected int originEnId = -1;
	@Xml("description")
	protected String description;

	public String getName() {
		if(name == null && description != null && description.indexOf(" is a ") > 0)
			return description.substring(0, description.indexOf(" is a "));
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

	public String getType() {
		return "UNKNOWN ART FORM";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
