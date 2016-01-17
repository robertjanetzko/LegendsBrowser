package legends.model.events;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece lost")
public class MasterpieceLost extends HfEvent implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("creation_event")
	private int creationEvent = -1;
	@Xml("site")
	private int siteId = -1;
	@Xml("method")
	private int method = -1;

	private MasterpieceEvent event;

	@Override
	public void process() {
		Event e = World.getHistoricalEvent(creationEvent);
		if (e instanceof MasterpieceEvent)
			event = (MasterpieceEvent) e;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return event != null && event.getEntityId() == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId || (event != null && event.getSiteId() == siteId);
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || (event != null && event.getHfId() == hfId);
	}

	@Override
	public String getShortDescription() {
		String m;
		switch (method) {
		case 2:
			m = " during fortification carving";
			break;
		case 7:
		case 20:
		case 36:
		case 37:
		case 39:
		case 54:
		case 775:
			m = "";
			break;

		default:
			m = ""; // " with unknown method " + method;
			break;
		}

		HistoricalFigure hf = World.getHistoricalFigure(hfId);
		String creation = "an unknown masterpiece";
		if (event != null)
			creation = event.getCreation() + " by " + World.getHistoricalFigure(event.getHfId()).getLink() + " for "
					+ World.getEntity(event.getEntityId()).getLink() + " at "
					+ World.getSite(event.getSiteId()).getLink();
		if (hf.getId() != -1)
			return hf.getLink() + " destroyed " + creation + m + " at " + World.getSite(siteId).getLink();
		else
			return creation + " was destroyed by an unknown creature " + m + " at " + World.getSite(siteId).getLink();
	}
}
