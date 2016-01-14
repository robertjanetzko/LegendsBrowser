package legends.model;

import legends.xml.annotation.Xml;

public class EntityPopulation extends AbstractObject {
	@Xml("civ_id")private int civId;
	@Xml("race")private String race;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

}
