package legends.model;

import legends.xml.annotation.Xml;

/**
The entity and hf reputation types correspond to the wiki, but it is unknown           
what their tags are...
There's still grudge, brawler, murderer, comrade, member of respected group,
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
	@Xml("rep_trade_partner")
	private int repTradePartner = -1;
	@Xml("rep_enemy_fighter")
	private int repEnemyFighter = -1;
	@Xml("rep_killer")
	private int repKiller = -1;
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

	public int getRepKiller() {
		return repKiller;
	}

	public void setRepKiller(int repKiller) {
		this.repKiller = repKiller;
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

}
