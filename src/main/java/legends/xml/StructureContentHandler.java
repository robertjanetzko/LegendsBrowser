package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Structure;
import legends.xml.handlers.ElementContentHandler;

public class StructureContentHandler extends ElementContentHandler<Structure> {

	Structure structure = new Structure();

	public StructureContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id","type","name","name2");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			structure.setId(Integer.parseInt(value));
			break;
		case "type":
			structure.setType(value);
			break;
		case "name":
			structure.setName(value);
			break;
		case "name2":
			structure.setName2(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public Structure getElement() {
		Structure r = structure;
		structure = new Structure();
		return r;
	}
}
