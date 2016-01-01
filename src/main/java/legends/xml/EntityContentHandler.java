package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Entity;
import legends.xml.handlers.ElementContentHandler;

public class EntityContentHandler extends ElementContentHandler<Entity> {

	Entity entity = new Entity();

	public EntityContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id","name");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			entity.setId(Integer.parseInt(value));
			break;
		case "name":
			entity.setName(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public Entity getElement() {
		Entity a = entity;
		entity = new Entity();
		return a;
	}
}
