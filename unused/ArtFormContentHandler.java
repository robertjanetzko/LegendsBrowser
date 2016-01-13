package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.ArtForm;
import legends.xml.handlers.ElementContentHandler;

public class ArtFormContentHandler<T extends ArtForm> extends ElementContentHandler<T> {

	T artForm;
	Class<T> artFormClass;

	public ArtFormContentHandler(Class<T> artFormClass, String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "name", "origin");
		this.artFormClass = artFormClass;
		try {
			artForm = artFormClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			artForm.setId(Integer.parseInt(value));
			break;
		case "name":
			artForm.setName(value);
			break;
		case "origin":
			artForm.setOriginEnId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public T getElement() {
		T t = artForm;
		try {
			artForm = artFormClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
}
