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

@XmlSubtype("failed intrigue corruption")
public class FailedIntrigueCorruptionEvent extends Event implements HfRelatedEvent, EntityRelatedEvent, LocalEvent {
	@Xml("target_hfid")
	private int targetHfId = -1;
	@Xml("corruptor_hfid")
	private int corruptorHfId = -1;

	@Xml("target_identity")
	private int targetIdentity = -1; /// unused
	@Xml("corruptor_identity")
	private int corruptorIdentity = -1; /// unused

	@XmlComponent
	EventLocation location = new EventLocation();

	@Xml("failed_judgment_test")
	private boolean failedJudgmentTest;

	@Xml(value = "action", track = true)
	private String action;
	@Xml("lure_hfid")
	private int lureHfId = -1;
	@Xml(value = "method", track = true)
	private String method;
	@Xml("relevant_id_for_method")
	private int relevantIdForMethod = -1;
	@Xml("relevant_entity_id")
	private int relevantEntityId = -1;
	@Xml("relevant_position_profile_id")
	private int relevantPositionProfileId = -1; /// TODO unused

	@Xml(value = "top_facet", track = true)
	private String topFacet;
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
		String meeting = lureHfId != -1
				? String.format("%s lured %s to a meeting with %s, where the latter",
						World.getHistoricalFigure(lureHfId).getShortLink(),
						World.getHistoricalFigure(targetHfId).getShortLink(),
						World.getHistoricalFigure(corruptorHfId).getShortLink())
				: String.format("%s met with %s and", World.getHistoricalFigure(corruptorHfId).getShortLink(),
						World.getHistoricalFigure(targetHfId).getShortLink());
		return String.format("%s attempted to corrupt %s in order to %s %s. %s%s %s. The plan failed.",
				World.getHistoricalFigure(corruptorHfId).getLink(), World.getHistoricalFigure(targetHfId).getLink(),
				getActionString(), location.getLink("in"), meeting, getErrorString(), getMethodString());
	}

	private String getActionString() {
		if (action == null)
			return "NO ACTION";
		switch (action) {
		case "bring into network":
			return "have someone to act on plots and schemes";
		case "bribe official":
			return "have law enforcement look the other way";
		case "corrupt in place":
			return "have an agent";
		case "induce to embezzle":
			return "secure embezzled funds";
		default:
			return "UNKNOWN ACTION";
		}
	}

	private String getErrorString() {
		if (failedJudgmentTest)
			return ", while completely misreading the situation,";
		return "";
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
