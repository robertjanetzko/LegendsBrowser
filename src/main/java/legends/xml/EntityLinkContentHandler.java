package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityLink;
import legends.xml.handlers.XMLContentHandler;

public class EntityLinkContentHandler extends XMLContentHandler {

	EntityLink entityLink = new EntityLink();
	Consumer<EntityLink> handler;

	public EntityLinkContentHandler(String name, XMLReader xmlReader, Consumer<EntityLink> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("link_type","entity_id","link_strength");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "link_type":
			entityLink.setLinkType(value);
			break;
		case "entity_id":
			entityLink.setEntityId(Integer.parseInt(value));
			break;
		case "link_strength":
			entityLink.setLinkStrength(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(entityLink);
		entityLink = new EntityLink();
		super.popContentHandler();
	}
}
