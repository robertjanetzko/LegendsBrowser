package legends.model.collections;

import java.util.List;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.AddHfSiteLinkEvent;
import legends.model.events.NewSiteLeaderEvent;
import legends.model.events.basic.Event;

public class SiteConqueredCollection extends EventCollection {
	private int attackingEnId = -1;
	private int defendingEnId = -1;
	private int siteId = -1;
	private int warEventCol = -1;

	public int getAttackingEnId() {
		return attackingEnId;
	}

	public void setAttackingEnId(int attackingEnId) {
		this.attackingEnId = attackingEnId;
	}

	public int getDefendingEnId() {
		return defendingEnId;
	}

	public void setDefendingEnId(int defendingEnId) {
		this.defendingEnId = defendingEnId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getWarEventCol() {
		return warEventCol;
	}

	public void setWarEventCol(int warEventCol) {
		this.warEventCol = warEventCol;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "attacking_enid":
			setAttackingEnId(Integer.parseInt(value));
			break;
		case "defending_enid":
			setDefendingEnId(Integer.parseInt(value));
			break;
		case "site_id":
			setSiteId(Integer.parseInt(value));
			break;
		case "war_eventcol":
			setWarEventCol(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public void process() {
		super.process();
		
		List<Event> events = getHistoricalEvents();
		for (int i = 1; i < events.size(); i++) {
			if (events.get(i) instanceof AddHfEntityLinkEvent && events.get(i - 1) instanceof NewSiteLeaderEvent) {
				AddHfEntityLinkEvent e1 = (AddHfEntityLinkEvent) events.get(i);
				NewSiteLeaderEvent e2 = (NewSiteLeaderEvent) events.get(i - 1);
				if (e1.getCalcHfId() == -1)
					e1.setCalcHfId(e2.getNewLeaderHfId());
				if (e1.getCalcLinkType().equals(""))
					e1.setCalcLinkType("ruler");
			}
		}
		for (int i = 2; i < events.size(); i++) {
			if (events.get(i) instanceof AddHfSiteLinkEvent && events.get(i - 2) instanceof AddHfEntityLinkEvent) {
				AddHfSiteLinkEvent e1 = (AddHfSiteLinkEvent) events.get(i);
				AddHfEntityLinkEvent e2 = (AddHfEntityLinkEvent) events.get(i - 2);
				if (e1.getCalcHfId() == -1)
					e1.setCalcHfId(e2.getCalcHfId());
				if (e1.getCalcLinkType().equals(""))
					e1.setCalcLinkType(e2.getCalcLinkType());
			}
		}
	}

	@Override
	public String getShortDescription() {
		String attacker = World.getEntity(attackingEnId).getLink();
		String defender = World.getEntity(defendingEnId).getLink();
		String site = World.getSite(siteId).getLink();
		return attacker + " conquered " + site + " from " + defender;
	}
}
