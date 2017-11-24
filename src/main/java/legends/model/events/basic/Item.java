package legends.model.events.basic;

import legends.xml.annotation.Xml;

public class Item {
	@Xml("item,item_id")
	private int item = -1;
	@Xml("mat,item_mat")
	private String mat;
	@Xml("item_type")
	private String itemType;
	@Xml("item_subtype")
	private String itemSubType;
	@Xml("mattype,mat_type,item_mat_type")
	private int matType = -1;
	@Xml("matindex,item_mat_index,mat_index")
	private int matIndex = -1;
	@Xml("dye_mat_type")
	private int dyeMatType = -1;

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

	public String getText() {
		return getText(null);
	}

	public String getText(String prefix) {
		String s = "";
		if (mat != null) {
			if (prefix != null)
				s += " " + prefix + " ";
			s += mat + " ";
			if (itemSubType != null && !itemSubType.equals("-1"))
				s += itemSubType;
			else
				s += itemType;
		} else {
			if (itemSubType != null && !itemSubType.equals("-1"))
				s += itemSubType;
			else if (itemType != null && !itemType.equals("-1"))
				s += itemType;
		}
		if (s.isEmpty())
			s = "";

		return s;
	}

}
