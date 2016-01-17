package legends.model.events;

import legends.model.events.basic.Item;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece item improvement")
public class MasterpieceItemImprovementEvent extends MasterpieceEvent {
	@XmlComponent
	private Item item = new Item();
	@Xml("improvement_type")
	private String impType;
	@Xml("improvement_subtype")
	private String impSubtype;
	@Xml("imp_mat")
	private String impMat;
	@XmlComponent
	private Art art = new Art();

	@Override
	public String getAction() {
		return "added " + getCreation();
	}

	@Override
	public String getCreation() {
		String item = this.item.getText();
		if (item.equals(""))
			item = "<i>UNKNOWN ITEM</i>";
		if (impType == null)
			return "<i>UNKNOWN IMPROVEMENT</i> to a " + item;
		if (impType.equals("art_image"))
			return "a masterful image \"" + art.getLink() + "\" in " + impMat + " to a " + item;
		else
			return "masterful " + impType + " in " + impMat + " to a " + item;
	}
}
