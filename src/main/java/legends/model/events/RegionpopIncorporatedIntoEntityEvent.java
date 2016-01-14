package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.RegionRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("regionpop incorporated into entity")
public class RegionpopIncorporatedIntoEntityEvent extends Event
		implements EntityRelatedEvent, SiteRelatedEvent, RegionRelatedEvent {
	@Xml("pop_race")
	private int popRace = -1;
	@Xml("pop_number_moved")
	private int popNumberMoved = -1;
	@Xml("pop_srid")
	private int popSrId = -1;
	@Xml("pop_flid")
	private int popFlId = -1;
	@Xml("join_entity_id")
	private int joinEntityId = -1;
	@Xml("site_id")
	private int siteId;

	public int getPopRace() {
		return popRace;
	}

	public void setPopRace(int popRace) {
		this.popRace = popRace;
	}

	public int getPopNumberMoved() {
		return popNumberMoved;
	}

	public void setPopNumberMoved(int popNumberMoved) {
		this.popNumberMoved = popNumberMoved;
	}

	public int getPopSrId() {
		return popSrId;
	}

	public void setPopSrId(int popSrId) {
		this.popSrId = popSrId;
	}

	public int getPopFlId() {
		return popFlId;
	}

	public void setPopFlId(int popFlId) {
		this.popFlId = popFlId;
	}

	public int getJoinEntityId() {
		return joinEntityId;
	}

	public void setJoinEntityId(int joinEntityId) {
		this.joinEntityId = joinEntityId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return joinEntityId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public boolean isRelatedToRegion(int regionId) {
		return popSrId == regionId;
	}

	@Override
	public String getShortDescription() {
		String entity = World.getEntity(joinEntityId).getLink();
		String site = World.getSite(siteId).getLink();
		String region = World.getRegion(popSrId).getLink();
		return popNumberMoved + " of " + popRace + " from " + region + " joined with " + entity + " at " + site;
	}

}
