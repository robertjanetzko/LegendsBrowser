package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf does interaction")
public class HfDoesInteractionEvent extends Event implements LocalEvent, HfRelatedEvent {
	@Xml("doer,doer_hfid")
	private int doerHfId = -1;
	@Xml("target,target_hfid")
	private int targetHfId = -1;
	@Xml("interaction")
	private String interaction;
	@Xml("interaction_action")
	private String interactionAction;
	@Xml("interaction_string")
	private String interactionString;
	@Xml("source")
	private int source = -1;
	@XmlComponent
	EventLocation location = new EventLocation();

	private static Set<String> interactions = new HashSet<>();

	public int getDoerHfId() {
		return doerHfId;
	}

	public void setDoerHfId(int doerHfId) {
		this.doerHfId = doerHfId;
	}

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getInteractionAction() {
		return interactionAction;
	}

	public void setInteractionAction(String interactionAction) {
		this.interactionAction = interactionAction;
	}

	public String getInteractionString() {
		return interactionString;
	}

	public void setInteractionString(String interactionString) {
		this.interactionString = interactionString;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return doerHfId == hfId || targetHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String doer = World.getHistoricalFigure(doerHfId).getLink();
		String target = World.getHistoricalFigure(targetHfId).getLink();

		if (interactionAction != null || interactionString != null) {
			String[] action = interactionAction.substring(1, interactionAction.length() - 1).split(":");
			String[] string = interactionString.substring(1, interactionString.length() - 1).split(":");

			String s1 = "";
			String s2 = "";
			if (action[0].equals("IS_HIST_STRING_1"))
				s1 = action[1];
			if (action[0].equals("IS_HIST_STRING_2")) {
				s1 = " bit ";
				s2 = action[1];
			}
			if (string[0].equals("IS_HIST_STRING_1"))
				s1 = string[1];
			if (string[0].equals("IS_HIST_STRING_2"))
				s2 = string[1];

			return doer + s1 + target + s2;
		}

		if (interaction.startsWith("DEITY_CURSE_WEREBEAST_"))
			if (interaction.endsWith("_BITE"))
				return doer + " bit " + target + ", passing on the UNKNOWN monster curse";
			else
				return doer + " cursed " + target + " to assume the form of a UNKNOWN-like monster every full moon";
		else if (interaction.startsWith("DEITY_CURSE_VAMPIRE_"))
			return doer + " cursed " + target + " to prowl the night in search of blood";
		else
			return doer + " cursed " + target + " " + interaction;
	}

	public static void printUnknownInteractions() {
		interactions.remove("DEITY_CURSE_VAMPIRE_");
		interactions.remove("DEITY_CURSE_WEREBEAST_");
		interactions.remove("DEITY_CURSE_WEREBEAST__BITE");

		if (interactions.size() > 0)
			System.out.println("unknown hf interactions: " + interactions);
	}

}
