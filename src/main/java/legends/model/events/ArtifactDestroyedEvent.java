package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact destroyed")
public class ArtifactDestroyedEvent extends Event
		implements ArtifactRelatedEvent, SiteRelatedEvent, EntityRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("destroyer_enid")
	private int destroyerEnId = -1;

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.destroyerEnId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		return World.getArtifact(artifactId).getLink() + " was destroyed by " + World.getEntity(destroyerEnId).getLink() + " in "
				+ World.getSite(siteId).getLink();
	}
}
