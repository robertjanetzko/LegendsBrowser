package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class HfDoesInteractionEvent extends Event implements HfRelatedEvent {
	private int doerHfId = -1;
	private int targetHfId = -1;
	private String interaction;

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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "doer_hfid":
			setDoerHfId(Integer.parseInt(value));
			break;
		case "target_hfid":
			setTargetHfId(Integer.parseInt(value));
			break;
		case "interaction":
			interactions.add(value.replaceAll("[0-9]", ""));
			setInteraction(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return doerHfId == hfId || targetHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String doer = World.getHistoricalFigure(doerHfId).getLink();
		String target = World.getHistoricalFigure(targetHfId).getLink();
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
