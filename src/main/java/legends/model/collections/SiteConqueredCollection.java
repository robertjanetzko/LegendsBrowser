package legends.model.collections;

import java.util.List;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.AddHfSiteLinkEvent;
import legends.model.events.NewSiteLeaderEvent;
import legends.model.events.basic.Event;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("site conquered")
public class SiteConqueredCollection extends EventCollection {
	@Xml("attacking_enid")
	private int attackingEnId = -1;
	@Xml("defending_enid")
	private int defendingEnId = -1;
	@Xml("site_id")
	private int siteId = -1;
	@Xml("war_eventcol")
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
	public void process() {
		super.process();

		List<Event> events = getAllHistoricalEvents();
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
				if (e1.getLinkType().equals(""))
					e1.setLinkType(e2.getCalcLinkType());
			}
		}
	}

	@Override
	public String getLink() {
		String site = World.getSite(siteId).getLink();
		return "the <a href=\"/collection/" + getId() + "\" class=\"collection site-conquered\">" + getOrdinalString()
				+ "Pillaging</a> of " + site;
	}

	@Override
	public String getShortDescription() {
		return getLink() + " occurred";
	}
}
