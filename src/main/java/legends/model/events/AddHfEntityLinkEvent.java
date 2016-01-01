package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class AddHfEntityLinkEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	private int civId = -1;

	private int calcHfId = -1;
	private String calcLinkType = "";

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public String getCalcLinkType() {
		return calcLinkType;
	}

	public void setCalcLinkType(String calcLinkType) {
		this.calcLinkType = calcLinkType;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}
	
	@Override
	public void process() {
		Event prev = World.getHistoricalEvent(getId() - 1);
		if (prev instanceof CreatedStructureEvent) {
			CreatedStructureEvent event = (CreatedStructureEvent) prev;
			if (getCalcHfId() == -1) {
				setCalcHfId(event.getBuilderHfId());
				setCalcLinkType("master");
			}
		}
	}

	public String getDescription() {
		String civ = World.getEntity(civId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();

		switch (calcLinkType) {
		case "enemy":
			return hf + " became an enemy of " + civ;
		case "prisoner":
			return hf + " was imprisoned by " + civ;
		case "ruler":
			return hf + " became ruler of " + civ;
		case "master":
			return hf + " became master of " + civ;
		default:
			return hf + " linked to " + civ;
		}
	}

	@Override
	public String getShortDescription() {
//		String info = "<ul>";
//		Event prev = World.getHistoricalEvent(getId() - 1);
//		if (prev != null)
//			info += "<li>after: " + prev.getDescription()+"</li>";
//		Event next = World.getHistoricalEvent(getId() + 1);
//		if (next != null)
//			info += "<li>before: " + next.getDescription()+"</li>";
//		info += "</ul>";
		return getDescription();//+info;
	}

}
