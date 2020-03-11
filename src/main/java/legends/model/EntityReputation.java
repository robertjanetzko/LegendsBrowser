package legends.model;

import legends.xml.annotation.Xml;

/**
The entity and hf reputation types correspond to the wiki, but it is unknown           
what their tags are...
There's still comrade, member of respected group,
member of hated group, friendly fighter, bully, brigand, loyal soldier, monster,
flatterer, hunter, thief, and intruder.
.47 seems to add something along the lines of preacher.
*/

public class EntityReputation {
	@Xml("entity_id")
	private int entityId;
	@Xml("first_ageless_year")
	private int firstAgelessYear;
	@Xml("first_ageless_season_count")
	private int firstAgelessSeasonCount;
	@Xml("unsolved_murders")
	private int unsolvedMurders;

	@Xml("rep_hero")
	private int repHero = -1;
	@Xml("rep_violent")
	private int repViolent = -1;
	@Xml("rep_psychopath")
	private int repPsychopath = -1;
	@Xml("rep_trade_partner")
	private int repTradePartner = -1;
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
	@Xml("rep_preacher")
	private int repPreacher = -1;
	@Xml("rep_brigand")
	private int repBrigand = -1;
	@Xml("rep_intruder")
	private int repIntruder = -1;
	@Xml("rep_monster")
	private int repMonster = -1;
	@Xml("rep_thief")
	private int repThief = -1;
	@Xml("rep_hated_group")
	private int repHatedGroup = -1;
	@Xml("rep_respectedGroup")
	private int repRespectedGroup = -1;
	@Xml("rep_hunter")
	private int repHunter = -1;
	@Xml("rep_loyal_soldier")
	private int repLoyalSoldier = -1;
	@Xml("rep_comrade")
	private int repComrade = -1;
	@Xml("rep_bully")
	private int repBully = -1;
	

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getFirstAgelessYear() {
		return firstAgelessYear;
	}

	public void setFirstAgelessYear(int firstAgelessYear) {
		this.firstAgelessYear = firstAgelessYear;
	}

	public int getFirstAgelessSeasonCount() {
		return firstAgelessSeasonCount;
	}

	public void setFirstAgelessSeasonCount(int firstAgelessSeasonCount) {
		this.firstAgelessSeasonCount = firstAgelessSeasonCount;
	}

	public int getUnsolvedMurders() {
		return unsolvedMurders;
	}

	public void setUnsolvedMurders(int unsolvedMurders) {
		this.unsolvedMurders = unsolvedMurders;
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

	public int getRepTradePartner() {
		return repTradePartner;
	}

	public void setRepTradePartner(int repTradePartner) {
		this.repTradePartner = repTradePartner;
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
	
	public int getRepPreacher() {
		return repPreacher;
	}

	public void setRepPreacher(int repPreacher) {
		this.repPreacher = repPreacher;
	}

	public int getRepBrigand() {
		return repBrigand;
	}

	public void setRepBrigand(int repBrigand) {
		this.repBrigand = repBrigand;
	}

	public int getRepIntruder() {
		return repIntruder;
	}

	public void setRepIntruder(int repIntruder) {
		this.repIntruder = repIntruder;
	}

	public int getRepMonster() {
		return repMonster;
	}

	public void setRepMonster(int repMonster) {
		this.repMonster = repMonster;
	}

	public int getRepThief() {
		return repThief;
	}

	public void setRepThief(int repThief) {
		this.repThief = repThief;
	}

	public int getRepHatedGroup() {
		return repHatedGroup;
	}

	public void setRepHatedGroup(int repHatedGroup) {
		this.repHatedGroup = repHatedGroup;
	}

	public int getRepRespectedGroup() {
		return repRespectedGroup;
	}

	public void setRepRespectedGroup(int repRespectedGroup) {
		this.repRespectedGroup = repRespectedGroup;
	}

	public int getRepHunter() {
		return repHunter;
	}

	public void setRepHunter(int repHunter) {
		this.repHunter = repHunter;
	}

	public int getRepLoyalSoldier() {
		return repLoyalSoldier;
	}

	public void setRepLoyalSoldier(int repLoyalSoldier) {
		this.repLoyalSoldier = repLoyalSoldier;
	}

	public int getRepComrade() {
		return repComrade;
	}

	public void setRepComrade(int repComrade) {
		this.repComrade = repComrade;
	}

	public int getRepBully() {
		return repBully;
	}

	public void setRepBully(int repBully) {
		this.repBully = repBully;
	}
}
