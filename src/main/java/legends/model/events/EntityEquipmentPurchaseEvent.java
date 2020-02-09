package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity equipment purchase")
public class EntityEquipmentPurchaseEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml(value = "new_equipment_level", track = true)
	private int newEquipmentLevel = -1;
	@Xml(value = "hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> hfIds = new ArrayList<>(); /// TODO unused

	@Override
	public boolean isRelatedToHf(int hfId) {
		return hfIds.contains(hfId);
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s purchased %s equipment", World.getEntity(entityId).getLink(), getQualityString());
	}

	public String getQualityString() {
		switch (newEquipmentLevel) {
		case 1:
			return "well-crafted";
		case 2:
			return "finely-crafted";
		case 3:
			return "superior quality";
		case 4:
			return "exceptional";
		case 5:
			return "masterwork";

		default:
			return "UNKNOWN QUALITY " + newEquipmentLevel;
		}
	}

}
