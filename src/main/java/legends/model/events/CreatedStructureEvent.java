package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class CreatedStructureEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent, HfRelatedEvent {
	int civId = -1;
	int siteId = -1;
	int siteCivId = -1;
	int structureId = -1;
	int builderHfId = -1;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getBuilderHfId() {
		return builderHfId;
	}

	public void setBuilderHfId(int builderHfId) {
		this.builderHfId = builderHfId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "structure_id":
			setStructureId(Integer.parseInt(value));
			break;
		case "builder_hfid":
			setBuilderHfId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return builderHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		if (builderHfId != -1)
			return World.getHistoricalFigure(builderHfId).getLink() + " thrust a spire of slade up from the underworld, naming it UNKNOWN BUILDING, and established a gateway between worlds in " + site;
		if (siteCivId != -1)
			return World.getEntity(siteCivId).getLink() + " of " + World.getEntity(civId).getLink() + " constructed "
					+ structureId + " in " + site;
		else
			return World.getEntity(civId).getLink() + " constructed " + structureId + " in " + site;
	}
}
