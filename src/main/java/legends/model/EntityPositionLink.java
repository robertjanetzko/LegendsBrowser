package legends.model;

public class EntityPositionLink {
	private int positionProfileId;
	private int entityId;
	private int startYear;

	public int getPositionProfileId() {
		return positionProfileId;
	}

	public void setPositionProfileId(int positionProfileId) {
		this.positionProfileId = positionProfileId;
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

	@Override
	public String toString() {
		return startYear+"-? "+positionProfileId+" of "+entityId;
	}

}
