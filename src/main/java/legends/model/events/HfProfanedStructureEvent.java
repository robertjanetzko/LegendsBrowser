package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf profaned structure")
public class HfProfanedStructureEvent extends HfEvent implements SiteRelatedEvent, StructureRelatedEvent {
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("structure,structure_id")
	private int structureId = -1;
	@Xml("action")
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
