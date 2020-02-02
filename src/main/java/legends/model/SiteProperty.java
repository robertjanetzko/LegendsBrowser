package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlAutoIncrement;

@XmlAutoIncrement
public class SiteProperty extends AbstractObject {
	private int siteId;
	@Xml("type")
	private String type = "UNKNOWN";
	@Xml("owner_hfid")
	private int ownerHfId = -1;

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
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
