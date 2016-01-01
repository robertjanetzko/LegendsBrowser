package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.RegionRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class RegionpopIncorporatedIntoEntityEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent, RegionRelatedEvent {
	private int popRace = -1;
	private int popNumberMoved = -1;
	private int popSrId = -1;
	private int popFlId = -1;
	private int joinEntityId = -1;
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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "pop_race":
			setPopRace(Integer.parseInt(value));
			break;
		case "pop_number_moved":
			setPopNumberMoved(Integer.parseInt(value));
			break;
		case "pop_srid":
			setPopSrId(Integer.parseInt(value));
			break;
		case "pop_flid":
			setPopFlId(Integer.parseInt(value));
			break;
		case "join_entity_id":
			setJoinEntityId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
