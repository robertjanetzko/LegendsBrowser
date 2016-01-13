package legends.model.events.basic;

import legends.helper.EventHelper;
import legends.model.AbstractObject;
import legends.model.collections.basic.EventCollection;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtypes;

@XmlSubtypes("type")
public class Event extends AbstractObject {
	@Xml("year")
	protected int year;
	@Xml("seconds72")
	protected int seconds;
	@Xml("type")
	protected String type;

	protected EventCollection collection;

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

	public EventCollection getCollection() {
		return collection;
	}

	public void setCollection(EventCollection collection) {
		this.collection = collection;
	}

	public void process() {

	}

	public String getDescription() {
		return getShortDescription();
	}

	public String getShortDescription() {
		return "[" + id + "] " + type;
	}

	public String getSentence() {
		return EventHelper.capitalize(getShortDescription());
	}

}
