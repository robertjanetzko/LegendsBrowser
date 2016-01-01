package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.UndergroundRegion;
import legends.xml.handlers.ElementContentHandler;

public class UndergroundRegionContentHandler extends ElementContentHandler<UndergroundRegion> {

	UndergroundRegion region = new UndergroundRegion();

	public UndergroundRegionContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id","depth","type");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			region.setId(Integer.parseInt(value));
			break;
		case "depth":
			region.setDepth(Integer.parseInt(value));
			break;
		case "type":
			region.setType(value);
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
