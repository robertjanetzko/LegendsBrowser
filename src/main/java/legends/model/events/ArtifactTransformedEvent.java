package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact transformed")
public class ArtifactTransformedEvent extends HfEvent implements ArtifactRelatedEvent, SiteRelatedEvent {
	@Xml("new_artifact_id")
	private int newArtifactId = -1;
	@Xml("old_artifact_id")
	private int oldArtifactId = -1;
	@Xml("unit_id")
	private int unitId = -1;
	@Xml("site_id")
	private int siteId = -1;

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.newArtifactId == artifactId || this.oldArtifactId == artifactId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return World.getArtifact(newArtifactId).getLink() + " was made from "
				+ World.getArtifact(oldArtifactId).getLink()+" in "+World.getSite(siteId).getLink();
	}
}
