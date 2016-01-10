package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;

public class MerchantEvent extends Event {
	private int sourceId = -1;
	private int destinationId = -1;
	private int siteId = -1;

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "source":
			setSourceId(Integer.parseInt(value));
			break;
		case "destination":
			setDestinationId(Integer.parseInt(value));
			break;
		case "site":
			setSiteId(Integer.parseInt(value));
			break;
		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		final String site = World.getSite(siteId).getLink();
		final String source = World.getEntity(sourceId).getLink();
		final String destination = World.getEntity(destinationId).getLink();
		return "merchants from " + source + " visited " + destination + " at " + site;
	}

}
