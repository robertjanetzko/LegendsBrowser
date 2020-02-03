package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf viewed artifact")
public class HfViewedArtifact extends HfEvent implements SiteRelatedEvent, ArtifactRelatedEvent, StructureRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("site_id,site")
	private int siteId = -1;
	@Xml("structure_id")
	private int structureId = -1;

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

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
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
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.siteId == siteId && this.structureId == structureId;
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "";
		if (siteId != -1)
			site = " in " + World.getSite(siteId).getLink();
		if (structureId != -1)
			site = "in " + World.getStructure(structureId, siteId).getLink() + " " + site;
		return hf + " viewed " + artifact + site;
	}

}
