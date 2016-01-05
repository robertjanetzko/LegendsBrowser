package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class ArtifactEvent extends HfEvent implements SiteRelatedEvent, ArtifactRelatedEvent {
	private int artifactId = -1;
	private int unitId = -1;
	private int siteId = -1;

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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "artifact_id":
			setArtifactId(Integer.parseInt(value));
			break;
		case "unit_id":
			setUnitId(Integer.parseInt(value));
			break;
		case "site":
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
		String site = "";
		if (siteId != -1)
			site = " in " + World.getSite(siteId).getLink();
		switch (type) {
		case "artifact created":
			return hf + " created " + artifact + site;
		case "artifact stored":
			return hf + " stored " + artifact + site;
		default:
			return super.getShortDescription();
		}
	}

}
