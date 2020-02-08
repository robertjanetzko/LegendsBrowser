package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf recruited unit type for entity")
public class HfRecruitedUnitTypeForEntityEvent extends HfEvent implements LocalEvent, EntityRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("unit_type")
	private String unitType = "";
	@XmlComponent
	private EventLocation location = new EventLocation();

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String entity = World.getEntity(entityId).getLink();
		return hf + " recruited " + unitType + "s into " + entity + location.getLink("in");
	}

}
