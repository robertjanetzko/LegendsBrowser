package legends.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricalEventCollection {
	private int id;
	private String name;
	private int startYear;
	private int startSeconds72;
	private int endYear;
	private int endSeconds72;
	private List<Integer> events = new ArrayList<>();
	private List<Integer> eventCols = new ArrayList<>();
	private String type;
	private int ordinal;
	private int civId;
	private int occasionId;
	private int parentEventcol;
	private int subregionId;
	private int featureLayerId;
	private int siteId;
	private int x;
	private int y;
	private int attackingEnId;
	private int defendingEnId;
	private int attackingHfId;
	private int defendingHfId;

	private int aggressorEntId;
	private int defenderEntId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getStartSeconds72() {
		return startSeconds72;
	}

	public void setStartSeconds72(int startSeconds72) {
		this.startSeconds72 = startSeconds72;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getEndSeconds72() {
		return endSeconds72;
	}

	public void setEndSeconds72(int endSeconds72) {
		this.endSeconds72 = endSeconds72;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public List<Integer> getEvents() {
		return events;
	}

	public List<Integer> getEventCols() {
		return eventCols;
	}

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	public int getParentEventcol() {
		return parentEventcol;
	}

	public void setParentEventcol(int parentEventcol) {
		this.parentEventcol = parentEventcol;
	}

	public int getSubregionId() {
		return subregionId;
	}

	public void setSubregionId(int subregionId) {
		this.subregionId = subregionId;
	}

	public int getFeatureLayerId() {
		return featureLayerId;
	}

	public void setFeatureLayerId(int featureLayerId) {
		this.featureLayerId = featureLayerId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

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

	public int getAttackingHfId() {
		return attackingHfId;
	}

	public void setAttackingHfId(int attackingHfId) {
		this.attackingHfId = attackingHfId;
	}

	public int getDefendingHfId() {
		return defendingHfId;
	}

	public void setDefendingHfId(int defendingHfId) {
		this.defendingHfId = defendingHfId;
	}

	public int getAggressorEntId() {
		return aggressorEntId;
	}

	public void setAggressorEntId(int aggressorEntId) {
		this.aggressorEntId = aggressorEntId;
	}

	public int getDefenderEntId() {
		return defenderEntId;
	}

	public void setDefenderEntId(int defenderEntId) {
		this.defenderEntId = defenderEntId;
	}

}
