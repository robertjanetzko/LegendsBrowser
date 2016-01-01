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

public class HfReunionEvent extends Event implements LocalEvent, HfRelatedEvent {
	private int group1HfId = -1;
	private List<Integer> group2HfIds = new ArrayList<>();

	private EventLocation location = new EventLocation();

	public int getGroup1HfId() {
		return group1HfId;
	}

	public void setGroup1HfId(int group1HfId) {
		this.group1HfId = group1HfId;
	}

	public List<Integer> getGroup2HfIds() {
		return group2HfIds;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "group_1_hfid":
			setGroup1HfId(Integer.parseInt(value));
			break;
		case "group_2_hfid":
			getGroup2HfIds().add(Integer.parseInt(value));
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
		return group1HfId == hfId || group2HfIds.contains(hfId);
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(group1HfId).getLink();
		List<String> hfs = group2HfIds.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink)
				.collect(Collectors.toList());
		if (hfs.size() == 1)
			return hf + " was reunited with " + hfs.get(0);
		else {
			String last = hfs.remove(hfs.size()-1);
			return hf + " was reunited with " + hfs.stream().collect(Collectors.joining(", "))+" and "+last;
		}
	}

}
