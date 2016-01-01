package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalFigureLink;
import legends.xml.handlers.XMLContentHandler;

public class HistoricalFigureLinkContentHandler extends XMLContentHandler {

	HistoricalFigureLink link = new HistoricalFigureLink();
	Consumer<HistoricalFigureLink> handler;

	public HistoricalFigureLinkContentHandler(String name, XMLReader xmlReader, Consumer<HistoricalFigureLink> handler) {
		super(name, xmlReader);
		this.handler = handler;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "link_type":
			link.setLinkType(value);
			break;
		case "hfid":
			link.setHistoricalFigureId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(link);
		link = new HistoricalFigureLink();
		super.popContentHandler();
	}
}
