package legends.model;

import legends.xml.annotation.Xml;

public class Reference {
	@Xml("type")
	private String type;
	@Xml("id")
	private int id = -1;
	@Xml("value")
	private String value;
	@Xml("level")
	private int level;
	@Xml("text")
	private String text;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
