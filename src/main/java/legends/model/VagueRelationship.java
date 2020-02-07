package legends.model;

import legends.xml.annotation.Xml;

public class VagueRelationship {
	@Xml("hfid")
	private int hfId = -1;

	@Xml("artistic_buddy")
	private boolean artisticBuddy = false;
	@Xml("atheletic_rival")
	private boolean athleticRival = false;
	@Xml("athlete_buddy")
	private boolean athleteBuddy = false;
	@Xml("business_rival")
	private boolean businessRival = false;
	@Xml("childhood_friend")
	private boolean childhoodFriend = false;
	@Xml("grudge")
	private boolean grudge = false;
	@Xml("jealous_obsession")
	private boolean jealousObsession = false;
	@Xml("jealous_relationship_grudge")
	private boolean jealousRelationshipGrudge = false;
	@Xml("persecution_grudge")
	private boolean persecutionGrudge = false;
	@Xml("religious_persecution_grudge")
	private boolean religiousPersecutionGrudge = false;
	@Xml("scholar_buddy")
	private boolean scholarBuddy = false;
	@Xml("war_buddy")
	private boolean warBuddy = false;
	@Xml("supernatural_grudge")
	private boolean supernaturalGrudge = false;
	
	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public String getType() {
		if (artisticBuddy)
			return "art friend";
		if (athleticRival)
			return "athletic rival";
		if (athleteBuddy)
			return "athletic friend";
		if (businessRival)
			return "business rival";
		if (childhoodFriend)
			return "childhood friend";
		if (grudge)
			return "grudge";
		if (jealousObsession)
			return "infatuated";
		if (jealousRelationshipGrudge)
			return "jealous";
		if (persecutionGrudge)
			return "grudge after persecution";
		if (religiousPersecutionGrudge)
			return "grudge after religious persecution";
		if (scholarBuddy)
			return "scholar friend";
		if (warBuddy)
			return "war friend";
		if (supernaturalGrudge)
			return "subject of supernatural vengeance";

		return "unknown";
	}
}
