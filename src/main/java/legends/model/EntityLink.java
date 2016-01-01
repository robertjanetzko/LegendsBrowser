package legends.model;

import java.util.HashSet;
import java.util.Set;

public class EntityLink {
	private String linkType;
	private int entityId;
	private int linkStrength;

	private static Set<String> linkTypes = new HashSet<>();

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		linkTypes.add(linkType);
		this.linkType = linkType;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getLinkStrength() {
		return linkStrength;
	}

	public void setLinkStrength(int linkStrength) {
		this.linkStrength = linkStrength;
	}

	@Override
	public String toString() {
		return linkType + " " + entityId;
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("former slave");
		linkTypes.remove("slave");
		linkTypes.remove("prisoner"); 
		linkTypes.remove("member");
		linkTypes.remove("former prisoner");
		linkTypes.remove("enemy");
		linkTypes.remove("criminal");
		linkTypes.remove("former member");
		
		if (linkTypes.size() > 0)
			System.out.println("unknown entity link types: " + linkTypes);
	}

}
