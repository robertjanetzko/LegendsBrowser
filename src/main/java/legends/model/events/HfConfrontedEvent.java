package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf confronted")
public class HfConfrontedEvent extends HfEvent implements LocalEvent {
	@Xml("situation")
	private String situation;
	@Xml("reason")
	private String reason;
	@XmlComponent
	private EventLocation location = new EventLocation("");

	private static Set<String> situations = new HashSet<>();
	private static Set<String> reasons = new HashSet<>();

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String loc = location.getLink("in");
		switch (reason) {
		case "murder":
			return hf + " aroused " + situation + loc + " after a murder";
		case "ageless":
			return hf + " aroused " + situation + loc + " after appearing not to age";
		default:
			return hf + " aroused " + situation + loc + " after " + reason;
		}
	}

	public static void printUnknownSituations() {
		situations.remove("general suspicion");

		if (situations.size() > 0)
			LOG.debug("unknown hf confronted situations: " + situations);
	}

	public static void printUnknownReasons() {
		reasons.remove("murder");
		reasons.remove("ageless");

		if (reasons.size() > 0)
			LOG.debug("unknown hf confronted reasons: " + reasons);
	}

}
