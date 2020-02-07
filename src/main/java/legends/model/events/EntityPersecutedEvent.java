
package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity persecuted")
public class EntityPersecutedEvent extends Event
		implements EntityRelatedEvent, SiteRelatedEvent, HfRelatedEvent, StructureRelatedEvent {
	@Xml("persecutor_hfid")
	private int persecutorHfId = -1;
	@Xml("persecutor_enid")
	private int persecutorEnId = -1;
	@Xml("target_enid")
	private int targetEnId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml(value = "expelled_hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> expelledHfIds = new ArrayList<>();

	@Xml("destroyed_structure_id")
	private int destroyedStructureId = -1;
	@Xml("shrine_amount_destroyed")
	private int shrineAmountDestroyed = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.persecutorEnId == entityId || targetEnId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return persecutorHfId == hfId || expelledHfIds.contains(hfId);
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return destroyedStructureId == structureId && this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String sites = "";
		if (destroyedStructureId != -1)
			sites = String.format(" and %s was destroyed%s", World.getStructure(destroyedStructureId, siteId).getLink(),
					shrineAmountDestroyed > 0 ? " along with several smaller sacred sites" : "");
		else
			sites =  " and some sacred sites were desecrated";
		return String.format("%s of %s persecuted %s in %s. %s %s expelled%s",
				World.getHistoricalFigure(persecutorHfId).getLink(), World.getEntity(persecutorEnId).getLink(),
				World.getEntity(targetEnId).getLink(), World.getSite(siteId).getLink(),
				expelledHfIds.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink)
						.collect(EventHelper.stringList()),
				expelledHfIds.size() > 1 ? "were" : "was", sites);
	}
}
