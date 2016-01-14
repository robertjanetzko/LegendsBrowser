package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("creature devoured")
public class CreatureDevouredEvent extends Event implements LocalEvent, HfRelatedEvent {
	@XmlComponent
	private EventLocation location = new EventLocation();

	@Xml("victim")
	private int calcDevouredHfId = -1;
	@Xml("eater")
	private int calcSlayerHfId = -1;

	@Xml("race")
	private String race;
	@Xml("caste")
	private String caste;
	@Xml("entity")
	private int entity = -1;

	public int getCalcDevouredHfId() {
		return calcDevouredHfId;
	}

	public void setCalcDevouredHfId(int calcDevouredHfId) {
		this.calcDevouredHfId = calcDevouredHfId;
	}

	public int getCalcSlayerHfId() {
		return calcSlayerHfId;
	}

	public void setCalcSlayerHfId(int calcSlayerHfId) {
		this.calcSlayerHfId = calcSlayerHfId;
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

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcDevouredHfId == hfId || calcSlayerHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String civ = "";
		if (entity != -1)
			civ = " of " + World.getEntity(entity).getLink();
		String devoured = "UNKNOWN HISTORICAL FIGURE";
		if (calcDevouredHfId != -1)
			devoured = World.getHistoricalFigure(calcDevouredHfId).getLink();
		else if (race != null)
			devoured = "a " + race;
		String slayer = "UNKNOWN HISTORICAL FIGURE";
		if (calcSlayerHfId != -1)
			slayer = World.getHistoricalFigure(calcSlayerHfId).getLink();
		String loc = location.getLink("in");
		return slayer + " devoured " + devoured + civ + loc;
	}

}
