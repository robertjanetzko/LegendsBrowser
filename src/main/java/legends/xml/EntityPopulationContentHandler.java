package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityPopulation;
import legends.xml.handlers.ElementContentHandler;

public class EntityPopulationContentHandler extends ElementContentHandler<EntityPopulation> {

	EntityPopulation population = new EntityPopulation();

	public EntityPopulationContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			population.setId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public EntityPopulation getElement() {
		EntityPopulation a = population;
		population = new EntityPopulation();
		return a;
	}
}
