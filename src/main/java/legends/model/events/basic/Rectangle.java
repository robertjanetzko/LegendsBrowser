package legends.model.events.basic;

public class Rectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Rectangle(String value) {
		String[] coords = value.split("[,:]");
		x1 = Integer.parseInt(coords[0]);
		y1 = Integer.parseInt(coords[1]);
		x2 = Integer.parseInt(coords[2]);
		y2 = Integer.parseInt(coords[3]);
	}
	
	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}
	
	public int getWidth() {
		return x2 - x1;
	}

	public int getHeight() {
		return y2 - y1;
	}
}
