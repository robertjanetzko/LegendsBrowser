package legends.model.events.basic;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Coords {
	private int x;
	private int y;

	public Coords(String value) {
		String[] coords = value.split(",");
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static void readList(String value, Consumer<Coords> consumer) {
		Stream.of(value.split("\\|")).filter(s -> s.contains(",")).map(s -> new Coords(s)).forEach(consumer);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coords) {
			final Coords o = (Coords) obj;
			return x == o.x && y == o.y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return x << 16 + y;
	}

}
