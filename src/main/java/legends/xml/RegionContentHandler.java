package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Region;
import legends.model.Site;
import legends.model.World;
import legends.xml.handlers.ElementContentHandler;

public class RegionContentHandler extends ElementContentHandler<Region> {

	Region region = new Region();

	public RegionContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "type","coords");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				Region r = World.getRegion(id);
				if (r != null && r.getId() != -1)
					region = r;
			}
			region.setId(id);
			break;
		case "name":
			region.setName(value);
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
	public Region getElement() {
		Region r = region;
		region = new Region();
		return r;
	}
}
