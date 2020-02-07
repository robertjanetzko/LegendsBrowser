package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.RegionRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact recovered")
public class ArtifactRecoveredEvent extends HfEvent
		implements SiteRelatedEvent, ArtifactRelatedEvent, RegionRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("unit_id")
	private int unitId = -1;
	@Xml("site_id,site")
	private int siteId = -1;
	@Xml("structure_id")
	private int structureId = -1;
	@Xml("subregion_id")
	private int subregionId = -1;

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
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToRegion(int regionId) {
		return subregionId == regionId;
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = "";
		if (subregionId != -1)
			site = "in " + World.getRegion(subregionId).getLink();
		if (siteId != -1)
			site = "in " + World.getSite(siteId).getLink();
		if (structureId != -1)
			site = "from " + World.getStructure(structureId, siteId).getLink() + " " + site;
		return artifact + " was recovered " + site + " by " + hf;
	}

}
