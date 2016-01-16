package legends.model.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.model.HistoricalFigure;
import legends.model.MountainPeak;
import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlIgnorePlus;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf reach summit")
public class HfReachSummitEvent extends Event implements LocalEvent, HfRelatedEvent {
	@XmlIgnorePlus
	@Xml(value = "group,group_hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> group = new ArrayList<>();
	@XmlComponent
	private EventLocation location = new EventLocation();

	public List<Integer> getGroup() {
		return group;
	}

	public EventLocation getLocation() {
		return location;
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
		MountainPeak peak = World.getMountainPeaks().stream().filter(m -> m.getCoords().equals(location.getCoords()))
				.findFirst().orElse(World.UNKNOWN_MOUNTAIN_PEAK);

		return hfs.stream().collect(Collectors.joining(", ")) + " was the first to reach the summit of " + peak.getLink()
				+ " which rises above " + loc;
	}

}
