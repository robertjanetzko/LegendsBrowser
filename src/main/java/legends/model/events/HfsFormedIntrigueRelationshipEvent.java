package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hfs formed intrigue relationship")
public class HfsFormedIntrigueRelationshipEvent extends Event implements HfRelatedEvent, LocalEvent, EntityRelatedEvent {
	@Xml("target_hfid")
	private int targetHfId = -1;
	@Xml("corruptor_hfid")
	private int corruptorHfId = -1;
	/// TODO add meeting participants from legends-plus

	@Xml(value = "corruptor_seen_as", track = true)
	private String corruptorSeenAs;
	@Xml(value = "target_seen_as", track = true)
	private String targetSeenAs;

	@Xml("target_identity")
	private int targetIdentity = -1;
	@Xml("corruptor_identity")
	private int corruptorIdentity = -1;

	@Xml("relevant_entity_id")
	private int relevantEntityId = -1;
	@Xml("relevant_position_profile_id")
	private int relevantPositionProfileId = -1; /// TODO unused

	@XmlComponent
	EventLocation location = new EventLocation();

	@Xml("successful")
	private boolean successful;
	@Xml("failed_judgment_test")
	private boolean failedJudgmentTest;

	@Xml(value = "action", track = true)
	private String action;
	@Xml("lure_hfid")
	private int lureHfId = -1;
	@Xml(value = "method", track = true)
	private String method;
	@Xml(value = "relevant_id_for_method")
	private int relevantIdForMethod = -1;
	@Xml(value = "circumstance", track = true)
	private String circumstance;
	@Xml(value = "circumstance_id", track = true)
	private int circumstanceId = -1;

	@Xml(value = "top_facet", track = true)
	private String topFacet; /// TODO unused
	@Xml("top_facet_rating")
	private int topFacetRating;
	@Xml("top_facet_modifier")
	private int topFacetModifier;
	@Xml(value = "top_value", track = true)
	private String topValue;
	@Xml("top_value_rating")
	private int topValueRating;
	@Xml("top_value_modifier")
	private int topValueModifier;
	@Xml(value = "top_relationship_factor", track = true)
	private String topRelationshipFactor;
	@Xml("top_relationship_rating")
	private int topRelationshipRating;
	@Xml("top_relationship_modifier")
	private int topRelationshipModifier;
	@Xml("ally_defense_bonus")
	private int allyDefenseBonus;
	@Xml("coconspirator_bonus")
	private int coconspiratorBonus;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return targetHfId == hfId || corruptorHfId == hfId || lureHfId == hfId || relevantIdForMethod == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return relevantEntityId == entityId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		if ("is entity subordinate".equals(circumstance))
			return String.format("%s subordinated %s as a member of %s toward the fullfillment of plots and schemes %s",
					World.getHistoricalFigure(corruptorHfId).getLink(), World.getHistoricalFigure(targetHfId).getLink(),
					World.getEntity(circumstanceId).getLink(), location.getLink("in"));
		String meeting = lureHfId != -1
				? String.format("%s lured %s to a meeting with %s, where the latter",
						World.getHistoricalFigure(lureHfId).getShortLink(),
						World.getHistoricalFigure(targetHfId).getShortLink(),
						World.getHistoricalFigure(corruptorHfId).getShortLink())
				: String.format("%s met with %s and", World.getHistoricalFigure(corruptorHfId).getShortLink(),
						World.getHistoricalFigure(targetHfId).getShortLink());
		return String.format("%s corrupted %s in order to %s %s. %s %s. %s agreed willingly.",
				World.getHistoricalFigure(corruptorHfId).getLink(), World.getHistoricalFigure(targetHfId).getLink(),
				getActionString(), location.getLink("in"), meeting, getMethodString(),
				World.getHistoricalFigure(targetHfId).getPronounWithCapitalFirst());
	}

	private String getActionString() {
		if (action == null)
			return "NO ACTION";
		switch (action) {
		case "bribe official":
			return "have law enforcement look the other way";
		case "bring into network":
			return "to have someone to act on plots and schemes";
		case "corrupt in place":
			return "have an agent";
		default:
			return "UNKNOWN ACTION";
		}
	}

	private String getMethodString() {
		if (method == null)
			return "NO METHOD";
		switch (method) {
		case "blackmail over embezzlement":
			return "made a blackmail threat, due to embezzlement using the position UNKNOWN POSITION of "
					+ World.getEntity(relevantEntityId).getLink();
		case "bribe":
			return "offered a bribe";
		case "flatter":
			return "made flattering remarks";
		case "intimidate":
			return "made a threat";
		case "offer immortality":
			return "offered immortality";
		case "precedence":
			return "pulled rank as UNKNOWN POSITION of " + World.getEntity(relevantEntityId).getLink();
		case "religious sympathy":
			return "played for sympathy by appealing to shared worship of "
					+ World.getHistoricalFigure(relevantIdForMethod).getLink();
		case "revenge on grudge":
			return "offered revenge upon the persecutor " + World.getHistoricalFigure(relevantIdForMethod).getLink();
		default:
			return "UNKNOWN METHOD" + method;
		}
	}

}
