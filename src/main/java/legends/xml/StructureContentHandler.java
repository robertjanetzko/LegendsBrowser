package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Structure;
import legends.xml.handlers.ElementContentHandler;

public class StructureContentHandler extends ElementContentHandler<Structure> {

	Structure structure = new Structure();

	public StructureContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public Structure getElement() {
		Structure r = structure;
		structure = new Structure();
		System.out.println(r.toString());
		return r;
	}
}
