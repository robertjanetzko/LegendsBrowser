package legends.model.collections;

import legends.model.World;
import legends.model.collections.basic.EventCollection;

public class InsurrectionCollection extends EventCollection {
	private int siteId = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "target_enid":
			setTargetEnId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
