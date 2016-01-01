package legends.model.events.basic;

import legends.helper.EventHelper;

public abstract class Event {
	protected int id;
	protected int year;
	protected int seconds;
	protected String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean setProperty(String property, String value) {
		switch (property) {
		case "id":
			setId(Integer.parseInt(value));
			break;
		case "year":
			setYear(Integer.parseInt(value));
			break;
		case "seconds72":
			setSeconds(Integer.parseInt(value));
			break;
		case "type":
			setType(value);
			break;

		default:
			if (!property.equals("historical_event"))
				System.err.println(id+" "+year+" unknown property: " + property + " = " + value+" "+this);
			return false;
		}
		return true;
	}
	
	public void process() {
		
	}
	
	public String getDescription() {
		return getShortDescription();
	}
	
	public String getShortDescription() {
		return "["+id+"] "+type;
	}
	
	public String getSentence() {
		return EventHelper.capitalize(getShortDescription());
	}

}
