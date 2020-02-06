package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf modified structure") /// TODO no type in export
public class HfModifiedStructure extends Event implements SiteRelatedEvent, HfRelatedEvent, StructureRelatedEvent {
	@Xml("site_id")
	private int siteId = -1;
	@Xml("structure_id")
	private int structureId = -1;
	@Xml("modifier_hfid")
	private int modifierHfid = -1;
	@Xml("modification")
	private String modification;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return modifierHfid == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.siteId == siteId && this.structureId == structureId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s had a %s added to %s in %s", World.getHistoricalFigure(modifierHfid).getLink(),
				modification, World.getStructure(structureId, siteId).getLink(), World.getSite(siteId).getLink());
	}
}
