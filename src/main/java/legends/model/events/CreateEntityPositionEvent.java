package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("create entity position")
public class CreateEntityPositionEvent extends HfEvent implements EntityRelatedEvent {
	@Xml("civ")
	private int civId = -1;
	@Xml("site_civ")
	private int siteCivId = -1;
	@Xml(value = "reason", track = true)
	private String reason = "reason";
	@Xml("position")
	private String position;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.civId == entityId || this.siteCivId == entityId;
	}

	@Override
	public String getShortDescription() {
		String hf = "";
		if (hfId != -1)
			hf = World.getHistoricalFigure(hfId).getLink() + " of ";
		String civ = World.getEntity(civId).getLink();
		switch (reason) {
		case "force_of_argument":
			return hf + civ + " created the position of " + position + " trough force of argument";
		case "threat_of_violence":
			return hf + civ + " compelled the creation of the position of " + position + " with threats of violence";
		case "collaboration":
			return "members of " + civ + " collaborated to create the position of " + position;
		case "wave_of_popular_support":
			return hf + civ + " created the position of " + position + ", pushed by a wave of popular support";
		case "as_a_matter_of_course":
			return hf + civ + " created the position of " + position + " as a matter of course";
		default:
		}
		return hf + civ + " created the position of " + position + " ? " + reason;
	}

}
