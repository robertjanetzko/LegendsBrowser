package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.WrittenContent;
import legends.xml.handlers.ElementContentHandler;

public class WrittenContentContentHandler extends ElementContentHandler<WrittenContent> {

	WrittenContent wc = new WrittenContent();

	public WrittenContentContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		registerContentHandler(
				new ReferenceContentHandler("reference", xmlReader, e -> wc.getReferences().add(e)));
		setHandledValues("id", "title", "page_start", "page_end", "type","style", "author");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			wc.setId(Integer.parseInt(value));
			break;
		case "title":
			wc.setTitle(value);
			break;
		case "page_start":
			wc.setPageStart(Integer.parseInt(value));
			break;
		case "page_end":
			wc.setPageEnd(Integer.parseInt(value));
			break;
		case "type":
			wc.setType(value);
			break;
		case "style":
			wc.getStyles().add(value);
			break;
		case "author":
			wc.setAuthorHfId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public WrittenContent getElement() {
		WrittenContent w = wc;
		wc = new WrittenContent();
		return w;
	}
}
