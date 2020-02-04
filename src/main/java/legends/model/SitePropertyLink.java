package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;


public class SitePropertyLink extends AbstractObject {
	@Xml("site_id")
	private int siteId;
	@Xml("property_id")
	private int propertyId;

	public int getSiteId() {
		return siteId;
	}
	public int getPropertyId() {
		return propertyId;
	}
}
