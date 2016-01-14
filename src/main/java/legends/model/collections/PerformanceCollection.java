package legends.model.collections;

import legends.model.collections.basic.EventCollection;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("performance,procession,ceremony,competition")
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
	public String getShortDescription() {
		return type;
	}
}
