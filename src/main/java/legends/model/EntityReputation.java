package legends.model;

public class EntityReputation {
	private int entityId;
	private int firstAgelessYear;
	private int firstAgelessSeasonCount;
	private int unsolvedMurders;

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getFirstAgelessYear() {
		return firstAgelessYear;
	}

	public void setFirstAgelessYear(int firstAgelessYear) {
		this.firstAgelessYear = firstAgelessYear;
	}

	public int getFirstAgelessSeasonCount() {
		return firstAgelessSeasonCount;
	}

	public void setFirstAgelessSeasonCount(int firstAgelessSeasonCount) {
		this.firstAgelessSeasonCount = firstAgelessSeasonCount;
	}

	public int getUnsolvedMurders() {
		return unsolvedMurders;
	}

	public void setUnsolvedMurders(int unsolvedMurders) {
		this.unsolvedMurders = unsolvedMurders;
	}

}
