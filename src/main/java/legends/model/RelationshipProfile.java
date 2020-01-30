package legends.model;

import legends.xml.annotation.Xml;

public class RelationshipProfile {
	@Xml("hf_id")
	private int hfId = -1;
	@Xml("meet_count")
	private int meetCount = -1;
	@Xml("last_meet_year")
	private int lastMeetYear = -1;
	@Xml("last_meet_seconds72")
	private int lastMeetSeconds = -1;
	@Xml("rep_friendly")
	private int repFriendly = -1;
	@Xml("rep_buddy")
	private int repBuddy = -1;
	@Xml("rep_quarreler")
	private int repQuarreler = -1;
	@Xml("rep_trade_partner")
	private int repTradePartner = -1;
	@Xml("rep_bonded")
	private int repBonded = -1;
	@Xml("rep_information_source")
	private int repInformationSource = -1;

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public int getMeetCount() {
		return meetCount;
	}

	public void setMeetCount(int meetCount) {
		this.meetCount = meetCount;
	}

	public int getLastMeetYear() {
		return lastMeetYear;
	}

	public void setLastMeetYear(int lastMeetYear) {
		this.lastMeetYear = lastMeetYear;
	}

	public int getLastMeetSeconds() {
		return lastMeetSeconds;
	}

	public void setLastMeetSeconds(int lastMeetSeconds) {
		this.lastMeetSeconds = lastMeetSeconds;
	}

	public int getRepFriendly() {
		return repFriendly;
	}

	public void setRepFriendly(int repFriendly) {
		this.repFriendly = repFriendly;
	}

	public int getRepBuddy() {
		return repBuddy;
	}

	public void setRepBuddy(int repBuddy) {
		this.repBuddy = repBuddy;
	}

	public int getRepQuarreler() {
		return repQuarreler;
	}

	public void setRepQuarreler(int repQuarreler) {
		this.repQuarreler = repQuarreler;
	}

	public int getRepTradePartner() {
		return repTradePartner;
	}

	public void setRepTradePartner(int repTradePartner) {
		this.repTradePartner = repTradePartner;
	}

	public int getRepBonded() {
		return repBonded;
	}

	public void setRepBonded(int repBonded) {
		this.repBonded = repBonded;
	}

	public int getRepInformationSource() {
		return repInformationSource;
	}

	public void setRepInformationSource(int repInformationSource) {
		this.repInformationSource = repInformationSource;
	}

	public String getType() {
		if (repBonded > 0)
			return "lover";
		if (repBuddy > 0)
			return "friend";
		if (repFriendly > 0)
			return "friendly terms";
		if (repQuarreler > 0)
			return "quarreler";
		if (repTradePartner > 0)
			return "trade partner";
		if (repInformationSource > 0)
			return "source of information";

		return "unknown";
	}

}
