package legends.model;

public class HistoricalFigureSkill {
	private String skill;
	private int totalIp;

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getTotalIp() {
		return totalIp;
	}

	public void setTotalIp(int totalIp) {
		this.totalIp = totalIp;
	}

	@Override
	public String toString() {
		return skill + " (" + totalIp + ")";
	}

}
