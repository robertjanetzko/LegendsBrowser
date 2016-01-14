package legends.model;

import legends.xml.annotation.Xml;

public class EntitySquadLink {
	@Xml("squad_id")
	private int squadId = -1;
	@Xml("squad_position")
	private int squadPosition = -1;
	@Xml("entity_id")
	private int entityId = -1;
	@Xml("start_year")
	private int startYear = -1;
	@Xml("end_year")
	private int endYear = -1;

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId(int squadId) {
		this.squadId = squadId;
	}

	public int getSquadPosition() {
		return squadPosition;
	}

	public void setSquadPosition(int squadPosition) {
		this.squadPosition = squadPosition;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

}
