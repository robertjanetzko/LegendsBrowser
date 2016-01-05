package legends.model;

public class EntitySquadLink {
	private int squadId = -1;
	private int squadPosition = -1;
	private int entityId = -1;
	private int startYear = -1;
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
