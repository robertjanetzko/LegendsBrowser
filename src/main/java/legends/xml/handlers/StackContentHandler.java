package legends.xml.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class StackContentHandler implements ContentHandler {
	private static final Log LOG = LogFactory.getLog(StackContentHandler.class);

	protected XMLReader xmlReader;
	private StackContentHandler parent;
	protected String name;
	protected MethodConsumer consumer;

	protected String value;

	public StackContentHandler(String name) {
		this.name = name;
	}

	public XMLReader getXmlReader() {
		return xmlReader;
	}

	public void setXmlReader(XMLReader xmlReader) {
		this.xmlReader = xmlReader;
	}

	public String getName() {
		return name;
	}

	public MethodConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MethodConsumer consumer) {
		this.consumer = consumer;
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
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equals(name)) {
			consume();
			popContentHandler();
		} else {
			LOG.warn(name + " - unknown element: " + localName + " = " + value);
		}
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

	protected void pushContentHandler(StackContentHandler contentHandler) {
		contentHandler.parent = this;
		contentHandler.xmlReader = xmlReader;
		xmlReader.setContentHandler(contentHandler);
	}

	protected void popContentHandler() {
		xmlReader.setContentHandler(parent);
	}

	protected void consume() {
	}
}
