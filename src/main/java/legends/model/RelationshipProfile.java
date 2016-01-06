package legends.model;

public class RelationshipProfile {
	private int hfId = -1;
	private int meetCount = -1;
	private int lastMeetYear = -1;
	private int lastMeetSeconds = -1;
	private int repFriendly = -1;
	private int repBuddy = -1;
	private int repQuarreler = -1;

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
		System.out.println(hfId+" "+repQuarreler);
		this.repQuarreler = repQuarreler;
	}
	
	public String getType() {
		if(repBuddy > 0)
			return "Friend";
		if(repFriendly > 0)
			return "Friendly Terms";
		if(repQuarreler > 0)
			return "Quarreler";
		return "unknown";
	}

}
