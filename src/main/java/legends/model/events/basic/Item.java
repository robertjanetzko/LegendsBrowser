package legends.model.events.basic;

import legends.xml.annotation.Xml;

public class Item {
	@Xml("item")
	private int item;
	@Xml("mat")
	private String mat;
	@Xml("item_type")
	private String itemType;
	@Xml("item_subtype")
	private String itemSubType;

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
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

	public String getText(String prefix) {
		String s = "";
		if (mat != null) {
			s += " " + prefix + " " + mat + " ";
			if (itemSubType != null)
				s += itemSubType;
			else
				s += itemType;
		}
		return s;
	}

}
