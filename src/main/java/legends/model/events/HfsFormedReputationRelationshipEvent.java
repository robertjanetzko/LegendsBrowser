package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hfs formed reputation relationship")
public class HfsFormedReputationRelationshipEvent extends Event implements HfRelatedEvent, LocalEvent {
	@Xml("hfid1")
	private int hfId1 = -1;
	@Xml("identity_id1")
	private int identityId1 = -1;

	@Xml("hfid2")
	private int hfId2 = -1;
	@Xml("identity_id2")
	private int identityId2 = -1;

	@Xml(value = "hf_rep_1_of_2", track = true)
	private String hfRep1of2 = "";
	@Xml(value = "hf_rep_2_of_1", track = true)
	private String hfRep2of1 = "";

	@XmlComponent
	private EventLocation location = new EventLocation("the depths of the world");

	public int getHfId1() {
		return hfId1;
	}

	public void setHfId1(int hfId1) {
		this.hfId1 = hfId1;
	}

	public int getIdentityId1() {
		return identityId1;
	}

	public void setIdentityId1(int identityId1) {
		this.identityId1 = identityId1;
	}

	public int getHfId2() {
		return hfId2;
	}

	public void setHfId2(int hfId2) {
		this.hfId2 = hfId2;
	}

	public int getIdentityId2() {
		return identityId2;
	}

	public void setIdentityId2(int identityId2) {
		this.identityId2 = identityId2;
	}

	public String getHfRep1of2() {
		return hfRep1of2;
	}

	public void setHfRep1of2(String hfRep1of2) {
		this.hfRep1of2 = hfRep1of2;
	}

	public String getHfRep2of1() {
		return hfRep2of1;
	}

	public void setHfRep2of1(String hfRep2of1) {
		this.hfRep2of1 = hfRep2of1;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return hfId1 == hfId || hfId2 == hfId;
	}

	@Override
	public String getShortDescription() {
		String hf1 = World.getHistoricalFigure(hfId1).getLink();
		if (identityId1 != -1)
			hf1 += ", as \"" + identityId1 + "\"";
		String hf2 = World.getHistoricalFigure(hfId2).getLink();
		if (identityId2 != -1)
			hf2 += ", as \"" + identityId2 + "\"";
		String loc = location.getLink("in");

		if ("information source".equals(hfRep1of2) && "information source".equals(hfRep2of1))
			return hf1 + " and " + hf2 + ", formed a false friendship where each used the other for information" + loc;
		if ("information source".equals(hfRep1of2) && "buddy".equals(hfRep2of1))
			return hf1 + ", formed a false friendship with " + hf2 + "in order to extract information" + loc;
		return hf1 + " and " + hf2 + loc;
	}

}
