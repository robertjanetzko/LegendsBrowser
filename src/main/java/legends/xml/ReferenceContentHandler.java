package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Reference;
import legends.xml.handlers.XMLContentHandler;

public class ReferenceContentHandler extends XMLContentHandler {

	Reference reference = new Reference();
	Consumer<Reference> handler;

	public ReferenceContentHandler(String name, XMLReader xmlReader, Consumer<Reference> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("type","id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "type":
			reference.setType(value);
			break;
		case "id":
			reference.setId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(reference);
		reference = new Reference();
		super.popContentHandler();
	}
}
