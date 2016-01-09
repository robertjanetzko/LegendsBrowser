package legends.model.events;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;

public class CreatedStructureEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent, HfRelatedEvent, StructureRelatedEvent {
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
		case "civ":
			setCivId(Integer.parseInt(value));
			break;
		case "site_id":
		case "site":
			setSiteId(Integer.parseInt(value));
			break;
		case "site_civ_id":
		case "site_civ":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "structure_id":
		case "structure":
			setStructureId(Integer.parseInt(value));
			break;
		case "builder_hfid":
		case "builder_hf":
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
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.siteId == siteId;
	}

	@Override
	public void process() {
		Site site = World.getSite(siteId);
		Entity civ = World.getEntity(civId);
		civ.getSites().add(site);
		site.setOwner(civ);
		
		Entity siteCiv = World.getEntity(siteCivId);
		siteCiv.getSites().add(site);
		siteCiv.setParent(civ);
		if(siteCiv.getType() .equals("unknown"))
			siteCiv.setType("sitegovernment");
		if(siteCiv.getRace() .equals("unknown"))
			siteCiv.setRace(civ.getRace());
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		if (builderHfId != -1)
			return World.getHistoricalFigure(builderHfId).getLink()
					+ " thrust a spire of slade up from the underworld, naming it "+World.getStructure(structureId, siteId).getLink()+", and established a gateway between worlds in "
					+ site;
		if (siteCivId != -1)
			return World.getEntity(siteCivId).getLink() + " of " + World.getEntity(civId).getLink() + " constructed "
					+ World.getStructure(structureId, siteId).getLink() + " in " + site;
		else
			return World.getEntity(civId).getLink() + " constructed " + World.getStructure(structureId, siteId).getLink() + " in " + site;
	}
}
