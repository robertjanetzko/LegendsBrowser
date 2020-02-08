package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("agreement formed")
public class AgreementFormedEvent extends Event implements HfRelatedEvent, SiteRelatedEvent {
	@Xml("agreement_id")
	private int agreementId = -1;
	@Xml("concluder_hfid")
	private int concluderHfId = -1;
	@Xml("agreement_subject_id")
	private int agreementSubjectId = -1;
	@Xml(value = "reason", track = true)
	private String reason;

	private int calcHfId = -1;
	private int calcSiteId = -1;
	private int calcArtifactId = -1;
	
	@Xml("successful")
	private boolean successful;
	@Xml("failed_judgment_test")
	private boolean failedJudgmentTest;
	@Xml("delegated")
	private boolean delegated;

	@Xml(value = "action", track = true)
	private String action; /// TODO unused
	@Xml(value = "method", track = true)
	private String method;

	@Xml(value = "relevant_id_for_method")
	private int relevantIdForMethod = -1;
	@Xml("relevant_entity_id")
	private int relevantEntityId = -1;
	@Xml("relevant_position_profile_id")
	private int relevantPositionProfileId = -1; /// TODO unused

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

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getConcluderHfId() {
		return concluderHfId;
	}

	public void setConcluderHfId(int concluderHfId) {
		this.concluderHfId = concluderHfId;
	}

	public int getAgreementSubjectId() {
		return agreementSubjectId;
	}

	public void setAgreementSubjectId(int agreementSubjectId) {
		this.agreementSubjectId = agreementSubjectId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public int getCalcSiteId() {
		return calcSiteId;
	}

	public void setCalcSiteId(int calcSiteId) {
		this.calcSiteId = calcSiteId;
	}

	public int getCalcArtifactId() {
		return calcArtifactId;
	}

	public void setCalcArtifactId(int calcArtifactId) {
		this.calcArtifactId = calcArtifactId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId || concluderHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return calcSiteId == siteId;
	}

	@Override
	public void process() {
		Event next = World.getHistoricalEvent(getId() + 1);
		if (next instanceof ArtifactCreatedEvent) {
			ArtifactCreatedEvent ae = (ArtifactCreatedEvent) next;
			if (calcHfId == -1)
				setCalcHfId(ae.getHfId());
			if (calcSiteId == -1)
				setCalcSiteId(ae.getSiteId());
			if (calcArtifactId == -1)
				setCalcArtifactId(ae.getArtifactId());
		}
	}

	@Override
	public String getShortDescription() {
		if (calcHfId != -1) {
			// demon summoning
			String hf = "UNKNOWN HISTORICAL FIGURE";
			if (calcHfId != -1)
				hf = World.getHistoricalFigure(calcHfId).getLink();
			String site = "UNKNOWN SITE";
			if (calcSiteId != -1)
				site = World.getSite(calcSiteId).getLink();
			String artifact = "UNKNOWN ARTIFACT";
			if (calcArtifactId != -1)
				artifact = World.getArtifact(calcArtifactId).getLink();
			return id + " - " + agreementId + " UNKNOWN HISTORICAL FIGURE aided " + hf
					+ " in becoming a permanent part of the living world that war might rage forever. The ritual took place in "
					+ site + " using " + artifact;
		} else {
			if (reason == null)
				return "UNKNOWN AGREEMENT " + agreementId + " formed";
			else
				return World.getHistoricalFigure(concluderHfId).getLink() + " concluded UNKNOWN AGRREMENT "
						+ agreementId + " after " + reason;
		}
	}

}
