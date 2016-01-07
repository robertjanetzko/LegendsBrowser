package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.Artifact;
import legends.model.World;
import legends.model.WorldConstruction;
import legends.xml.handlers.ElementContentHandler;

public class ArtifactContentHandler extends ElementContentHandler<Artifact> {

	Artifact artifact = new Artifact();

	public ArtifactContentHandler(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id","name","item","mat","item_type","item_subtype","item_description");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			int id = Integer.parseInt(value);
			if (World.isPlusMode()) {
				Artifact r = World.getArtifact(id);
				if (r != null && r.getId() != -1)
					artifact = r;
			}
			artifact.setId(id);
			break;
		case "name":
			artifact.setName(value);
			break;
		case "item":
			artifact.setItem(value);
			break;
		case "mat":
			artifact.setMat(value);
			break;
		case "item_type":
			artifact.setItemType(value);
			break;
		case "item_subtype":
			artifact.setItemSubType(value);
			break;
		case "item_description":
			artifact.setItemDescription(value);
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
