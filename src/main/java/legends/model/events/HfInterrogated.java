package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf interrogated")
public class HfInterrogated extends Event implements HfRelatedEvent, EntityRelatedEvent {
	@Xml("target_hfid")
	private int targetHfId;
	@Xml("interrogator_hfid")
	private int interrogatorHfId;
	@Xml("arresting_enid")
	private int arrestingEnId;
	@Xml("wanted_and_recognized")
	private boolean wantedAndRecognized;
	@Xml("held_firm_in_interrogation")
	private boolean heldFirmInInterrogation;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return targetHfId == hfId || interrogatorHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return arrestingEnId == entityId;
	}

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public int getInterrogatorHfId() {
		return interrogatorHfId;
	}

	public void setInterrogatorHfId(int interrogatorHfId) {
		this.interrogatorHfId = interrogatorHfId;
	}

	public int getArrestingEnId() {
		return arrestingEnId;
	}

	public void setArrestingEnId(int arrestingEnId) {
		this.arrestingEnId = arrestingEnId;
	}

	public boolean isWantedAndRecognized() {
		return wantedAndRecognized;
	}

	public void setWantedAndRecognized(boolean wantedAndRecognized) {
		this.wantedAndRecognized = wantedAndRecognized;
	}

	public boolean isHeldFirmInInterrogation() {
		return heldFirmInInterrogation;
	}

	public void setHeldFirmInInterrogation(boolean heldFirmInInterrogation) {
		this.heldFirmInInterrogation = heldFirmInInterrogation;
	}

	@Override
	public String getShortDescription() {
		if (wantedAndRecognized && heldFirmInInterrogation)
			return String.format(
					"%s was recognized and arrested by %s. Despite the interrogation by %s, %s refused to reveal anything and was released",
					World.getHistoricalFigure(targetHfId).getLink(), World.getEntity(arrestingEnId).getLink(),
					World.getHistoricalFigure(interrogatorHfId).getLink(),
					World.getHistoricalFigure(targetHfId).getShortLink());
		else
			return String.format("%s was interrogated", World.getHistoricalFigure(targetHfId).getLink());
	}

}
