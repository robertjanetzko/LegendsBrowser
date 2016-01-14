package legends.model.collections;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.PeaceAcceptedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("war")
public class WarCollection extends EventCollection {
	@Xml("name")
	private String name;
	@Xml("aggressor_ent_id")
	private int aggressorEntId = -1;
	@Xml("defender_ent_id")
	private int defenderEntId = -1;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAggressorEntId() {
		return aggressorEntId;
	}

	public void setAggressorEntId(int aggressorEntId) {
		this.aggressorEntId = aggressorEntId;
	}

	public int getDefenderEntId() {
		return defenderEntId;
	}

	public void setDefenderEntId(int defenderEntId) {
		this.defenderEntId = defenderEntId;
	}

	@Override
	public void process() {
		super.process();

		getAllHistoricalEvents().stream().filter(e -> e instanceof PeaceAcceptedEvent)
				.map(e -> ((PeaceAcceptedEvent) e)).filter(e -> e.getDestinationEnId() == -1)
				.forEach(e -> e.setDestinationEnId(aggressorEntId));
		getAllHistoricalEvents().stream().filter(e -> e instanceof PeaceAcceptedEvent)
				.map(e -> ((PeaceAcceptedEvent) e)).filter(e -> e.getSourceEnId() == -1)
				.forEach(e -> e.setSourceEnId(defenderEntId));

	}

	@Override
	public String getShortDescription() {
		String aggressor = World.getEntity(aggressorEntId).getLink();
		String defender = World.getEntity(defenderEntId).getLink();
		return getLink() + " was waged by " + aggressor + " on " + defender;
	}

	@Override
	public String getLink() {
		return "<a href=\"/collection/" + getId() + "\" class=\"collection war\">" + getName() + "</a>";
	}
}
