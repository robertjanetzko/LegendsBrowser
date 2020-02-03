package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlAutoIncrement;

@XmlAutoIncrement
public class SiteProperty extends AbstractObject {

	private int siteId;

	@Xml("type")
	private String type = null;

	@Xml("owner_hfid")
	private int ownerHfId = -1;

	@Xml("structure_id")
	private int structureId = -1;

	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getOwnerId() {
		return ownerHfId;
	}
	public void setOwnerId(int id) {
		this.ownerHfId = id;
	}

	public String getType() {
		if (type != null)
			return type;
		else if (structureId != -1)
			return World.getStructure(structureId, siteId).getType();
		return "UNKNOWN";
	}

	public int getStructureId() {
		return structureId;
	}

	public String getLink() {
		if (structureId != -1)
			return World.getStructure(structureId, siteId).getLink();
		if (ownerHfId != -1)
			return String.format("a %s owned by %s", type, World.getHistoricalFigure(ownerHfId));
		return String.format("a %s", type);
	}
}
