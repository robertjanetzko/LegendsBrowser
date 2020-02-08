package legends.model.events;

import legends.model.Entity;
import legends.model.EntityHonor;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("add hf entity honor")
public class AddHfEntityHonor extends Event implements HfRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("hfid")
	private int hfId = -1;
	@Xml("honor_id")
	private int honorId = -1;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.hfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		Entity entity = World.getEntity(entityId);
		EntityHonor honor = entity.getHonor(honorId);
		return String.format("%s received the title %s of %s%s", World.getHistoricalFigure(hfId).getLink(), honor.getName(),
				entity.getLink(), honor.getRequirement());
	}

}
