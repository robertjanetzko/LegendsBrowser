package legends.model.collections;

import legends.model.collections.basic.EventCollection;

public class PerformanceCollection extends EventCollection {
	private int civId = -1;
	private int occasionId = -1;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
//		case "civ_id":
//			setCivId(Integer.parseInt(value));
//			break;
//		case "occasion_id":
//			setOccasionId(Integer.parseInt(value));
//			break;

		default:
			return super.setProperty(property, value);
		}
//		return true;
	}

	@Override
	public String getShortDescription() {
		return type;
	}
}
