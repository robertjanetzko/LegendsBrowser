package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.ChangeHfStateEvent;
import legends.model.events.basic.Filters;

public class PurgeCollection extends EventCollection {
	private int siteId = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "adjective":
			setAdjective(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}
	
	@Override
	public void process() {
		super.process();

		getAllHistoricalEvents().stream().collect(Filters.filterEvent(ChangeHfStateEvent.class)).forEach(e -> e.setState("refugee"));;
	}

	@Override
	public String getLink() {
		String site = World.getSite(siteId).getLink();
		return "the <a href=\"/collection/" + getId() + "\" class=\"collection purge\">"+getOrdinalString()+adjective+" purge</a> in " + site;
	}
	
	@Override
	public String getShortDescription() {
		String site = World.getSite(siteId).getLink();
		return "the "+getOrdinalString()+adjective+" Purge in "+site+" occurred";
	}
}
