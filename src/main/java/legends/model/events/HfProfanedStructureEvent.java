package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;

public class HfProfanedStructureEvent extends HfEvent implements SiteRelatedEvent, StructureRelatedEvent {
	private int siteId = -1;
	private int structureId = -1;
	private int action = -1;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "site":
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "structure":
		case "structure_id":
			setStructureId(Integer.parseInt(value));
			break;
		case "action":
			setAction(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}
	
	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.siteId == siteId;
	}


	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String site = World.getSite(siteId).getLink();
		return hf + " profaned " + World.getStructure(structureId, siteId).getLink() + " in " + site;
	}

}
