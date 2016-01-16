package legends.model.collections;

import legends.xml.annotation.Xml;

public class BattleCollectionSquad {
	@Xml("race")
	private String race;
	@Xml("entity_pop")
	private int entityPop = -1;
	@Xml("number")
	private int number = -1;
	@Xml("deaths")
	private int deaths = -1;
	@Xml("site")
	private int site = -1;

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public int getEntityPop() {
		return entityPop;
	}

	public void setEntityPop(int entityPop) {
		this.entityPop = entityPop;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getSite() {
		return site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return number + " " + race.toLowerCase() + "s (" + deaths + " died)";
	}

}