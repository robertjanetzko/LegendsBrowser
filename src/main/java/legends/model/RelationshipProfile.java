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
	@Xml("rep_hero")
	private int repHero = -1;
	@Xml("rep_violent")
	private int repViolent = -1;
	@Xml("rep_psychopath")
	private int repPsychopath = -1;
	@Xml("rep_enemy_fighter")
	private int repEnemyFighter = -1;
	@Xml("rep_friendly_fighter")
	private int repFriendlyFighter = -1;
	@Xml("rep_killer")
	private int repKiller = -1;
	@Xml("rep_murderer")
	private int repMurderer = -1;
	@Xml("rep_poet")
	private int repPoet = -1;
	@Xml("rep_bard")
	private int repBard = -1;
	@Xml("rep_dancer")
	private int repDancer = -1;
	@Xml("rep_storyteller")
	private int repStoryteller = -1;
	@Xml("rep_treasure_hunter")
	private int repTreasureHunter = -1;
	@Xml("rep_knowledge_preserver")
	private int repKnowledgePreserver = -1;

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

	public int getRepHero() {
		return repHero;
	}

	public void setRepHero(int repHero) {
		this.repHero = repHero;
	}

	public int getRepPsychopath() {
		return repPsychopath;
	}

	public void setRepPsychopath(int repPsychopath) {
		this.repPsychopath = repPsychopath;
	}

	public int getRepViolent() {
		return repViolent;
	}

	public void setRepViolent(int repViolent) {
		this.repViolent = repViolent;
	}

	public int getRepEnemyFighter() {
		return repEnemyFighter;
	}

	public void setRepEnemyFighter(int repEnemyFighter) {
		this.repEnemyFighter = repEnemyFighter;
	}

	public int getRepFriendlyFighter() {
		return repFriendlyFighter;
	}

	public void setRepFriendlyFighter(int repFriendlyFighter) {
		this.repFriendlyFighter = repFriendlyFighter;
	}

	public int getRepKiller() {
		return repKiller;
	}

	public void setRepKiller(int repKiller) {
		this.repKiller = repKiller;
	}

	public int getRepMurderer() {
		return repMurderer;
	}

	public void setRepMurderer(int repMurderer) {
		this.repMurderer = repMurderer;
	}

	public int getRepPoet() {
		return repPoet;
	}

	public void setRepPoet(int repPoet) {
		this.repPoet = repPoet;
	}

	public int getRepBard() {
		return repBard;
	}

	public void setRepBard(int repBard) {
		this.repBard = repBard;
	}

		public int getRepDancer() {
		return repDancer;
	}

	public void setRepDancer(int repDancer) {
		this.repDancer = repDancer;
	}

	public int getRepStoryteller() {
		return repStoryteller;
	}

	public void setRepStoryteller(int repStoryteller) {
		this.repStoryteller = repStoryteller;
	}

	public int getRepTreasureHunter() {
		return repTreasureHunter;
	}

	public void setRepTreasureHunter(int repTreasureHunter) {
		this.repTreasureHunter = repTreasureHunter;
	}

	public int getRepKnowledgePreserver() {
		return repKnowledgePreserver;
	}

	public void setRepKnowledgePreserver(int repKnowledgePreserver) {
		this.repKnowledgePreserver = repKnowledgePreserver;
	}

	public String getType() {
		List<String> reputation = new ArrayList<String>();
		if (repBonded > 0)
			reputation.add("bonded"); // For animal partners.
		if (repBuddy > 0)
			reputation.add("friend");
		if (repGrudge > 0)
			reputation.add("grudge");
		if (repHero > 0)
			reputation.add("hero");
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
		if (repViolent > 0)
			reputation.add("violent");
		if (repPsychopath > 0)
			reputation.add("psychopath");
		if (repKiller > 0)
			reputation.add("killer");
		if (repMurderer > 0)
			reputation.add("murderer");
		if (repFriendlyFighter > 0)
			reputation.add("friendly fighter");
		if (repEnemyFighter > 0)
			reputation.add("enemy fighter");
		if (repPoet > 0)
			reputation.add("poet");
		if (repBard > 0)
			reputation.add("bard");
		if (repStoryteller > 0)
			reputation.add("storyteller");
		if (repDancer > 0)
			reputation.add("dancer");
		if (repTreasureHunter > 0)
			reputation.add("treasure hunter");
		if (repKnowledgePreserver > 0)
			reputation.add("preserver of knowledge");
		if (meetCount>0)
			reputation.add("met "+meetCount+" times");

		if (reputation.isEmpty()) {
			reputation.add("unknown");
		}
		return String.join(", ", reputation);
	}

}
