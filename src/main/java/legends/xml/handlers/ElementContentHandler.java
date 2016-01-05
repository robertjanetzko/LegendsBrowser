package legends.xml.handlers;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

abstract public class ElementContentHandler<T> extends XMLContentHandler {
	public ElementContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
	}

	abstract public T getElement();
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(!localName.equals(getName()))
			System.out.println("unkown list element tag: "+localName+" = "+value+" ? "+this);
		super.endElement(uri, localName, qName);
	}
}
