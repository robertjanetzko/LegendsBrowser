package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class EntityPosition extends AbstractObject {
	@Xml("name")
	private String name = "UNKNOWN POSITION";
	@Xml("name_male")
	private String nameMale;
	@Xml("name_female")
	private String nameFemale;
	@Xml("spouse")
	private String spouse;
	@Xml("spouse_male")
	private String spouseMale;
	@Xml("spouse_female")
	private String spouseFemale;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameMale() {
		return nameMale;
	}

	public void setNameMale(String nameMale) {
		this.nameMale = nameMale;
	}

	public String getNameFemale() {
		return nameFemale;
	}

	public void setNameFemale(String nameFemale) {
		this.nameFemale = nameFemale;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getSpouseMale() {
		return spouseMale;
	}

	public void setSpouseMale(String spouseMale) {
		this.spouseMale = spouseMale;
	}

	public String getSpouseFemale() {
		return spouseFemale;
	}

	public void setSpouseFemale(String spouseFemale) {
		this.spouseFemale = spouseFemale;
	}

}
