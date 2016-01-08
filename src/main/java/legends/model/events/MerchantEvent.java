package legends.model.events;

import legends.model.events.basic.Event;

public class MerchantEvent extends Event {

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "ccc":
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		return "purge";
	}

}
