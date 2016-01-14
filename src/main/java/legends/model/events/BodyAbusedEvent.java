package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.Item;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("body abused")
public class BodyAbusedEvent extends HfEvent implements EntityRelatedEvent, HfRelatedEvent, LocalEvent {
	@Xml(value = "bodies", elementClass = Integer.class, multiple = true)
	private List<Integer> bodies = new ArrayList<>();
	@Xml("civ")
	private int civId = -1;
	@Xml("abuse_type")
	private int abuseType = -1;
	@XmlComponent(prefix = "props_")
	private Item item = new Item();
	@Xml("props_pile_type")
	private int pileType = -1;
	@XmlComponent
	private EventLocation location = new EventLocation("");

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getAbuseType() {
		return abuseType;
	}

	public void setAbuseType(int abuseType) {
		this.abuseType = abuseType;
	}

	public int getPileType() {
		return pileType;
	}

	public void setPileType(int pileType) {
		this.pileType = pileType;
	}

	public List<Integer> getBodies() {
		return bodies;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return bodies.contains(hfId) || this.hfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		String body = bodies.stream().map(World::getHistoricalFigure).collect(EventHelper.hfList());

		String item = this.item.getText();

		String s1 = "body";
		String s2 = " was";
		if (bodies.size() > 1) {
			s1 = "bodies";
			s2 = " were";
		}

		switch (abuseType) {
		case -1:
			return "the " + s1 + " of " + body + s2 + " impaled on " + item + " by " + civ + location.getLink("in");
		case 0:
			return "the " + s1 + " of " + body + s2 + " impaled on " + item + " by " + civ + location.getLink("in");
		case 1:
			return "the " + s1 + " of " + body + s2 + " added to a gruesome sculpture by " + civ
					+ location.getLink("in");
		case 3:
			return "the " + s1 + " of " + body + s2 + " hung from a tree by " + civ + location.getLink("in");
		case 4:
			return "the " + s1 + " of " + body + s2 + " horribly mutilated by " + civ + location.getLink("in");
		case 5:
			return "the " + s1 + " of " + body + s2 + " animated by " + hf + location.getLink("in");
		default:
		}

		return bodies + " by " + civ + location.getLink("in") + " == abuse: " + abuseType + " == pile: " + pileType
				+ " " +item;
	}

}
