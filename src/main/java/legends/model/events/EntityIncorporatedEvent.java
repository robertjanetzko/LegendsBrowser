package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity overthrown")
public class EntityIncorporatedEvent extends Event implements EntityRelatedEvent, HfRelatedEvent, SiteRelatedEvent {
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("overthrown_hfid")
	private int overthrownHfId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("pos_taker_hfid")
	private int posTakerHfid = -1;
	@Xml("instigator_hfid")
	private int instigatorHfId = -1;
	@Xml(value = "conspirator_hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> conspiratorHfIds = new ArrayList<>();

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return overthrownHfId == hfId || posTakerHfid == hfId || instigatorHfId == hfId
				|| conspiratorHfIds.contains(hfId);
	}

	@Override
	public String getShortDescription() {
		String support = conspiratorHfIds.isEmpty() ? ""
				: String.format(". The support of %s was crucial to the coup",
						conspiratorHfIds.stream().map(World::getHistoricalFigure).collect(EventHelper.hfList()));
		return String.format("%s toppled the government of %s of %s and %s in %s%s",
				World.getHistoricalFigure(instigatorHfId).getLink(),
				World.getHistoricalFigure(overthrownHfId).getLink(), World.getEntity(entityId).getLink(),
				instigatorHfId != posTakerHfid
						? String.format("placed %s in power", World.getHistoricalFigure(posTakerHfid).getLink())
						: "assumed control",
				World.getSite(siteId).getLink(), support);
	}
}
