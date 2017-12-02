package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact claim formed")
public class ArtifactClaimFormedEvent extends HfEvent implements ArtifactRelatedEvent, EntityRelatedEvent {
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("entity_id")
	private int entityId = -1;
	@Xml(value = "claim", track = true)
	private String claim = "";
	@Xml("position_profile_id")
	private int positionProfileId = -1;

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId == entityId;
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();

		switch (claim) {
		case "symbol":
			return artifact + " was made a symbol of the "
					+ World.getEntity(entityId).getPosition(positionProfileId).getName() + " by "
					+ World.getEntity(entityId).getLink();
		case "heirloom":
			return artifact + " was made a family heirloom by " + World.getHistoricalFigure(hfId).getLink();
		case "treasure":
			if (hfId != -1)
				return artifact + " was claimed by " + World.getHistoricalFigure(hfId).getLink();
			else
				return artifact + " was claimed by " + World.getEntity(entityId).getLink();
		default:
			return " artifact claim ";
		}
	}

}
