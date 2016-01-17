package legends.model.events;

import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece engraving")
public class MasterpieceEngravingEvent extends MasterpieceEvent {
	@XmlComponent
	private Art art = new Art();

	@Override
	public String getCreation() {
		return "a masterful engraving \"" + art.getLink() + "\"";
	}

}
