package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Entity;
import legends.model.World;
import legends.xml.handlers.ElementContentHandler;

public class EntityContentHandler extends ElementContentHandler<Entity> {

	Entity entity = new Entity();

	public EntityContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		registerContentHandler(
				new EntityLinkContentHandler("entity_link", xmlReader, e -> entity.getEntityLinks().add(e)));
		registerContentHandler(
				new EntityPositionContentHandler("entity_position", xmlReader, e -> entity.getPositions().put(e.getId(),e)));
		registerContentHandler(
				new EntityPositionAssignmentContentHandler("entity_position_assignment", xmlReader, e -> entity.getAssignments().put(e.getId(),e)));
		setHandledValues("id","name","race","type","child","histfig_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				Entity r = World.getEntity(id);
				if (r != null && r.getId() != -1)
					entity = r;
			}
			entity.setId(id);
			break;
		case "name":
			entity.setName(value);
			break;
		case "race":
			entity.setRace(value);
			break;
		case "type":
			entity.setType(value);
			break;
		case "child":
			entity.getChildren().add(Integer.parseInt(value));
			break;
		case "histfig_id":
			entity.getHfIds().add(Integer.parseInt(value));
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
