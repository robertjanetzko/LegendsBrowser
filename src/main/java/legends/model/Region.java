package legends.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.model.events.basic.Coords;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordListConverter;

public class Region extends AbstractObject {
	@Xml("name")
	private String name;

	@Xml("type")
	private String type;

	@Xml("coords")
	@XmlConverter(CoordListConverter.class)
	private List<Coords> coords = new ArrayList<>();

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Coords> getCoords() {
		return coords;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")";
	}

	public String getURL() {
		return Application.getSubUri() + "/region/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"region\">" + getName() + "</a>";
	}
	
	public String getMapDescription() {
		return getLink() + "<br/><span>" + type + "</span>";
	}

	class Line {
		Coords p1, p2;

		public Line(Coords p1, Coords p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		@Override
		public String toString() {
			return p1 + " - " + p2 + "\n";
		}
	}

	public List<Coords> getOutline() {
		List<Line> lines = new LinkedList<>();

		for (Coords c : coords) {
			Coords top = new Coords(c.getX(), c.getY() + 1);
			if (!coords.contains(top))
				lines.add(new Line(new Coords(c.getX(), c.getY() + 1), new Coords(c.getX() + 1, c.getY() + 1)));

			Coords left = new Coords(c.getX() - 1, c.getY());
			if (!coords.contains(left))
				lines.add(new Line(new Coords(c.getX(), c.getY()), new Coords(c.getX(), c.getY() + 1)));

			Coords bottom = new Coords(c.getX(), c.getY() - 1);
			if (!coords.contains(bottom))
				lines.add(new Line(new Coords(c.getX(), c.getY()), new Coords(c.getX() + 1, c.getY())));

			Coords right = new Coords(c.getX() + 1, c.getY());
			if (!coords.contains(right))
				lines.add(new Line(new Coords(c.getX() + 1, c.getY()), new Coords(c.getX() + 1, c.getY() + 1)));
		}

		List<Coords> line = new LinkedList<>();
		
		if (lines.size() > 0) {
			Line start = lines.remove(0);
			line.add(start.p1);
			Coords next = start.p2;
			while (!next.equals(start.p1)) {
				line.add(next);

				for (Line l : lines) {
					if (l.p1.equals(next)) {
						next = l.p2;
						lines.remove(l);
						break;
					} else if (l.p2.equals(next)) {
						next = l.p1;
						lines.remove(l);
						break;
					}
				}
			}
		}

		return line;
	}

}
