package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.UndergroundRegion;
import legends.model.World;
import legends.xml.handlers.ElementContentHandler;

public class UndergroundRegionContentHandler extends ElementContentHandler<UndergroundRegion> {

	UndergroundRegion region = new UndergroundRegion();

	public UndergroundRegionContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "depth", "type","coords");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (!World.isPlusMode())
				region.setId(id);
			else {
				UndergroundRegion r = World.getUndergroundRegion(id);
				if (r != null && r.getId() != -1)
					region = r;
				else
					System.out.println("unknown underground region " + id);
			}
			break;
		case "depth":
			region.setDepth(Integer.parseInt(value));
			break;
		case "type":
			region.setType(value);
			break;
		case "coords":
			region.setCoords(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public UndergroundRegion getElement() {
		UndergroundRegion r = region;
		region = new UndergroundRegion();
		return r;
	}
}
