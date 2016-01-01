package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Artifact;
import legends.xml.handlers.ElementContentHandler;

public class ArtifactContentHandler extends ElementContentHandler<Artifact> {

	Artifact artifact = new Artifact();

	public ArtifactContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id","name","item");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			artifact.setId(Integer.parseInt(value));
			break;
		case "name":
			artifact.setName(value);
			break;
		case "item":
			artifact.setItem(value);
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public Artifact getElement() {
		Artifact a = artifact;
		artifact = new Artifact();
		return a;
	}
}
