package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf relationship denied")
public class HfRelationshipDeniedEvent extends Event implements LocalEvent, HfRelatedEvent {
	@Xml("seeker_hfid")
	private int seekerHfId = -1;
	@Xml("target_hfid")
	private int targetHfId = -1;
	@Xml(value = "relationship", track = true)
	private String relationship;
	@Xml(value = "reason", track = true)
	private String reason;
	@Xml("reason_id")
	private int reasonId = -1;
	@XmlComponent
	private EventLocation location = new EventLocation();

	private static Set<String> relationships = new HashSet<>();
	private static Set<String> reasons = new HashSet<>();

	public int getSeekerHfId() {
		return seekerHfId;
	}

	public void setSeekerHfId(int seekerHfId) {
		this.seekerHfId = seekerHfId;
	}

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return seekerHfId == hfId || targetHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String seeker = World.getHistoricalFigure(seekerHfId).getLink();
		String target = World.getHistoricalFigure(targetHfId).getLink();
		String reasonHf = "";
		if (reasonId != -1) {
			if (reasonId != targetHfId)
				reasonHf = World.getHistoricalFigure(reasonId).getLink();
			else
				reasonHf = "the latter";
		}
		String loc = location.getLink("in");

		switch (relationship) {
		case "apprentice":
			switch (reason) {
			case "prefers working alone":
				return seeker + " was denied an apprenticeship under " + target + loc + " as " + reasonHf
						+ " prefers to work alone";
			case "jealousy":
				return seeker + " was denied an apprenticeship under " + target + loc + " due to " + reasonHf
						+ "'s jealousy";
			default:
				break;
			}

		default:
			break;
		}

		return seeker + " " + target + loc + ": " + relationship + " " + reason + " " + reasonHf;
	}

	public static void printUnknownRelationships() {
		relationships.remove("apprentice");

		if (relationships.size() > 0)
			LOG.debug("unknown hf denied relationships: " + relationships);
	}

	public static void printUnknownReasons() {
		reasons.remove("prefers working alone");
		reasons.remove("jealousy");

		if (reasons.size() > 0)
			LOG.debug("unknown hf denied relationships reasons: " + reasons);
	}

}
