package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("peace rejected")
public class PeaceRejectedEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("source")
	private int sourceEnId = -1;
	@Xml("destination")
	private int destinationEnId = -1;
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("topic")
	private String topic;

	public int getSourceEnId() {
		return sourceEnId;
	}

	public void setSourceEnId(int sourceEnId) {
		this.sourceEnId = sourceEnId;
	}

	public int getDestinationEnId() {
		return destinationEnId;
	}

	public void setDestinationEnId(int destinationEnId) {
		this.destinationEnId = destinationEnId;
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
	public boolean isRelatedToEntity(int entityId) {
		return sourceEnId == entityId || destinationEnId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String source = World.getEntity(sourceEnId).getLink();
		String destination = World.getEntity(destinationEnId).getLink();
		return destination + " rejected an offer of peace from " + source;
	}

	public static void printUnknownTopics() {
		if (topics.size() > 0)
			LOG.debug("unknown peace event topics: " + topics);
	}
}
