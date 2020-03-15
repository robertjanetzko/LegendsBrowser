package legends.xml.converter;

import legends.model.events.basic.Rectangle;

public class RectangleConverter implements ValueConverter {
	@Override
	public Object convert(String value) {
		return new Rectangle(value);
	}
}
