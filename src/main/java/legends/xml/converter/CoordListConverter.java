package legends.xml.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import legends.model.events.basic.Coords;

public class CoordListConverter implements ValueConverter {
	@Override
	public Object convert(String value) {
		return Stream.of(value.split("\\|")).filter(s -> s.contains(",")).map(s -> new Coords(s))
				.collect(Collectors.toList());
	}
}
