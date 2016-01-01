package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalEra;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalEraContentHandler extends ElementContentHandler<HistoricalEra> {

	HistoricalEra era = new HistoricalEra();

	public HistoricalEraContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("name", "start_year");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "name":
			era.setName(value);
			break;
		case "start_year":
			era.setStartYear(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public HistoricalEra getElement() {
		HistoricalEra a = era;
		era = new HistoricalEra();
		return a;
	}
}
