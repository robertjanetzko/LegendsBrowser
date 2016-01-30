package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity law")
public class EntityLawEvent extends HfEvent implements EntityRelatedEvent {
	@Xml("entity_id")
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

	@Xml("law_add")
	public void setLawAdd(String law) {
		setLaw(law);
		setLawType("add");
	}

	@Xml("law_remove")
	public void setLawRemove(String law) {
		setLaw(law);
		setLawType("remove");
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
			if ("add".equals(lawType))
				return hf + " laid a series of oppressive edicts upon " + civ;
			else
				return hf + " lifted numerous oppressive laws from " + civ;
		default:
			return super.getShortDescription() + ": " + hf + " " + lawType + " " + law + " " + civ;
		}

	}

	public static void printUnknownLaws() {
		laws.remove("harsh");

		if (laws.size() > 0)
			LOG.debug("unknown entity laws: " + laws);
	}

}
