package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class HfAbductedEvent extends Event implements LocalEvent, HfRelatedEvent {
	private int targetHfId;
	private int snatcherHfId;

	private EventLocation location = new EventLocation();

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public int getSnatcherHfId() {
		return snatcherHfId;
	}

	public void setSnatcherHfId(int snatcherHfId) {
		this.snatcherHfId = snatcherHfId;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "target_hfid":
			setTargetHfId(Integer.parseInt(value));
			break;
		case "snatcher_hfid":
			setSnatcherHfId(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}
	
	@Override
	public boolean isRelatedToHf(int hfId) {
		return targetHfId == hfId || snatcherHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String target = World.getHistoricalFigure(getTargetHfId()).getLink();
		String snatcher = World.getHistoricalFigure(getSnatcherHfId()).getLink();
		String loc = location.getLink("from");
		return target + " was abducted"+loc+" by " + snatcher;
	}

}
