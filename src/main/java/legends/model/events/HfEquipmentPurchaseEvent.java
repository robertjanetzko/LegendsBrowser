package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf equipment purchase")
public class HfEquipmentPurchaseEvent extends Event implements HfRelatedEvent {
	@Xml("group_hfid")
	private int groupHfId = -1;
	@Xml(value = "quality", track = true)
	private int quality = -1;

	@Override
	public boolean isRelatedToHf(int hfId) {
		return groupHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		return String.format("%s purchased %s equipment", World.getHistoricalFigure(groupHfId).getLink(),
				getQualityString());
	}

	public String getQualityString() {
		switch (quality) {
		case 1:
			return "well-crafted";
		case 2:
			return "finely-crafted";
		case 3:
			return "superior quality";
		case 4:
			return "exceptional";

		default:
			return "UNKNOWN QUALITY " + quality;
		}
	}

}
