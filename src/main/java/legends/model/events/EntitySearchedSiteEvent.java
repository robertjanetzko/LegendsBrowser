package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("entity searched site")
public class EntitySearchedSiteEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("searcher_civ_id")
	private int searcherCivId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml(value = "result", track = true)
	private String result;

	public int getSearcherCivId() {
		return searcherCivId;
	}

	public void setSearcherCivId(int searcherCivId) {
		this.searcherCivId = searcherCivId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return searcherCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();

		return "the forces of " + World.getEntity(searcherCivId).getLink() + " searched " + site + " and found nothing";
	}
}
