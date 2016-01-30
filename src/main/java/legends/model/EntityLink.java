package legends.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import legends.xml.annotation.Xml;

public class EntityLink {
	private static final Log LOG = LogFactory.getLog(EntityLink.class);

	@Xml("type,link_type")
	private String linkType;
	@Xml("entity_id,target")
	private int entityId;
	@Xml("link_strength,strength")
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
			LOG.debug("unknown entity link types: " + linkTypes);
	}

}
