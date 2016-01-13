package legends.xml.converter;

import legends.model.events.basic.Coords;

public class CoordsConverter implements ValueConverter {
	@Override
	public Object convert(String value) {
		return new Coords(value);
	}
}
