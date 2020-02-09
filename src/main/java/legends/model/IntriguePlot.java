package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;
import java.util.ArrayList;
import java.util.List;

public class IntriguePlot extends AbstractObject {
	@Xml("local_id")
	private int localId = -1;
	@Xml("type")
	private String type;
	@Xml("on_hold")
	private boolean onHold;
	@Xml("actor_id")
	private int localActorId = -1;
	@Xml("delegated_plot_hfid")
	private int delegatedPlotHfid = -1;
	@Xml("delegated_plot_id")
	private int delegatedPlotId = -1;
	@Xml("parent_plot_hfid")
	private int parentPlotHfid = -1;
	@Xml("parent_plot_id")
	private int parentPlotId = -1;
	@Xml("artifact_id")
	private int artifactId = -1;
	@Xml("entity_id")
	private int entityId = -1;
	@Xml(value = "plot_actor", elementClass = IntriguePlotActor.class, multiple = true)
	private List<IntriguePlotActor> plotActors = new ArrayList<>();
	

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOnHold() {
		return onHold;
	}

	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}
	
	public int getLocalActorId() {
		return localActorId;
	}

	public void setLocalActorId(int localActorId) {
		this.localActorId = localActorId;
	}

	public int getDelegatedPlotHfid() {
		return delegatedPlotHfid;
	}

	public void setDelegatedPlotHfid(int delegatedPlotHfid) {
		this.delegatedPlotHfid = delegatedPlotHfid;
	}
	
	public int getDelegatedPlotId() {
		return delegatedPlotId;
	}

	public void setDelegatedPlotId(int delegatedPlotId) {
		this.delegatedPlotId = delegatedPlotId;
	}
	
	public int getParentPlotHfid() {
		return parentPlotHfid;
	}

	public void setParentPlotHfid(int parentPlotHfid) {
		this.parentPlotHfid = parentPlotHfid;
	}
	
	public int getParentPlotId() {
		return parentPlotId;
	}

	public void setParentPlotId(int parentPlotId) {
		this.parentPlotId = parentPlotId;
	}
	
	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}
	
	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public List<IntriguePlotActor> getPlotActors() {
		return plotActors;
	}

}
