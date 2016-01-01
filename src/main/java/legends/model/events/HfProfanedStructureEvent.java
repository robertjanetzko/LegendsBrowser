package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class HfProfanedStructureEvent extends HfEvent implements SiteRelatedEvent {
	private int siteId = -1;
	private int structureId = -1;

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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "structure_id":
			setStructureId(Integer.parseInt(value));
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
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String site = World.getSite(siteId).getLink();
		return hf + " profaned "+structureId+" in " + site;
	}

}
