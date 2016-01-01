package legends.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricalEvent {
	private int id;
	private int year;
	private int seconds72;
	private String type;
	private String subtype;
	private int hfId;
	private int hfIdTarget;
	private String state;
	private int civId;
	private int siteCivId;
	private int siteId;
	private int subregionId;
	private int featureLayerId;
	private int x;
	private int y;
	private int builderHfId;
	private int structureId;
	private int artifactId;
	private int unitId;
	private int histFigureId;
	private int agreementId;
	private int entityId;
	private int groupHfId;
	private int group1HfId;
	private List<Integer> group2HfIds = new ArrayList<>();
	private boolean returned;

	private int occasionId;
	private int scheduleId;

	private int winnerHfId;
	private List<Integer> competitorHfIds = new ArrayList<>();

	private int slayerHfId;
	private String slayerRace;
	private String slayerCaste;
	private int slayerItemId;
	private int slayerShooterItemId;
	private String cause;

	private int attackerHfId;

	private int attackerCivId;
	private int defenderCivId;
	private int attackerGeneralHfId;
	private int defenderGeneralHfId;

	private int woundeeHfId;
	private int wounderHfId;

	private int wcId;
	private String reason;
	private int reasonId;
	private String circumstance;
	private int circumstanceId;

	private int targetHfId;
	private int snatcherHfId;

	private String knowledge;
	private boolean first;

	private int changeeHfId;
	private int changerHfId;
	private String oldRace;
	private String oldCaste;
	private String newRace;
	private String newCaste;

	private String secretGoal;

	private String dispute;
	private int entityId1;
	private int entityId2;
	private int siteId1;
	private int siteId2;

	private String bodyState;
	private int buildingId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSeconds72() {
		return seconds72;
	}

	public void setSeconds72(int seconds72) {
		this.seconds72 = seconds72;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	public int getHfIdTarget() {
		return hfIdTarget;
	}

	public void setHfIdTarget(int hfIdTarget) {
		this.hfIdTarget = hfIdTarget;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getSiteCivId() {
		return siteCivId;
	}

	public void setSiteCivId(int siteCivId) {
		this.siteCivId = siteCivId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
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

	public int getBuilderHfId() {
		return builderHfId;
	}

	public void setBuilderHfId(int builderHfId) {
		this.builderHfId = builderHfId;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getHistFigureId() {
		return histFigureId;
	}

	public void setHistFigureId(int histFigureId) {
		this.histFigureId = histFigureId;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getGroupHfId() {
		return groupHfId;
	}

	public void setGroupHfId(int groupHfId) {
		this.groupHfId = groupHfId;
	}

	public int getGroup1HfId() {
		return group1HfId;
	}

	public void setGroup1HfId(int group1HfId) {
		this.group1HfId = group1HfId;
	}

	public List<Integer> getGroup2HfIds() {
		return group2HfIds;
	}

	public boolean isReturn() {
		return returned;
	}

	public void setReturn(boolean returned) {
		this.returned = returned;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getWinnerHfId() {
		return winnerHfId;
	}

	public void setWinnerHfId(int winnerHfId) {
		this.winnerHfId = winnerHfId;
	}

	public List<Integer> getCompetitorHfIds() {
		return competitorHfIds;
	}

	public int getSlayerHfId() {
		return slayerHfId;
	}

	public void setSlayerHfId(int slayerHfId) {
		this.slayerHfId = slayerHfId;
	}

	public String getSlayerRace() {
		return slayerRace;
	}

	public void setSlayerRace(String slayerRace) {
		this.slayerRace = slayerRace;
	}

	public String getSlayerCaste() {
		return slayerCaste;
	}

	public void setSlayerCaste(String slayerCaste) {
		this.slayerCaste = slayerCaste;
	}

	public int getSlayerItemId() {
		return slayerItemId;
	}

	public void setSlayerItemId(int slayerItemId) {
		this.slayerItemId = slayerItemId;
	}

	public int getSlayerShooterItemId() {
		return slayerShooterItemId;
	}

	public void setSlayerShooterItemId(int slayerShooterItemId) {
		this.slayerShooterItemId = slayerShooterItemId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public int getAttackerHfId() {
		return attackerHfId;
	}

	public void setAttackerHfId(int attackerHfId) {
		this.attackerHfId = attackerHfId;
	}

	public int getDefenderCivId() {
		return defenderCivId;
	}

	public void setDefenderCivId(int defenderCivId) {
		this.defenderCivId = defenderCivId;
	}

	public int getAttackerCivId() {
		return attackerCivId;
	}

	public void setAttackerCivId(int attackerCivId) {
		this.attackerCivId = attackerCivId;
	}

	public int getAttackerGeneralHfId() {
		return attackerGeneralHfId;
	}

	public void setAttackerGeneralHfId(int attackerGeneralHfId) {
		this.attackerGeneralHfId = attackerGeneralHfId;
	}

	public int getDefenderGeneralHfId() {
		return defenderGeneralHfId;
	}

	public void setDefenderGeneralHfId(int defenderGeneralHfId) {
		this.defenderGeneralHfId = defenderGeneralHfId;
	}

	public int getWoundeeHfId() {
		return woundeeHfId;
	}

	public void setWoundeeHfId(int woundeeHfId) {
		this.woundeeHfId = woundeeHfId;
	}

	public int getWounderHfId() {
		return wounderHfId;
	}

	public void setWounderHfId(int wounderHfId) {
		this.wounderHfId = wounderHfId;
	}

	public int getWcId() {
		return wcId;
	}

	public void setWcId(int wcId) {
		this.wcId = wcId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	public String getCircumstance() {
		return circumstance;
	}

	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

	public int getCircumstanceId() {
		return circumstanceId;
	}

	public void setCircumstanceId(int circumstanceId) {
		this.circumstanceId = circumstanceId;
	}

	public int getTargetHfId() {
		return targetHfId;
	}

	public void setTargetHfId(int targetHfId) {
		this.targetHfId = targetHfId;
	}

	public int getSnatcherHfId() {
		return snatcherHfId;
	}

	public void setSnatcherHfId(int snatcherHfId) {
		this.snatcherHfId = snatcherHfId;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getChangeeHfId() {
		return changeeHfId;
	}

	public void setChangeeHfId(int changeeHfId) {
		this.changeeHfId = changeeHfId;
	}

	public int getChangerHfId() {
		return changerHfId;
	}

	public void setChangerHfId(int changerHfId) {
		this.changerHfId = changerHfId;
	}

	public String getOldRace() {
		return oldRace;
	}

	public void setOldRace(String oldRace) {
		this.oldRace = oldRace;
	}

	public String getOldCaste() {
		return oldCaste;
	}

	public void setOldCaste(String oldCaste) {
		this.oldCaste = oldCaste;
	}

	public String getNewRace() {
		return newRace;
	}

	public void setNewRace(String newRace) {
		this.newRace = newRace;
	}

	public String getNewCaste() {
		return newCaste;
	}

	public void setNewCaste(String newCaste) {
		this.newCaste = newCaste;
	}

	public String getSecretGoal() {
		return secretGoal;
	}

	public void setSecretGoal(String secretGoal) {
		this.secretGoal = secretGoal;
	}

	public String getDispute() {
		return dispute;
	}

	public void setDispute(String dispute) {
		this.dispute = dispute;
	}

	public int getEntityId1() {
		return entityId1;
	}

	public void setEntityId1(int entityId1) {
		this.entityId1 = entityId1;
	}

	public int getEntityId2() {
		return entityId2;
	}

	public void setEntityId2(int entityId2) {
		this.entityId2 = entityId2;
	}

	public int getSiteId1() {
		return siteId1;
	}

	public void setSiteId1(int siteId1) {
		this.siteId1 = siteId1;
	}

	public int getSiteId2() {
		return siteId2;
	}

	public void setSiteId2(int siteId2) {
		this.siteId2 = siteId2;
	}

	public String getBodyState() {
		return bodyState;
	}

	public void setBodyState(String bodyState) {
		this.bodyState = bodyState;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public String toString() {
		return "[" + id + "] in " + year + " " + hfId + " " + type + " (" + state + ")" + " in " + siteId;
	}

}
