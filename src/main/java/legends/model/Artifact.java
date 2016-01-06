package legends.model;

import legends.helper.EventHelper;

public class Artifact {
	private int id;
	private String name;
	private String item;

	private String mat;
	private String itemType;
	private String itemSubType;
	private String itemDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMat() {
		return mat;
	}

	public void setMat(String mat) {
		this.mat = mat;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemSubType() {
		return itemSubType;
	}

	public void setItemSubType(String itemSubType) {
		this.itemSubType = itemSubType;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + item + ")";
	}

	public String getURL() {
		return "/artifact/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"artifact\">" + getName() + "</a>";
	}

}
