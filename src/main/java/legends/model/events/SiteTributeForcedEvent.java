package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("site tribute forced")
public class SiteTributeForcedEvent extends Event {
	@Xml("attacker_civ_id")
	int attackerCivId = -1;
	@Xml("defender_civ_id")
	int defenderCivId = -1;
	@Xml("site_civ_id")
	int siteCivId = -1;
	@Xml("site_id")
	int siteId = -1;
	@Xml("season")
	String season = "";

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
	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackerCivId).getLink();
		String siteCiv = World.getEntity(siteCivId).getLink();
		String site = World.getSite(siteId).getLink();
		
		String result = attacker + " secured tribute from " + siteCiv;
		if (!site.isEmpty()) {
			result = result + ", to be delivered from " + site;
		}
		if (!season.isEmpty()) {
			result = result + " every " + season;
		}

		return result;
	}

}
