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
	private String role = "no role";
	@Xml("strategy")
	private String strategy = "no strategy";
	@Xml("handle_actor_id")
	private int handlerLocalId = -1;
	@Xml("strategy_enid")
	private int strategyEntityId = -1;
	@Xml("strategy_eppid")
	private int strategyEntityPositionId = -1;
	@Xml("promised_actor_immortality")
	private boolean promisedActorImmortality;
	@Xml("promised_me_immortality")
	private boolean promisedMeImmortality;

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
	
	public int getHandlerLocalId() {
		return handlerLocalId;
	}

	public void setHandlerLocalId(int handlerLocalId) {
		this.handlerLocalId = handlerLocalId;
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
	
	public int getStrategyEntityPositionId() {
		return strategyEntityPositionId;
	}

	public void setStrategyEntityPositionId(int strategyEntityPositionId) {
		this.strategyEntityPositionId = strategyEntityPositionId;
	}
	
	public int getStrategyEntityId() {
		return strategyEntityId;
	}

	public void setStrategyEntityId(int strategyEntityId) {
		this.strategyEntityId = strategyEntityId;
	}
	
	public boolean getPromisingImmortality() {
		return promisedActorImmortality;
	}

	public void setPromisingImmortality(boolean promisedActorImmortality) {
		this.promisedActorImmortality = promisedActorImmortality;
	}
	
	public boolean getPromisedImmortality() {
		return promisedMeImmortality;
	}

	public void setPromisedImmortality(boolean promisedMeImmortality) {
		this.promisedMeImmortality = promisedMeImmortality;
	}

	public String getLink() {
		String position = "";
		if(strategyEntityPositionId>0) {
			position = String.format(", position %s of %s", strategyEntityPositionId, World.getEntity(strategyEntityId).getLink());
		}
		String immortality = "";
		if (promisedActorImmortality) {
			immortality = " - promising immortality";
		}
		if (promisedMeImmortality) {
			immortality = " - promised immortality";
		}
		return String.format("%s - %s - %s",
				entityId != -1 ? World.getEntity(entityId).getLink() : World.getHistoricalFigure(hfid).getLink() + position, role,
				strategy + immortality);
	}

}
