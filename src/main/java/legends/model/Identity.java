package legends.model;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class Identity extends AbstractObject {
	@Xml("name")
	private String name = "UNKNOWN IDENTITY";
	@Xml("race")
	private String race;
	@Xml("caste")
	private String caste;
	@Xml("histfig_id")
	private int hfId = -1;
	@Xml("birth_year")
	private int birthYear = -1;
	@Xml("birth_second")
	private int birthSeconds = -1;
	@Xml("profession")
	private String profession;
	@Xml("entity_id")
	private int entityId = -1;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthSeconds() {
		return birthSeconds;
	}

	public void setBirthSeconds(int birthSeconds) {
		this.birthSeconds = birthSeconds;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getURL() {
		return Application.getSubUri() + "/id/" + id;
	}

	public String getLink() {
		final String prof = profession != null ? "the " + profession + " " : "";
		final String entity = entityId >= 0 ? " of " + World.getEntity(entityId).getLink() : "";
		return prof + "<a href=\"" + getURL() + "\" class=\"identity\">" + getName() + "</a>" + entity;
	}

}
