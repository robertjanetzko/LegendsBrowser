package legends.xml;

import java.util.function.Consumer;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.RelationshipProfile;
import legends.xml.handlers.XMLContentHandler;

public class RelationshipProfileContentHandler extends XMLContentHandler {

	RelationshipProfile profile = new RelationshipProfile();
	Consumer<RelationshipProfile> handler;

	public RelationshipProfileContentHandler(String name, XMLReader xmlReader, Consumer<RelationshipProfile> handler) {
		super(name, xmlReader);
		this.handler = handler;
		setHandledValues("hf_id", "meet_count", "last_meet_year", "last_meet_seconds72", "rep_friendly", "rep_buddy",
				"rep_quarreler", "rep_trade_partner");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "hf_id":
			profile.setHfId(Integer.parseInt(value));
			break;
		case "meet_count":
			profile.setMeetCount(Integer.parseInt(value));
			break;
		case "last_meet_year":
			profile.setLastMeetYear(Integer.parseInt(value));
			break;
		case "last_meet_seconds72":
			profile.setLastMeetSeconds(Integer.parseInt(value));
			break;
		case "rep_friendly":
			profile.setRepFriendly(Integer.parseInt(value));
			break;
		case "rep_buddy":
			profile.setRepBuddy(Integer.parseInt(value));
			break;
		case "rep_quarreler":
			profile.setRepQuarreler(Integer.parseInt(value));
			break;
		case "rep_trade_partner":
			profile.setRepTradePartner(Integer.parseInt(value));
			break;

		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	protected void popContentHandler() {
		handler.accept(profile);
		profile = new RelationshipProfile();
		super.popContentHandler();
	}
}
