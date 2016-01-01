package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityFormerPositionLink;
import legends.xml.handlers.XMLContentHandler;

public class EntityFormerPositionLinkContentHandler extends XMLContentHandler {

	EntityFormerPositionLink entityLink = new EntityFormerPositionLink();
	Consumer<EntityFormerPositionLink> handler;

	public EntityFormerPositionLinkContentHandler(String name, XMLReader xmlReader, Consumer<EntityFormerPositionLink> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("position_profile_id","entity_id","start_year","end_year");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "position_profile_id":
			entityLink.setPositionProfileId(Integer.parseInt(value));
			break;
		case "entity_id":
			entityLink.setEntityId(Integer.parseInt(value));
			break;
		case "start_year":
			entityLink.setStartYear(Integer.parseInt(value));
			break;
		case "end_year":
			entityLink.setEndYear(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(entityLink);
		entityLink = new EntityFormerPositionLink();
		super.popContentHandler();
	}
}
