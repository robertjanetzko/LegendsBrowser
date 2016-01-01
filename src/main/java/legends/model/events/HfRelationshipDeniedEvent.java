package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class HfRelationshipDeniedEvent extends Event implements LocalEvent, HfRelatedEvent {
	private int seekerHfId = -1;
	private int targetHfId = -1;
	private String relationship;
	private String reason;
	private int reasonId = -1;

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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "seeker_hfid":
			setSeekerHfId(Integer.parseInt(value));
			break;
		case "target_hfid":
			setTargetHfId(Integer.parseInt(value));
			break;
		case "relationship":
			relationships.add(value);
			setRelationship(value);
			break;
		case "reason":
			reasons.add(value);
			setReason(value);
			break;
		case "reason_id":
			setReasonId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
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
				return seeker + " was denied an apprenticeship under " + target + loc + " as "+reasonHf+" prefers to work alone";
			case "jealousy":
				return seeker + " was denied an apprenticeship under " + target + loc + " due to "+reasonHf+"'s jealousy";
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
			System.out.println("unknown hf denied relationships: " + relationships);
	}

	public static void printUnknownReasons() {
		reasons.remove("prefers working alone");
		reasons.remove("jealousy");
		
		if (reasons.size() > 0)
			System.out.println("unknown hf denied relationships reasons: " + reasons);
	}

}
