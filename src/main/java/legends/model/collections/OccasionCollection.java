package legends.model.collections;

import legends.model.Entity;
import legends.model.Occasion;
import legends.model.Site;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.OccasionEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("occasion")
public class OccasionCollection extends EventCollection {
	@Xml("civ_id")
	private int civId = -1;
	@Xml("occasion_id")
	private int occasionId = -1;

	private Site site;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public void process() {
		super.process();

		getAllHistoricalEvents().stream().collect(Filters.filterEvent(OccasionEvent.class))
				.map(OccasionEvent::getLocation).map(EventLocation::getSiteId).map(World::getSite)
				.forEach(this::setSite);
		if (civId == -1) {
			getAllHistoricalEvents().stream().collect(Filters.filterEvent(OccasionEvent.class))
					.map(OccasionEvent::getCivId).forEach(this::setCivId);
		}
	}

	public String getUrl() {
		return "/collection/" + id;
	}

	@Override
	public String getLink() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		String name = occ.getName();
		if (ordinal > 1)
			name = "the " + getOrdinalString() + " Occasion of " + name;
		return "<a href=\"" + getUrl() + "\" class=\"collection occasion\">" + name + "</a>";
	}

	@Override
	public String getShortDescription() {
		return getLink() + " occurred";
	}

	public String getDescription() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		if (occ.getId() != -1 && occ.getEvent() != -1)
			return "A festival commemorating: " + World.getHistoricalEvent(occ.getEvent()).getSentence();
		else
			return "";
	}

	public String getName() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		return occ.getName();
	}
}
