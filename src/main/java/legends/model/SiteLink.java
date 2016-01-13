package legends.model;

import legends.xml.annotation.Xml;

public class SiteLink {
	@Xml("link_type")private String linkType;
	@Xml("site_id")private int siteId;
	@Xml("occupation_id")private int occupationId;
	@Xml("sub_id")private int subId;
	@Xml("entity_id")private int entityId;

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		return linkType + " " + siteId;
	}

}
