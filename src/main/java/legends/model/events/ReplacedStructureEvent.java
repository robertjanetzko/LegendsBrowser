package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.SiteRelatedEvent;

public class ReplacedStructureEvent extends Event implements EntityRelatedEvent, SiteRelatedEvent {
	private int civId = -1;
	private int siteCivId = -1;
	private int siteId = -1;
	private int oldAbId = -1;
	private int newAbId = -1;

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getOldAbId() {
		return oldAbId;
	}

	public void setOldAbId(int oldAbId) {
		this.oldAbId = oldAbId;
	}

	public int getNewAbId() {
		return newAbId;
	}

	public void setNewAbId(int newAbId) {
		this.newAbId = newAbId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "old_ab_id":
			setOldAbId(Integer.parseInt(value));
			break;
		case "new_ab_id":
			setNewAbId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId || siteCivId == entityId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site = World.getSite(siteId).getLink();

		return siteCiv+" of "+civ + " replaced " +oldAbId+" in "+site+" with "+newAbId;
	}
}
