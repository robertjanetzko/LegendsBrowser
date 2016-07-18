package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.HfTravelEvent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("journey")
public class JourneyCollection extends EventCollection {
	private int calcHfId = -1;

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	@Override
	public void process() {
		super.process();

		if (calcHfId == -1)
			getAllHistoricalEvents().stream().filter(e -> e instanceof HfTravelEvent).map(e -> ((HfTravelEvent) e))
					.map(HfTravelEvent::getHfId).findFirst().ifPresent(this::setCalcHfId);
	}

	@Override
	public String getLink() {
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		return "the <a href=\"" + getUrl() + "\" class=\"collection journey\">" + getOrdinalString()
				+ "Journey</a> of " + hf;
	}

	@Override
	public String getShortDescription() {
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		return "the " + getOrdinalString() + "Journey of " + hf + " occurred";
	}

	public String getName() {
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getName();
		return "The " + getOrdinalString() + "Journey of " + hf;
	}
}
