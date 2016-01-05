package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class PeaceEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	private int calcOffererCivId = -1;
	private int calcOfferedCivId = -1;
	private int siteId = -1;
	private String topic;

	public int getCalcOffererCivId() {
		return calcOffererCivId;
	}

	public void setCalcOffererCivId(int calcOfferCivId) {
		this.calcOffererCivId = calcOfferCivId;
	}

	public int getCalcOfferedCivId() {
		return calcOfferedCivId;
	}

	public void setCalcOfferedCivId(int calcOfferedCivId) {
		this.calcOfferedCivId = calcOfferedCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	private static Set<String> topics = new HashSet<>();

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "site":
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "source":
			setCalcOffererCivId(Integer.parseInt(value));
			break;
		case "destination":
			setCalcOfferedCivId(Integer.parseInt(value));
			break;
		case "topic":
			topics.add(value);
			setTopic(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return calcOffererCivId == entityId || calcOfferedCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String offerer = "UNKNOWN CIVILIZATION";
		if (calcOffererCivId != -1)
			offerer = World.getEntity(calcOffererCivId).getLink();
		String offered = "UNKNOWN CIVILIZATION";
		if (calcOfferedCivId != -1)
			offered = World.getEntity(calcOfferedCivId).getLink();
		switch (type) {
		case "peace accepted":
			return offered + " accepted an offer of peace from " + offerer;
		case "peace rejected":
			return offered + " rejected an offer of peace from " + offerer;
		default:
			return "unknown peace event";
		}
	}
	
	public static void printUnknownTopics() {
		if(topics.size()>0)
			System.out.println("unknown peace event topics: "+topics);
	}
}
