package legends.model.events;

import legends.model.events.basic.Item;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece dye")
public class MasterpieceDyeEvent extends MasterpieceEvent {
	@XmlComponent
	private Item item = new Item();
	@Xml("dye_mat")
	private String dyeMat;
	@Xml("dye_mat_index")
	private int dyeMatIndex = -1;

	@Override
	public String getAction() {
		return "masterfully dyed " + getCreation();
	}

	@Override
	public String getCreation() {
		String item = this.item.getText();
		if (item.equals(""))
			item = "<i>UNKNOWN ITEM</i>";
		return "a " + item + " with " + (dyeMat != null ? dyeMat : "<i>UNKNOWN DYE</i>");
	}

}
