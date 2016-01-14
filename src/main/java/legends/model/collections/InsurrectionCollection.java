package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("insurrection")
public class InsurrectionCollection extends EventCollection {
	@Xml("site_id")
	private int siteId = -1;
	@Xml("target_enid")
	private int targetEnId = -1;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getTargetEnId() {
		return targetEnId;
	}

	public void setTargetEnId(int targetEnId) {
		this.targetEnId = targetEnId;
	}

	@Override
	public String getLink() {
		return "the <a href=\"/collection/" + getId() + "\" class=\"collection insurrection\">" + getOrdinalString()
				+ "Insurrection</a> at " + World.getSite(siteId).getLink();
	}

	@Override
	public String getShortDescription() {
		return "the " + getOrdinalString() + "Insurrection at " + World.getSite(siteId).getLink() + " occurred";
	}
}
