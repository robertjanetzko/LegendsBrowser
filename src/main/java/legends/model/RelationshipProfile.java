package legends.model;

import java.util.List;
import java.util.ArrayList;

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
	@Xml("rep_grudge")
	private int repGrudge = -1;
	@Xml("rep_quarreler")
	private int repQuarreler = -1;
	@Xml("rep_flatterer")
	private int repFlatterer = -1;
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

	public int getRepGrudge() {
		return repGrudge;
	}

	public void setRepGrudge(int repGrudge) {
		this.repGrudge = repGrudge;
	}

	public int getRepQuarreler() {
		return repQuarreler;
	}

	public void setRepQuarreler(int repQuarreler) {
		this.repQuarreler = repQuarreler;
	}

	public int getRepFlatterer() {
		return repFlatterer;
	}

	public void setRepFlatterer(int repFlatterer) {
		this.repFlatterer = repFlatterer;
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
		List<String> reputation = new ArrayList<String>();
		if (repBonded > 0)
			reputation.add("bonded"); // For animal partners.
		if (repBuddy > 0)
			reputation.add("friend");
		if (repGrudge > 0)
			reputation.add("grudge");
		if (repFriendly > 0)
			reputation.add("friendly terms");
		if (repQuarreler > 0)
			reputation.add("quarreler");
		if (repFlatterer > 0)
			reputation.add("flatterer");
		if (repTradePartner > 0)
			reputation.add("trade partner");
		if (repInformationSource > 0)
			reputation.add("source of information");
		if (meetCount>0)
			reputation.add("met "+meetCount+" times");

		if (reputation.isEmpty()) {
			reputation.add("unknown");
		}
		return String.join(", ", reputation);
	}

}
