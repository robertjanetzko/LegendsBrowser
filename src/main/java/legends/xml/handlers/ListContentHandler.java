package legends.xml.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ListContentHandler extends StackContentHandler {
	private static final Log LOG = LogFactory.getLog(ListContentHandler.class);

	private AnnotationContentHandler elementContentHandler;
	private List<Object> elements = new ArrayList<>();

	public ListContentHandler(String name, AnnotationContentHandler elementContentHandler) {
		super(name);
		this.elementContentHandler = elementContentHandler;
		if (elementContentHandler.getConsumer() == null) {
			elementContentHandler.setConsumer(v -> getElements().add(v));
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals(elementContentHandler.getName())) {
			pushContentHandler(elementContentHandler);
		} else {
			LOG.warn(name + " - unknown list element: " + localName);
		}
	}

	@Override
	protected void consume() {
		try {
			if (consumer != null)
				consumer.accept(elements);
		} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
			LOG.error("error accapting element", e);
		}
		elements = new ArrayList<>();
	}

	public List<Object> getElements() {
		return elements;
	}

}
