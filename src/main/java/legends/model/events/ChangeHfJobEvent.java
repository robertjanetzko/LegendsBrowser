package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("change hf job")
public class ChangeHfJobEvent extends HfEvent implements LocalEvent {
	@Xml("new_job")
	private String newJob = "UNKNOWN JOB";
	@Xml("old_job")
	private String oldJob = "UNKNOWN JOB";

	@XmlComponent
	private EventLocation location = new EventLocation("the depths of the world");

	public String getNewJob() {
		return newJob;
	}

	public void setNewJob(String newJob) {
		this.newJob = newJob;
	}

	public String getOldJob() {
		return oldJob;
	}

	public void setOldJob(String oldJob) {
		this.oldJob = oldJob;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("in");
		if (oldJob.equals("standard"))
			return hf + " became a " + newJob.replace("_", " ") + loc;
		else if (newJob.equals("standard"))
			return hf + " stopped being a " + oldJob.replace("_", " ") + loc;
		else
			return hf + " gave up being a " + oldJob.replace("_", " ") + " to become a " + newJob.replace("_", " ") + loc;
	}

}
