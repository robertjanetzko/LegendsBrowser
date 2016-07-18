package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class Artifact extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("item")
	private String item;

	@Xml("mat")
	private String mat;
	@Xml("item_type")
	private String itemType;
	@Xml("item_subtype")
	private String itemSubType;
	@Xml("item_description")
	private String itemDescription;

	@Xml("page_count")
	private int pageCount = -1;
	@Xml(value = "writing", elementClass = Integer.class, multiple = true)
	private List<Integer> writtenContent = new ArrayList<>();

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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Integer> getWrittenContent() {
		return writtenContent;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + item + ")";
	}

	public static String getGlyph(String type) {
		if(type == null)
			return "";
		switch (type) {
		case "book":
		case "quire":
			return "glyphicon glyphicon-book";
		case "scroll":
			return "glyphicon glyphicon-file";
		case "slab":
			return "fa fa-square";

		default:
			return "";
		}
	}

	public String getIcon() {
		return "<span class=\"" + Artifact.getGlyph(itemSubType==null?itemType:itemSubType) + "\" aria-hidden=\"true\"></span> ";
	}

	public String getURL() {
		return Application.getSubUri() + "/artifact/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"artifact\">" + getIcon() + getName() + "</a>";
	}

}
