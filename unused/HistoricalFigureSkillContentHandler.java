package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalFigureSkill;
import legends.xml.handlers.XMLContentHandler;

public class HistoricalFigureSkillContentHandler extends XMLContentHandler {

	HistoricalFigureSkill link = new HistoricalFigureSkill();
	Consumer<HistoricalFigureSkill> handler;

	public HistoricalFigureSkillContentHandler(String name, XMLReader xmlReader, Consumer<HistoricalFigureSkill> handler) {
		super(name, xmlReader);
		this.handler = handler;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "skill":
			link.setSkill(value);
			break;
		case "total_ip":
			link.setTotalIp(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(link);
		link = new HistoricalFigureSkill();
		super.popContentHandler();
	}
}
