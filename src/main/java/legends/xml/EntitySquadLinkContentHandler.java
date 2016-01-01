package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntitySquadLink;
import legends.xml.handlers.XMLContentHandler;

public class EntitySquadLinkContentHandler extends XMLContentHandler {

	EntitySquadLink entityLink = new EntitySquadLink();
	Consumer<EntitySquadLink> handler;

	public EntitySquadLinkContentHandler(String name, XMLReader xmlReader, Consumer<EntitySquadLink> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("squad_id","squad_position","entity_id","start_year");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "squad_id":
			entityLink.setSquadId(Integer.parseInt(value));
			break;
		case "squad_position":
			entityLink.setSquadPosition(Integer.parseInt(value));
			break;
		case "entity_id":
			entityLink.setEntityId(Integer.parseInt(value));
			break;
		case "start_year":
			entityLink.setStartYear(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(entityLink);
		entityLink = new EntitySquadLink();
		super.popContentHandler();
	}
}
