package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class ChangeHfJobEvent extends HfEvent implements LocalEvent {
	private EventLocation location = new EventLocation("the depths of the world");

	private String newJob = "UNKNOWN JOB";
	private String oldJob = "UNKNOWN JOB";

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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "new_job":
			setNewJob(value.replace("_", " "));
			break;
		case "old_job":
			setOldJob(value.replace("_", " "));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("in");
		if (oldJob.equals("standard"))
			return hf + " became a " + newJob + loc;
		else if (newJob.equals("standard"))
			return hf + " stopped being a " + oldJob + loc;
		else
			return hf + " gave up being a " + oldJob +" to become a "+newJob + loc;
	}

}
