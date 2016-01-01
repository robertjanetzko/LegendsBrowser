package legends.xml;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.collections.AbductionCollection;
import legends.model.collections.BattleCollection;
import legends.model.collections.BeastAttackCollection;
import legends.model.collections.DuelCollection;
import legends.model.collections.JourneyCollection;
import legends.model.collections.OccasionCollection;
import legends.model.collections.PerformanceCollection;
import legends.model.collections.SiteConqueredCollection;
import legends.model.collections.TheftCollection;
import legends.model.collections.WarCollection;
import legends.model.collections.basic.EventCollection;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalEventCollectionContentHandler extends ElementContentHandler<EventCollection> {

	private EventCollection eventCol = new EventCollection();

	private Set<String> unknownTypes = new HashSet<>();

	public HistoricalEventCollectionContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "start_year", "start_seconds72", "end_year", "end_seconds72", "event",
				"eventcol", "type", "ordinal", "civ_id", "occasion_id", "parent_eventcol", "subregion_id",
				"feature_layer_id", "site_id", "coords", "attacking_enid", "defending_enid", "attacking_hfid",
				"defending_hfid", "aggressor_ent_id", "defender_ent_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "type":
			EventCollection tempEventCol = eventCol;
			eventCol = createEventCol(value);
			if (eventCol != null) {
				eventCol.populateFrom(tempEventCol);
				eventCol.setType(value);
			}
			break;

		default:
			if (eventCol == null || !eventCol.setProperty(localName, value))
				super.endElement(uri, localName, qName);
			break;
		}
	}

	private EventCollection createEventCol(String type) {
		switch (type) {
		case "abduction":
			return new AbductionCollection();
		case "battle":
			return new BattleCollection();
		case "beast attack":
			return new BeastAttackCollection();
		case "duel":
			return new DuelCollection();
		case "occasion":
			return new OccasionCollection();
		case "journey":
			return new JourneyCollection();
		case "theft":
			return new TheftCollection();
		case "site conquered":
			return new SiteConqueredCollection();
		case "war":
			return new WarCollection();
		case "competition":
		case "performance":
		case "ceremony":
		case "procession":
			return new PerformanceCollection();
		default:
			if (!unknownTypes.contains(type)) {
				System.err.println("unknown event collection type: " + type);
				unknownTypes.add(type);
			}

			return null;
		}
	}

	@Override
	public EventCollection getElement() {
		EventCollection e = eventCol;
		eventCol = new EventCollection();
		return e;
	}

}
