package legends.model.events;

import legends.model.events.basic.Item;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece food")
public class MasterpieceFoodEvent extends MasterpieceEvent {
	@XmlComponent
	private Item item = new Item();

	@Override
	public String getCreation() {
		String item = this.item.getText();
		if (item.equals(""))
			item = "<i>UNKNOWN FOOD</i>";
		return "a masterful " + item;
	}

}
