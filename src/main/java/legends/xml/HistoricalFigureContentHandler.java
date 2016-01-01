package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalFigure;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalFigureContentHandler extends ElementContentHandler<HistoricalFigure> {

	HistoricalFigure figure = new HistoricalFigure();

	public HistoricalFigureContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);

		setHandledValues("id", "name", "caste", "race", "appeared", "birth_year", "birth_seconds72", "death_year",
				"death_seconds72", "associated_type", "ent_pop_id", "entity_id", "goal", "sphere", "holds_artifact",
				"interaction_knowledge", "active_interaction", "journey_pet", "deity", "force", "animated",
				"animated_string", "used_identity_id");
		registerContentHandler(
				new EntityLinkContentHandler("entity_link", xmlReader, e -> figure.getEntityLinks().add(e)));
		registerContentHandler(new EntityFormerPositionLinkContentHandler("entity_former_position_link", xmlReader,
				e -> figure.getEntityFormerPositionLinks().add(e)));
		registerContentHandler(new HistoricalFigureLinkContentHandler("hf_link", xmlReader,
				e -> figure.getHistoricalFigureLinks().add(e)));
		registerContentHandler(new HistoricalFigureSkillContentHandler("hf_skill", xmlReader,
				e -> figure.getHistoricalFigureSkills().add(e)));
		registerContentHandler(new SiteLinkContentHandler("site_link", xmlReader, e -> figure.getSiteLinks().add(e)));
		registerContentHandler(new EntityPositionLinkContentHandler("entity_position_link", xmlReader,
				e -> figure.getEntityPositionLinks().add(e)));
		registerContentHandler(new EntityReputationContentHandler("entity_reputation", xmlReader,
				e -> figure.getEntityReputations().add(e)));
		registerContentHandler(new EntitySquadLinkContentHandler("entity_squad_link", xmlReader,
				e -> figure.getEntitySquadLinks().add(e)));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			figure.setId(Integer.parseInt(value));
			break;
		case "name":
			figure.setName(value);
			break;
		case "race":
			figure.setRace(value);
			break;
		case "caste":
			figure.setCaste(value);
			break;
		case "appeared":
			figure.setAppeared(Integer.parseInt(value));
			break;
		case "birth_year":
			figure.setAppeared(Integer.parseInt(value));
			break;
		case "birth_seconds72":
			figure.setAppeared(Integer.parseInt(value));
			break;
		case "death_year":
			figure.setAppeared(Integer.parseInt(value));
			break;
		case "death_seconds72":
			figure.setAppeared(Integer.parseInt(value));
			break;
		case "associated_type":
			figure.setAssociatedType(value);
			break;
		case "ent_pop_id":
			figure.setEntPopId(Integer.parseInt(value));
			break;
		case "goal":
			figure.getGoals().add(value);
			break;
		case "sphere":
			figure.getSpheres().add(value);
			break;
		case "holds_artifact":
			figure.getArtifacts().add(Integer.parseInt(value));
			break;
		case "interaction_knowledge":
			figure.getInteractionKnowledges().add(value);
			break;
		case "journey_pet":
			figure.setJourneyPet(value);
			break;
		case "active_interaction":
			if (figure.getActiveInteraction() != null)
				System.out.println("multiple inter");
			figure.setActiveInteraction(value);
			break;
		case "deity":
			figure.setDeity(true);
			break;
		case "force":
			figure.setForce(true);
			break;
		case "animated":
			figure.setAnimated(true);
			break;
		case "animated_string":
			figure.setAnimatedString(value);
			break;
		case "used_identity_id":
			figure.setUsedIdentityId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public HistoricalFigure getElement() {
		HistoricalFigure e = figure;
		figure = new HistoricalFigure();
		return e;
	}

}
