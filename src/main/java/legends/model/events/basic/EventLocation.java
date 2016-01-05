package legends.model.events.basic;

import legends.model.World;

public class EventLocation {
	private String place;
	private int siteId = -1;
	private int subregionId = -1;
	private int featureLayerId = -1;
	private Coords coords;

	public EventLocation() {
	}

	public EventLocation(String place) {
		this.place = place;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSubregionId() {
		return subregionId;
	}

	public void setSubregionId(int subregionId) {
		this.subregionId = subregionId;
	}

	public int getFeatureLayerId() {
		return featureLayerId;
	}

	public void setFeatureLayerId(int featureLayerId) {
		this.featureLayerId = featureLayerId;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public boolean setProperty(String property, String value) {
		switch (property) {
		case "site_id":
		case "site":
			setSiteId(Integer.parseInt(value));
			break;
		case "region":
		case "subregion_id":
			setSubregionId(Integer.parseInt(value));
			break;
		case "feature_layer_id":
			setFeatureLayerId(Integer.parseInt(value));
			break;
		case "coords":
			setCoords(new Coords(value));
			break;

		default:
			return false;
		}
		return true;
	}

	public String getLink(String preposition) {
		preposition = " " + preposition + " ";
		if (this.getSubregionId() != -1) {
			if (this.getSiteId() != -1)
				return preposition + World.getSite(this.getSiteId()).getLink() + " in "
						+ World.getRegion(this.getSubregionId()).getLink();
			return preposition + World.getRegion(this.getSubregionId()).getLink();
		} else if (this.getSiteId() != -1)
			return preposition + World.getSite(this.getSiteId()).getLink();
		else if (place != null)
			if (place.equals(""))
				return "";
			else
				return preposition + place;
		else
			return preposition + "no region";
	}

	public boolean isPresent() {
		if (this.getSubregionId() != -1)
			return true;
		if (this.getSiteId() != -1)
			return true;
		return false;
	}

}
