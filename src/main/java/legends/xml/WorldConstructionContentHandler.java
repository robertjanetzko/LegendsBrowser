package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.World;
import legends.model.WorldConstruction;
import legends.xml.handlers.ElementContentHandler;

public class WorldConstructionContentHandler extends ElementContentHandler<WorldConstruction> {

	WorldConstruction wc = new WorldConstruction();

	public WorldConstructionContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "type", "coords");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				WorldConstruction r = World.getWorldConstruction(id);
				if (r != null)
					wc = r;
			}
			wc.setId(id);
			break;
		case "name":
			wc.setName(value);
			break;
		case "type":
			wc.setType(value);
			break;
		case "coords":
			wc.setCoords(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public WorldConstruction getElement() {
		WorldConstruction w = wc;
		wc = new WorldConstruction();
		return w;
	}
}
