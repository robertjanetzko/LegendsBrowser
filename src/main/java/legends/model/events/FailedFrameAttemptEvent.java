package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("failed frame attempt")
public class FailedFrameAttemptEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	@Xml("target_hfid")
	private int targetHfId = -1;
	@Xml("convicter_enid")
	private int convicterEnId = -1;
	@Xml("plotter_hfid")
	private int plotterHfId = -1;
	@Xml("fooled_hfid")
	private int fooledHfId = -1;
	@Xml("framer_hfid")
	private int framerHfId = -1;
	@Xml(value = "crime", track = true)
	private String crime;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return targetHfId == hfId || plotterHfId == hfId || fooledHfId == hfId || framerHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return convicterEnId == entityId;
	}

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public int getConvicterEnId() {
		return convicterEnId;
	}

	public void setConvicterEnId(int convicterEnId) {
		this.convicterEnId = convicterEnId;
	}

	public int getPlotterHfId() {
		return plotterHfId;
	}

	public void setPlotterHfId(int plotterHfId) {
		this.plotterHfId = plotterHfId;
	}

	public int getFooledHfId() {
		return fooledHfId;
	}

	public void setFooledHfId(int fooledHfId) {
		this.fooledHfId = fooledHfId;
	}

	public int getFramerHfId() {
		return framerHfId;
	}

	public void setFramerHfId(int framerHfId) {
		this.framerHfId = framerHfId;
	}

	public String getCrime() {
		return crime;
	}

	public void setCrime(String crime) {
		this.crime = crime;
	}

	@Override
	public String getShortDescription() {
		return String.format(
				"%s attempted to frame %s for %s%s by fooling %s and %s with fabricated evidence, but nothing came of it.",
				World.getHistoricalFigure(framerHfId).getLink(), World.getHistoricalFigure(targetHfId).getLink(), crime,
				plotterHfId != -1
						? String.format(" at the behest of %s", World.getHistoricalFigure(plotterHfId).getLink())
						: "",
				World.getHistoricalFigure(fooledHfId).getLink(), World.getEntity(convicterEnId).getLink());
	}

}
