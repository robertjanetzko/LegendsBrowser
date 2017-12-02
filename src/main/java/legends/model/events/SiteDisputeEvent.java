package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("site dispute")
public class SiteDisputeEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	@Xml("entity_id_1")
	private int entityId1 = -1;
	@Xml("entity_id_2")
	private int entityId2 = -1;
	@Xml("site_id_1")
	private int siteId1 = -1;
	@Xml("site_id_2")
	private int siteId2 = -1;
	@Xml(value = "dispute", track = true)
	private String dispute;

	private static Set<String> disputes = new HashSet<>();

	public int getEntityId1() {
		return entityId1;
	}

	public void setEntityId1(int entityId1) {
		this.entityId1 = entityId1;
	}

	public int getEntityId2() {
		return entityId2;
	}

	public void setEntityId2(int entityId2) {
		this.entityId2 = entityId2;
	}

	public int getSiteId1() {
		return siteId1;
	}

	public void setSiteId1(int siteId1) {
		this.siteId1 = siteId1;
	}

	public int getSiteId2() {
		return siteId2;
	}

	public void setSiteId2(int siteId2) {
		this.siteId2 = siteId2;
	}

	public String getDispute() {
		return dispute;
	}

	public void setDispute(String dispute) {
		this.dispute = dispute;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return this.entityId1 == entityId || this.entityId2 == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId1 == siteId || this.siteId2 == siteId;
	}

	@Override
	public void process() {
		Entity entity1 = World.getEntity(entityId1);
		Site site1 = World.getSite(siteId1);
		site1.setOwner(entity1.getRoot());
		entity1.getSites().add(site1);
		entity1.getRoot().getSites().add(site1);

		Entity entity2 = World.getEntity(entityId2);
		Site site2 = World.getSite(siteId2);
		site2.setOwner(entity2.getRoot());
		entity2.getSites().add(site2);
		entity2.getRoot().getSites().add(site2);
	}

	@Override
	public String getShortDescription() {
		String entity1 = World.getEntity(entityId1).getLink();
		String entity2 = World.getEntity(entityId2).getLink();
		String site1 = World.getSite(siteId1).getLink();
		String site2 = World.getSite(siteId2).getLink();

		return entity1 + " of " + site1 + " and " + entity2 + " of " + site2 + " became embroiled in a dispute over "
				+ dispute;
	}

	public static void printUnknownDisputes() {
		disputes.remove("grazing rights");
		disputes.remove("rights-of-way");
		disputes.remove("water rights");
		disputes.remove("fishing rights");
		disputes.remove("livestock ownership");
		disputes.remove("territory");

		if (disputes.size() > 0)
			LOG.debug("unknown site disputes: " + disputes);
	}
}
