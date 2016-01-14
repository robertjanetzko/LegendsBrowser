package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("insurrection started")
public class InsurrectionStartedEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	@Xml("target_civ_id")
	private int targetCivId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("outcome")
	private String outcome;

	public int getTargetCivId() {
		return targetCivId;
	}

	public void setTargetCivId(int targetCivId) {
		this.targetCivId = targetCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	private static Set<String> outcomes = new HashSet<>();

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return targetCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(targetCivId).getLink();
		String site = World.getSite(siteId).getLink();
		if (outcome == null)
			return "an insurrection against " + civ + " began in " + site;
		else
			switch (outcome) {
			case "population gone":
				return "an insurrection in " + site + " against " + civ
						+ " ended with the disappearance of the rebelling population";
			case "leadership overthrown":
				return "the insurrection in " + site + " concluded with " + civ + " overthrown";
			default:
				return "an insurrection in " + site + " against " + civ + " ended because: " + outcome;
			}
	}

	public static void printUnknownOutcomes() {
		outcomes.remove("population gone");
		outcomes.remove("leadership overthrown");
		if (outcomes.size() > 0)
			System.out.println("unknown insurrection outcomes: " + outcomes);
	}
}
