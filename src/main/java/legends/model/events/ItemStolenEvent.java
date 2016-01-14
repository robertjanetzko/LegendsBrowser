package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.Item;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("item stolen")
public class ItemStolenEvent extends HfEvent implements SiteRelatedEvent, StructureRelatedEvent {
	@Xml("site")
	private int calcSiteId = -1;
	@Xml("structure")
	private int structureId = -1;
	@Xml("entity")
	private int entityId = -1;
	@XmlComponent
	Item item = new Item();

	public int getCalcSiteId() {
		return calcSiteId;
	}

	public void setCalcSiteId(int calcSiteId) {
		this.calcSiteId = calcSiteId;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.calcSiteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.calcSiteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String structure = "";
		if (structureId != -1)
			structure = World.getStructure(structureId, calcSiteId).getLink() + " in ";

		String site = "UNKNOWN SITE";
		if (calcSiteId != -1)
			site = World.getSite(calcSiteId).getLink();

		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (hfId != -1)
			hf = World.getHistoricalFigure(hfId).getLink();

		return item.getText("") + " was stolen from " + structure + site + " by " + hf;
	}

}
