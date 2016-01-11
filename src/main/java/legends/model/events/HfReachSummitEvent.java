package legends.model.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class HfReachSummitEvent extends Event implements LocalEvent, HfRelatedEvent {
	private List<Integer> group = new ArrayList<>();

	private EventLocation location = new EventLocation();

	public List<Integer> getGroup() {
		return group;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "group":
			break;
		case "group_hfid":
			getGroup().add(Integer.parseInt(value));
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
		return group.contains(hfId);
	}

	@Override
	public String getShortDescription() {
		String loc = location.getLink("");
		List<String> hfs = group.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink)
				.collect(Collectors.toList());
		return hfs.stream().collect(Collectors.joining(", "))
				+ " was the first to reach the summit of UNKNOWN MOUNTAIN which rises above " + loc;
	}

}
