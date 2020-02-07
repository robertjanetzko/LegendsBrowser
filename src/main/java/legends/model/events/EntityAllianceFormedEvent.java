package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity alliance formed")
public class EntityAllianceFormedEvent extends Event implements EntityRelatedEvent {
	@Xml("initiating_enid")
	private int initiatingEnId = -1;
	@Xml(value = "joining_enid", elementClass = Integer.class, multiple = true)
	private List<Integer> joiningEnId = new ArrayList<>();

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.initiatingEnId == entityId || joiningEnId.contains(entityId);
	}

	@Override
	public String getShortDescription() {
		return String.format("%s swore to support %s in war if the latter did likewise",
				joiningEnId.stream().map(World::getEntity).map(Entity::getLink).collect(EventHelper.stringList()),
				World.getEntity(initiatingEnId).getLink());
	}
}
