package legends.model;

public class SiteLink {
	private String linkType;
	private int siteId;
	private int occupationId;
	private int subId;
	private int entityId;

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
