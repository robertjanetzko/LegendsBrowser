package legends.xml.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ListContentHandler<T> extends XMLContentHandler {
	private ElementContentHandler<T> elementContentHandler;
	private List<T> elements = new ArrayList<>();
	private Consumer<List<T>> handler;

	public ListContentHandler(String name, XMLReader xmlReader, ElementContentHandler<T> elementContentHandler,
			Consumer<List<T>> handler) {
		super(name, xmlReader);

		this.elementContentHandler = elementContentHandler;
		this.handler = handler;

		elementContentHandler.setParent(this);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals(elementContentHandler.getName())) {
			pushContentHandler(elementContentHandler);
		} else {
			// System.out.println("unknown list element: " + localName);
		}
	}

	@Override
	void reactivated() {
		T e = elementContentHandler.getElement();
		if (e != null)
			elements.add(e);
	}

	public List<T> getElements() {
		return elements;
	}

	@Override
	protected void popContentHandler() {
		handler.accept(elements);
		super.popContentHandler();
	}

}
