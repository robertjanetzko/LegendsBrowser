package legends.model.events;

import legends.model.events.basic.Item;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("masterpiece item")
public class MasterpieceItemEvent extends MasterpieceEvent {
	@XmlComponent
	private Item item = new Item();

	@Override
	public String getCreation() {
		String item = this.item.getText();
		if (item.isEmpty())
			item = "item";
		return "a masterful " + item;
	}

}
