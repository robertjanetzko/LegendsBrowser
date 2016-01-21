package legends.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class Schedule extends AbstractObject {
	private String type = "";
	@Xml("reference")
	private int reference;
	@Xml("reference2")
	private int reference2;
	@Xml(value = "feature", elementClass = ScheduleFeature.class, multiple = true)
	private List<ScheduleFeature> features = new ArrayList<>();
	@Xml("item_type")
	private String itemType;
	@Xml("item_subtype")
	private String itemSubType;

	public String getType() {
		String t = type.replace("_", " ");
		switch (t) {
		case "throwing competition":
			return itemSubType + " " + t;
		default:
			return t;
		}
	}

	private static Set<String> types = new HashSet<>();
	
	@Xml("type")
	public void setType(String type) {
		if(!types.contains(type)) {
			System.out.println("schedule "+type);
			types.add(type);
		}
		this.type = type;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public int getReference2() {
		return reference2;
	}

	public void setReference2(int reference2) {
		this.reference2 = reference2;
	}

	public List<ScheduleFeature> getFeatures() {
		return features;
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

}
