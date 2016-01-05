package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class CreateEntityPositionEvent extends HfEvent implements EntityRelatedEvent {
	private int civId = -1;
	private int siteCivId = -1;
	private int reason = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "civ":
			setCivId(Integer.parseInt(value));
			break;
		case "site_civ":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "reason":
			setReason(Integer.parseInt(value));
			break;
		case "position":
			setPosition(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.civId == entityId || this.siteCivId == entityId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String civ = World.getEntity(civId).getLink();
		switch (reason) {
		case 0:
			return hf + " of " + civ + " created the position of " + position + " trough force of argument";
		case 1:
			return hf + " of " + civ + " compelled the creation of the position of " + position
					+ " with threats of violence";
		case 2:
			return "members of " + civ + " collaborated to create the position of " + position;
		case 3:
			return hf + " of " + civ + " created the position of " + position + ", pushed by a wave of pupular support";
		case 4:
			return hf + " of " + civ + " created the position of " + position + " as a matter of course";
		default:
		}
		return hf + " of " + civ + " created the position of " + position + " ? " + reason;
	}

}
