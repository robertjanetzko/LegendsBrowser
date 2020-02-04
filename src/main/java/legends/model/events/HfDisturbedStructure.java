package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf disturbed structure")
public class HfDisturbedStructure extends HfEvent implements SiteRelatedEvent, StructureRelatedEvent {
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("structure,structure_id")
	private int structureId = -1;
	
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
		return hf + " disturbed " + World.getStructure(structureId, siteId).getLink() + " in " + site;
	}
}
