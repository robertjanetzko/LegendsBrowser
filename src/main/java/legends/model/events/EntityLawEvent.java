package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;

public class EntityLawEvent extends HfEvent implements EntityRelatedEvent {
	private int entityId;
	private String lawType;
	private String law;

	private static Set<String> laws = new HashSet<>();

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getLawType() {
		return lawType;
	}

	public void setLawType(String lawType) {
		this.lawType = lawType;
	}

	public String getLaw() {
		return law;
	}

	public void setLaw(String law) {
		this.law = law;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "entity_id":
			setEntityId(Integer.parseInt(value));
			break;
		case "law_add":
			laws.add(value);
			setLawType("add");
			setLaw(value);
			break;
		case "law_remove":
			laws.add(value);
			setLawType("remove");
			setLaw(value);
			break;
		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(entityId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		switch (law) {
		case "harsh":
			if("add".equals(lawType))
				return hf+" laid a series of oppressive edicts upon "+civ;
			else
				return hf+" lifted numerous oppressive laws from "+civ;
		default:
			return super.getShortDescription() + ": " + hf + " " + lawType + " " + law + " " + civ;
		}

	}

	public static void printUnknownLaws() {
		laws.remove("harsh");

		if (laws.size() > 0)
			System.out.println("unknown entity laws: " + laws);
	}

}
