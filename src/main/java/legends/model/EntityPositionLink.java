package legends.model;

public class EntityPositionLink {
	private int positionProfileId;
	private int entityId;
	private int startYear = -1;
	private int endYear = -1;

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

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	public String getPosition(HistoricalFigure hf) {
		Entity e = World.getEntity(entityId);
		EntityPositionAssignment a = e.getAssignment(positionProfileId);
		if(a == null)
			return ""+positionProfileId;
		EntityPosition p = e.getPosition(a.getPositionId());
		if(p == null)
			return ""+positionProfileId;
		return p.getName();
	}

	@Override
	public String toString() {
		return startYear+"-"+endYear+" "+positionProfileId+" of "+entityId;
	}

}
