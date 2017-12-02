package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact possessed")
public class ArtifactPosessedEvent extends HfEvent implements ArtifactRelatedEvent, SiteRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("unit_id")
	private int unitId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml(value = "circumstance", track = true)
	private String circumstance;
	@Xml("circumstance_id")
	private int circumstanceId = -1;
	@Xml(value = "reason", track = true)
	private String reason;
	@Xml("reason_id")
	private int reasonId = -1;

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getCircumstance() {
		return circumstance;
	}

	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "an unknown site";
		if (siteId != -1)
			site = World.getSite(siteId).getLink();
		if ("artifact is symbol of entity position".equals(reason))
			return artifact + " was aquired in " + site + " by " + hf + " as a symbol of authority";
		if ("artifact is heirloom of family hfid".equals(reason))
			return artifact + " was aquired in " + site + " by " + hf + " as an heirloom of "
					+ World.getHistoricalFigure(reasonId).getLink() + " family"
					+ ("hf is dead".equals(circumstance)
							? " after the death of " + World.getHistoricalFigure(circumstanceId).getLink()
							: "");
		return artifact + " was claimed in " + site + " by " + hf;
	}

}
