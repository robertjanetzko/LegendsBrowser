package legends.model;

import legends.xml.annotation.Xml;

public class EntityPositionAssignment extends AbstractObject {
	@Xml("histfig")
	private int hfId = -1;
	@Xml("position_id")
	private int positionId = -1;
	@Xml("squad_id")
	private int squadId = -1;

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId(int squadId) {
		this.squadId = squadId;
	}

}
