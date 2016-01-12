package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class AgreementFormedEvent extends Event implements HfRelatedEvent, SiteRelatedEvent {
	private int agreementId = -1;

	private int concluderHfId = -1;
	private int agreementSubjectId = -1;
	private String reason;

	private int calcHfId = -1;
	private int calcSiteId = -1;
	private int calcArtifactId = -1;

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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "agreement_id":
			setAgreementId(Integer.parseInt(value));
			break;
		case "concluder_hfid":
			setConcluderHfId(Integer.parseInt(value));
			break;
		case "agreement_subject_id":
			setAgreementSubjectId(Integer.parseInt(value));
			break;
		case "reason":
			setReason(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
		if (next instanceof ArtifactEvent) {
			ArtifactEvent ae = (ArtifactEvent) next;
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
			return id+" - "+agreementId + " UNKNOWN HISTORICAL FIGURE aided " + hf
					+ " in becoming a permanent part of the living world that war might rage forever. The ritual took place in "
					+ site + " using " + artifact;
		} else {
			if(reason == null)
				return "UNKNOWN AGREEMENT "+agreementId+" formed";
			else
			return World.getHistoricalFigure(concluderHfId).getLink()+" concluded UNKNOWN AGRREMENT "+agreementId+" after "+reason;
		}
	}

}
