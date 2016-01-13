package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityPosition;
import legends.xml.handlers.XMLContentHandler;

public class EntityPositionContentHandler extends XMLContentHandler {

	EntityPosition position = new EntityPosition();
	Consumer<EntityPosition> handler;

	public EntityPositionContentHandler(String name, XMLReader xmlReader, Consumer<EntityPosition> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("id", "name", "name_male", "name_female", "spouse", "spouse_male", "spouse_female");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			position.setId(Integer.parseInt(value));
			break;
		case "name":
			position.setName(value);
			break;
		case "name_male":
			position.setNameMale(value);
			break;
		case "name_female":
			position.setNameFemale(value);
			break;
		case "spouse":
			position.setSpouse(value);
			break;
		case "spouse_male":
			position.setSpouseMale(value);
			break;
		case "spouse_female":
			position.setSpouseFemale(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	protected void popContentHandler() {
		handler.accept(position);
		position = new EntityPosition();
		super.popContentHandler();
	}
}
