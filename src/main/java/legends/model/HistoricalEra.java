package legends.model;

import legends.helper.EventHelper;
import legends.xml.annotation.Xml;

public class HistoricalEra {
	@Xml("name")
	private String name;
	@Xml("start_year")
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
		return name + " (" + startYear + " - ?)";
	}

}
