package legends.model.events.basic;

import legends.model.World;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordsConverter;

public class EventLocation {
	private String place;
	@Xml("site,site_id")
	private int siteId = -1;
	@Xml("structure_id,building_id")
	private int structureId = -1;
	@Xml("region,subregion_id")
	private int subregionId = -1;
	@Xml("feature_layer_id")
	private int featureLayerId = -1;
	@Xml("coords")
	@XmlConverter(CoordsConverter.class)
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

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
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
		case "structure_id":
			setStructureId(Integer.parseInt(value));
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
		return getLink(preposition, preposition);
	}

	public String getLink(String sitePreposition, String regionPreposition) {
		if (siteId != -1) {
			if (structureId != -1)
				return String.format(" %s %s in %s", sitePreposition, World.getStructure(structureId, siteId).getLink(),
						World.getSite(siteId).getLink());
			if (subregionId != -1)
				return String.format(" %s %s in %s", sitePreposition, World.getSite(siteId).getLink(),
						World.getRegion(subregionId).getLink());
			return String.format(" %s %s", sitePreposition, World.getSite(siteId).getLink());
		} else if (subregionId != -1) {
			return String.format(" %s %s", regionPreposition, World.getRegion(subregionId).getLink());
		} else if (place != null) {
			if (place.equals(""))
				return "";
			else
				return String.format(" %s %s", sitePreposition, place);
		} else
			return String.format(" %s no region", regionPreposition);
	}

	public boolean isPresent() {
		if (this.getSubregionId() != -1)
			return true;
		if (this.getSiteId() != -1)
			return true;
		return false;
	}

}
