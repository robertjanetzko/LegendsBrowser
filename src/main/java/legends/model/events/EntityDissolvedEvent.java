package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity dissolved")
public class EntityDissolvedEvent extends Event implements EntityRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("reason")
	private String reason;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s dissolved after %s", World.getEntity(entityId).getLink(), reason);
	}

}
