package legends.model;

import legends.helper.EventHelper;

public class HistoricalEra {
	private String name;
	private int startYear;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	@Override
	public String toString() {
		return name+" ("+startYear+" - ?)";
	}

}
