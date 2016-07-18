package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlAutoIncrement;

@XmlAutoIncrement
public class Structure extends AbstractObject {
	private int siteId;
	@Xml("type")
	private String type;
	@Xml("name")
	private String name;
	@Xml("name2")
	private String name2;

	@Xml(value = "inhabitant", elementClass = Integer.class, multiple = true)
	private List<Integer> inhabitantIds = new ArrayList<>();
	@Xml("deity")
	private int deityHfId = -1;
	@Xml("religion")
	private int religionEnId = -1;
	@Xml("dungeon_type")
	private int dungeonType = -1;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getType() {
		if ("dungeon".equals(type) && dungeonType != -1)
			return getDungeonType();
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public int getDeityHfId() {
		return deityHfId;
	}

	public void setDeityHfId(int deityHfId) {
		this.deityHfId = deityHfId;
	}

	public int getReligionEnId() {
		return religionEnId;
	}

	public void setReligionEnId(int religionEnId) {
		this.religionEnId = religionEnId;
	}

	public List<Integer> getInhabitantIds() {
		return inhabitantIds;
	}

	public String getDungeonType() {
		switch (dungeonType) {
		case 0:
			return "dungeon";
		case 1:
			return "sewers";
		case 2:
			return "catacombs";
		default:
			return "unknown dungeon type " + dungeonType;
		}
	}

	public void setDungeonType(int dungeonType) {
		this.dungeonType = dungeonType;
	}

	public String getURL() {
		return Application.getSubUri() + "/structure/" + (siteId * 100 + id);
	}

	public static String getGlyph(String type) {
		switch (type) {
		case "mead_hall":
			return "glyphicon glyphicon-home";
		case "inn_tavern":
			return "glyphicon glyphicon-cutlery";
		case "temple":
			return "fa fa-university";
		case "market":
			return "glyphicon glyphicon-apple";
		case "dungeon":
			return "glyphicon glyphicon-oil";
		case "keep":
			return "fa fa-fort-awesome";
		case "library":
			return "glyphicon glyphicon-book";
		case "underworld_spire":
			return "glyphicon glyphicon-tower";
		case "tomb":
			return "fa fa-stop-circle-o";

		default:
			return "";
		}
	}

	public String getIcon() {
		return "<span class=\"" + Structure.getGlyph(type) + "\" aria-hidden=\"true\"></span> ";
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN STRUCTURE</i>";
		return "<a href=\"" + getURL() + "\" class=\"structure\">" + getIcon() + getName() + "</a>";
	}

}
