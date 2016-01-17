package legends.model.events;

import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece arch constructed")
public class MasterpieceArchConstructed extends MasterpieceEvent {
	@Xml("building_type")
	private String buildingType;
	@Xml("building_custom")
	private String buildingCustom;

	@Override
	public String getAction() {
		return "constructed " + getCreation();
	}

	@Override
	public String getCreation() {
		return "a masterful " + (buildingType != null ? buildingType : "<i>UNKNOWN BUILDING</i>");
	}

}
