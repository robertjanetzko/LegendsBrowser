package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.Entity;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class SiteDisputeEvent extends Event implements SiteRelatedEvent, EntityRelatedEvent {
	private int entityId1 = -1;
	private int entityId2 = -1;
	private int siteId1 = -1;
	private int siteId2 = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "entity_id_1":
			setEntityId1(Integer.parseInt(value));
			break;
		case "entity_id_2":
			setEntityId2(Integer.parseInt(value));
			break;
		case "site_id_1":
			setSiteId1(Integer.parseInt(value));
			break;
		case "site_id_2":
			setSiteId2(Integer.parseInt(value));
			break;
		case "dispute":
			disputes.add(value);
			setDispute(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
			System.out.println("unknown site disputes: " + disputes);
	}
}
