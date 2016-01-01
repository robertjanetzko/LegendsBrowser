package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class ArtifactLostEvent extends Event implements ArtifactRelatedEvent, SiteRelatedEvent {
	private int artifactId;
	private int siteId;

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
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
		String site = World.getSite(siteId).getLink();
		return artifact + " was lost in " + site;
	}

}
