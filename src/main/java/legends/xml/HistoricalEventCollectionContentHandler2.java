package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalEventCollection;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalEventCollectionContentHandler2 extends ElementContentHandler<HistoricalEventCollection> {

	HistoricalEventCollection collection = new HistoricalEventCollection();

	public HistoricalEventCollectionContentHandler2(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "start_year", "start_seconds72", "end_year", "end_seconds72", "event", "eventcol",
				"type", "ordinal", "civ_id", "occasion_id", "parent_eventcol", "subregion_id", "feature_layer_id",
				"site_id", "coords", "attacking_enid","defending_enid", "attacking_hfid", "defending_hfid",
				"aggressor_ent_id","defender_ent_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			collection.setId(Integer.parseInt(value));
			break;
		case "name":
			collection.setName(value);
			break;
		case "start_year":
			collection.setStartYear(Integer.parseInt(value));
			break;
		case "start_seconds72":
			collection.setStartSeconds72(Integer.parseInt(value));
			break;
		case "end_year":
			collection.setEndYear(Integer.parseInt(value));
			break;
		case "end_seconds72":
			collection.setEndSeconds72(Integer.parseInt(value));
			break;
		case "event":
			collection.getEvents().add(Integer.parseInt(value));
			break;
		case "eventcol":
			collection.getEventCols().add(Integer.parseInt(value));
			break;
		case "type":
			collection.setType(value);
			break;
		case "civ_id":
			collection.setCivId(Integer.parseInt(value));
			break;
		case "occasion_id":
			collection.setOccasionId(Integer.parseInt(value));
			break;
		case "ordinal":
			collection.setOrdinal(Integer.parseInt(value));
			break;
		case "parent_eventcol":
			collection.setParentEventcol(Integer.parseInt(value));
			break;
		case "subregion_id":
			collection.setSubregionId(Integer.parseInt(value));
			break;
		case "feature_layer_id":
			collection.setFeatureLayerId(Integer.parseInt(value));
			break;
		case "site_id":
			collection.setSiteId(Integer.parseInt(value));
			break;
		case "coords":
			String[] coords = value.split(",");
			collection.setX(Integer.parseInt(coords[0]));
			collection.setX(Integer.parseInt(coords[1]));
			break;
		case "attacking_enid":
			collection.setAttackingEnId(Integer.parseInt(value));
			break;
		case "defending_enid":
			collection.setDefendingEnId(Integer.parseInt(value));
			break;
		case "attacking_hfid":
			collection.setAttackingHfId(Integer.parseInt(value));
			break;
		case "defending_hfid":
			collection.setDefendingHfId(Integer.parseInt(value));
			break;
		case "aggressor_ent_id":
			collection.setAggressorEntId(Integer.parseInt(value));
			break;
		case "defender_ent_id":
			collection.setDefenderEntId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public HistoricalEventCollection getElement() {
		HistoricalEventCollection a = collection;
		collection = new HistoricalEventCollection();
		return a;
	}
}
