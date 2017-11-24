package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("artifact given")
public class ArtifactGivenEvent extends Event implements ArtifactRelatedEvent, HfRelatedEvent, EntityRelatedEvent {
	@Xml("artifact_id")
	private int artifactId;
	@Xml("giver_hist_figure_id")
	private int giverHfId;
	@Xml("giver_entity_id")
	private int giverEntityId;
	@Xml("receiver_hist_figure_id")
	private int receiverHfId;
	@Xml("receiver_entity_id")
	private int receiverEntityId;

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getGiverHfId() {
		return giverHfId;
	}

	public void setGiverHfId(int giverHfId) {
		this.giverHfId = giverHfId;
	}

	public int getGiverEntityId() {
		return giverEntityId;
	}

	public void setGiverEntityId(int giverEntityId) {
		this.giverEntityId = giverEntityId;
	}

	public int getReceiverHfId() {
		return receiverHfId;
	}

	public void setReceiverHfId(int receiverHfId) {
		this.receiverHfId = receiverHfId;
	}

	public int getReceiverEntityId() {
		return receiverEntityId;
	}

	public void setReceiverEntityId(int receiverEntityId) {
		this.receiverEntityId = receiverEntityId;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.giverEntityId == entityId || this.receiverEntityId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.giverHfId == hfId || this.receiverHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String artifact = World.getArtifact(artifactId).getLink();

		String giver = giverHfId != -1 ? World.getHistoricalFigure(giverHfId).getLink()
				: World.getEntity(giverEntityId).getLink();
		String receiver = World.getHistoricalFigure(receiverHfId).getLink()
				+ (receiverEntityId != -1 ? " of " + World.getEntity(receiverEntityId).getLink() : "");

		return artifact + " was offered to " + receiver + " by " + giver;
	}

}
