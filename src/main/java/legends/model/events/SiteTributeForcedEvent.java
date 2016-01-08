package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;

public class SiteTributeForcedEvent extends Event {
	private int attackerCivId = -1;
	private int defenderCivId = -1;
	private int siteCivId = -1;
	private int siteId = -1;

	public int getAttackerCivId() {
		return attackerCivId;
	}

	public void setAttackerCivId(int attackerCivId) {
		this.attackerCivId = attackerCivId;
	}

	public int getDefenderCivId() {
		return defenderCivId;
	}

	public void setDefenderCivId(int defenderCivId) {
		this.defenderCivId = defenderCivId;
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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacker_civ_id":
			setAttackerCivId(Integer.parseInt(value));
			break;
		case "defender_civ_id":
			setDefenderCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			setSiteCivId(Integer.parseInt(value));
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
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site = World.getSite(siteId).getLink();

		return attacker + " secured tribute from " + siteCiv + ", to be delivered from " + site;
	}

}
