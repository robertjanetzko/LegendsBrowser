package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.ChangeHfStateEvent;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("purge")
public class PurgeCollection extends EventCollection {
	@Xml("site_id")
	private int siteId = -1;
	@Xml("adjective")
	private String adjective;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getAdjective() {
		return adjective;
	}

	public void setAdjective(String adjective) {
		this.adjective = adjective;
	}

	@Override
	public void process() {
		super.process();

		getAllHistoricalEvents().stream().collect(Filters.filterEvent(ChangeHfStateEvent.class))
				.forEach(e -> e.setState("refugee"));
	}

	@Override
	public String getLink() {
		String site = World.getSite(siteId).getLink();
		return "the <a href=\"" + getUrl() + "\" class=\"collection purge\">" + getOrdinalString()
				+ adjective + " purge</a> in " + site;
	}

	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		return "the " + getOrdinalString() + adjective + " Purge in " + site + " occurred";
	}
	
	public String getName() {
		String site = World.getSite(siteId).getLink();
		return "The " + getOrdinalString() + adjective + " Purge in " + site;
	}

}
