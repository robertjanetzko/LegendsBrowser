package legends.model.collections;

import legends.model.Site;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.OccasionEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("occasion,performance,procession,ceremony,competition")
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
		String civ = World.getEntity(civId).getLink();
		return "the <a href=\"" +getUrl() + "\" class=\"collection occasion\">" + getOrdinalString() + type
				+ " " + occasionId + " </a> of " + civ;
	}

	@Override
	public String getShortDescription() {
		return getLink()+" occurred";
	}
}
