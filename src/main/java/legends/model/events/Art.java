package legends.model.events;

import legends.xml.annotation.Xml;

public class Art {
	@Xml("art_id")
	private int id;
	@Xml("art_subid")
	private int subId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	@Override
	public String toString() {
		return id + " " + subId;
	}

	public String getLink() {
		return "<i>UNKNOWN IMAGE</i>";
	}

}
