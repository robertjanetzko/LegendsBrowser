package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class IntrigueActor extends AbstractObject {
	@Xml("local_id")
	private int localId = -1;
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("hfid")
	private int hfid = -1;
	@Xml("role")
	private String role;
	@Xml("strategy")
	private String strategy;

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getHfid() {
		return hfid;
	}

	public void setHfid(int hfid) {
		this.hfid = hfid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getLink() {
		return String.format("%s - %s - %s",
				entityId != -1 ? World.getEntity(entityId).getLink() : World.getHistoricalFigure(hfid).getLink(), role,
				strategy);
	}

}
