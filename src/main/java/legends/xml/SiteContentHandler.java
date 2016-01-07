package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Site;
import legends.model.Structure;
import legends.model.World;
import legends.model.WorldConstruction;
import legends.xml.handlers.ElementContentHandler;
import legends.xml.handlers.ListContentHandler;

public class SiteContentHandler extends ElementContentHandler<Site> {

	Site site = new Site();

	public SiteContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "type", "coords");
		registerContentHandler(new ListContentHandler<Structure>("structures", xmlReader,
				new StructureContentHandler("structure", xmlReader), l -> site.setStructures(l)));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				Site r = World.getSite(id);
				if (r != null && r.getId() != -1)
					site = r;
			}
			site.setId(id);
			break;
		case "name":
			site.setName(value);
			break;
		case "type":
			site.setType(value);
			break;
		case "coords":
			String[] coords = value.split(",");
			site.setX(Integer.parseInt(coords[0]));
			site.setY(Integer.parseInt(coords[1]));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public Site getElement() {
		Site s = site;
		site = new Site();
		return s;
	}
}
