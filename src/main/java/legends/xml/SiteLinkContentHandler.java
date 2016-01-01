package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.SiteLink;
import legends.xml.handlers.XMLContentHandler;

public class SiteLinkContentHandler extends XMLContentHandler {

	SiteLink entityLink = new SiteLink();
	Consumer<SiteLink> handler;

	public SiteLinkContentHandler(String name, XMLReader xmlReader, Consumer<SiteLink> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("link_type","site_id","occupation_id","sub_id","entity_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "link_type":
			entityLink.setLinkType(value);
			break;
		case "site_id":
			entityLink.setSiteId(Integer.parseInt(value));
			break;
		case "occupation_id":
			entityLink.setOccupationId(Integer.parseInt(value));
			break;
		case "sub_id":
			entityLink.setSubId(Integer.parseInt(value));
			break;
		case "entity_id":
			entityLink.setEntityId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}
	
	@Override
	protected void popContentHandler() {
		handler.accept(entityLink);
		entityLink = new SiteLink();
		super.popContentHandler();
	}
}
