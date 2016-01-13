package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityPositionAssignment;
import legends.xml.handlers.XMLContentHandler;

public class EntityPositionAssignmentContentHandler extends XMLContentHandler {

	EntityPositionAssignment assignment = new EntityPositionAssignment();
	Consumer<EntityPositionAssignment> handler;

	public EntityPositionAssignmentContentHandler(String name, XMLReader xmlReader,
			Consumer<EntityPositionAssignment> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("id", "histfig", "position_id", "squad_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			assignment.setId(Integer.parseInt(value));
			break;
		case "histfig":
			assignment.setHfId(Integer.parseInt(value));
			break;
		case "position_id":
			assignment.setPositionId(Integer.parseInt(value));
			break;
		case "squad_id":
			assignment.setSquadId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	protected void popContentHandler() {
		handler.accept(assignment);
		assignment = new EntityPositionAssignment();
		super.popContentHandler();
	}
}
