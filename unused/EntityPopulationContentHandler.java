package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.EntityPopulation;
import legends.model.World;
import legends.xml.handlers.ElementContentHandler;

public class EntityPopulationContentHandler extends ElementContentHandler<EntityPopulation> {

	EntityPopulation population = new EntityPopulation();

	public EntityPopulationContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "civ_id", "race");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				EntityPopulation r = World.getEntityPopulation(id);
				if (r != null)
					population = r;
			}
			population.setId(id);
			break;
		case "civ_id":
			population.setCivId(Integer.parseInt(value));
			break;
		case "race":
			population.setRace(value);
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
