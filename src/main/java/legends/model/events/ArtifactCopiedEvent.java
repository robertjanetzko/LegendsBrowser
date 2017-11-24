package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact copied")
public class ArtifactCopiedEvent extends Event
		implements ArtifactRelatedEvent, SiteRelatedEvent, StructureRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("dest_site_id")
	private int destSiteId = -1;
	@Xml("dest_structure_id")
	private int destStructureId = -1;
	@Xml("dest_entity_id")
	private int destEntityId = -1;
	@Xml("source_site_id")
	private int sourceSiteId = -1;
	@Xml("source_structure_id")
	private int sourceStructureId = -1;
	@Xml("source_entity_id")
	private int sourceEntityId = -1;
	@Xml("from_original")
	private boolean fromOriginal;

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getDestSiteId() {
		return destSiteId;
	}

	public void setDestSiteId(int destSiteId) {
		this.destSiteId = destSiteId;
	}

	public int getDestStructureId() {
		return destStructureId;
	}

	public void setDestStructureId(int destStructureId) {
		this.destStructureId = destStructureId;
	}

	public int getDestEntityId() {
		return destEntityId;
	}

	public void setDestEntityId(int destEntityId) {
		this.destEntityId = destEntityId;
	}

	public int getSourceSiteId() {
		return sourceSiteId;
	}

	public void setSourceSiteId(int sourceSiteId) {
		this.sourceSiteId = sourceSiteId;
	}

	public int getSourceStructureId() {
		return sourceStructureId;
	}

	public void setSourceStructureId(int sourceStructureId) {
		this.sourceStructureId = sourceStructureId;
	}

	public int getSourceEntityId() {
		return sourceEntityId;
	}

	public void setSourceEntityId(int sourceEntityId) {
		this.sourceEntityId = sourceEntityId;
	}

	public boolean isFromOriginal() {
		return fromOriginal;
	}

	public void setFromOriginal(boolean fromOriginal) {
		this.fromOriginal = fromOriginal;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.sourceSiteId == siteId || this.destSiteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return (this.sourceSiteId == siteId && this.sourceStructureId == structureId)
				|| (this.destSiteId == siteId && this.destStructureId == structureId);
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();

		return World.getEntity(destEntityId).getLink() + " made a copy of the original " + artifact + " from "
				+ World.getStructure(sourceStructureId, sourceSiteId).getLink() + " in "
				+ World.getSite(sourceSiteId).getLink() + " of " + World.getEntity(sourceEntityId).getLink()
				+ ", keeping it within " + World.getStructure(destStructureId, destSiteId).getLink() + " in "
				+ World.getSite(destSiteId).getLink();
	}

}
