package legends.xml.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.xml.EntityContentHandler;

public class XMLContentHandler implements ContentHandler {

	XMLReader xmlReader;
	XMLContentHandler parent;

	private String skipToEndElement = null;
	private Set<String> handledValues = new HashSet<>();
	private String name;

	Map<String, XMLContentHandler> handlers = new HashMap<>();

	protected String value;

	public XMLContentHandler(XMLReader xmlReader) {
		super();

		this.xmlReader = xmlReader;
	}

	public XMLContentHandler(String name, XMLReader xmlReader) {
		this(xmlReader);
		this.name = name;
	}

	public void registerContentHandler(XMLContentHandler contentHandler) {
		contentHandler.setParent(this);
		handlers.put(contentHandler.getName(), contentHandler);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (skipToEndElement != null)
			return;
		if (handledValues.contains(localName))
			return;

		XMLContentHandler contentHandler = handlers.get(localName);
		if (contentHandler != null) {
//				System.out.println(localName + " -> " + contentHandler);
			pushContentHandler(contentHandler);
		} else {
			System.out.println("unknown element: " + localName + " <= " + this);
			skipToEndElement = localName;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equals(skipToEndElement))
			skipToEndElement = null;

		if (localName.equals(name))
			popContentHandler();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch, start, length);
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

	public ContentHandler getParent() {
		return parent;
	}

	public void setParent(XMLContentHandler parent) {
		this.parent = parent;
	}

	void pushContentHandler(ContentHandler contentHandler) {
		// System.out.println("set content handler: " + contentHandler);
		xmlReader.setContentHandler(contentHandler);
	}

	protected void popContentHandler() {
		// System.out.println("set content handler: " + parent);
		xmlReader.setContentHandler(parent);
		parent.reactivated();
	}

	void reactivated() {

	}

	protected void setHandledValues(String... values) {
		for (String v : values)
			handledValues.add(v);
	}
}
