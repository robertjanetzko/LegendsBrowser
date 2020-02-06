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
	private int reason = -1;
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

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
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
		case 0:
			return hf + civ + " created the position of " + position + " trough force of argument";
		case 1:
			return hf + civ + " compelled the creation of the position of " + position + " with threats of violence";
		case 2:
			return "members of " + civ + " collaborated to create the position of " + position;
		case 3:
			return hf + civ + " created the position of " + position + ", pushed by a wave of popular support";
		case 4:
			return hf + civ + " created the position of " + position + " as a matter of course";
		default:
		}
		return hf + civ + " created the position of " + position + " ? " + reason;
	}

}
