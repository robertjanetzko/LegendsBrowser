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
	
	private List<Coords> cacheOutline = null;

	public List<Coords> getOutline() {
		
		if (cacheOutline != null)
			return cacheOutline;

		/* draw the region in a matrix */
		final int N = Math.max(World.getMapTileWidth(), World.getMapTileHeight()) + 1;
		final boolean region[][] = new boolean[N+2][N+2];
		for (Coords c : coords) {
			region[c.getX()+1][c.getY()+1] = true;
		}
		
		/* starting point */
		int x0 = coords.get(0).getX() + 1;
		int y0 = coords.get(0).getY() + 1;
		int x = x0, y = y0;
		
		/* current and previous direction */
		char curdir = 'n';
		if (coords.size() == 1 || coords.get(0).getX() == coords.get(1).getX()-1)
			curdir = 'e';
		char prevdir = 0;

		List<Coords> line = new LinkedList<>();
		
		/* follow the outline by keeping the right hand inside */
		while (x != x0 || y != y0 || prevdir == 0) {
			prevdir = curdir;
			if (curdir == 'n' && y>1) {
				y -= 1;
				if (region[x-1][y-1])
					curdir = 'w';
				else if (! region[x][y-1])
		    	    curdir = 'e';
			}
			else if (curdir == 's' && y<N) {
				y += 1;	
				if (region[x][y])
					curdir = 'e';	
				else if (! region[x-1][y])
					curdir = 'w';
			}
			else if (curdir == 'w' && x>1) {
				x -= 1;
				if (region[x-1][y])
					curdir = 's';
				else if (! region[x-1][y-1])
					curdir = 'n';
			}
			else if (curdir == 'e' && x<N) {
				x += 1;
				if (region[x][y-1])
					curdir = 'n';
				else if (! region[x][y])
					curdir = 's';
			}
			if (curdir != prevdir) {
				/* change of direction: record point */
				line.add(new Coords(x-1, y-1));
				if (line.size() > 256 * 10)
					break;
			}
		}
		
		cacheOutline = line;
		return line;
	}

}
