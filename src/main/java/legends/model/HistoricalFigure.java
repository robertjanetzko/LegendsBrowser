package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.helper.EventHelper;

public class HistoricalFigure {
	private int id = -1;
	private String name;
	private String race;
	private String caste;
	private int appeared;
	private int birthYear;
	private int birthSeconds;
	private int deathYear;
	private int deathSeconds;
	private String associatedType;
	private int entPopId;
	private int entityId;
	private int usedIdentityId;

	private List<EntityLink> entityLinks = new ArrayList<>();
	private List<EntityFormerPositionLink> entityFormerPositionLinks = new ArrayList<>();
	private List<HistoricalFigureLink> historicalFigureLinks = new ArrayList<>();
	private List<HistoricalFigureSkill> historicalFigureSkills = new ArrayList<>();
	private List<EntityReputation> entityReputations = new ArrayList<>();
	private List<SiteLink> siteLinks = new ArrayList<>();
	private List<EntityPositionLink> entityPositionLinks = new ArrayList<>();
	private List<EntitySquadLink> entitySquadLinks = new ArrayList<>();
	private List<EntitySquadLink> entityFormerSquadLinks = new ArrayList<>();
	private List<RelationshipProfile> relationshipProfiles = new ArrayList<>();

	private List<String> goals = new ArrayList<>();
	private List<Integer> artifacts = new ArrayList<>();
	private List<String> spheres = new ArrayList<>();

	private List<String> interactionKnowledges = new ArrayList<>();
	private String activeInteraction;
	private String journeyPet;

	private boolean deity = false;
	private boolean force = false;
	private boolean ghost = false;

	private boolean leader = false;
	private boolean vampire = false;
	private boolean werebeast = false;
	private boolean necromancer = false;

	private boolean animated = false;
	private String animatedString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public int getAppeared() {
		return appeared;
	}

	public void setAppeared(int appeared) {
		this.appeared = appeared;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthSeconds() {
		return birthSeconds;
	}

	public void setBirthSeconds(int birthSeconds) {
		this.birthSeconds = birthSeconds;
	}

	public int getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}

	public int getDeathSeconds() {
		return deathSeconds;
	}

	public void setDeathSeconds(int deathSeconds) {
		this.deathSeconds = deathSeconds;
	}

	public String getAssociatedType() {
		return associatedType;
	}

	public void setAssociatedType(String associatedType) {
		this.associatedType = associatedType;
	}

	public int getEntPopId() {
		return entPopId;
	}

	public void setEntPopId(int entPopId) {
		this.entPopId = entPopId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public List<EntityLink> getEntityLinks() {
		return entityLinks;
	}

	public List<EntityFormerPositionLink> getEntityFormerPositionLinks() {
		return entityFormerPositionLinks;
	}

	public List<HistoricalFigureLink> getHistoricalFigureLinks() {
		return historicalFigureLinks;
	}

	public List<HistoricalFigureSkill> getHistoricalFigureSkills() {
		return historicalFigureSkills;
	}

	public List<String> getGoals() {
		return goals;
	}

	public List<Integer> getArtifacts() {
		return artifacts;
	}

	public List<String> getSpheres() {
		return spheres;
	}

	public List<SiteLink> getSiteLinks() {
		return siteLinks;
	}

	public List<EntityPositionLink> getEntityPositionLinks() {
		return entityPositionLinks;
	}

	public List<EntityReputation> getEntityReputations() {
		return entityReputations;
	}

	public List<EntitySquadLink> getEntitySquadLinks() {
		return entitySquadLinks;
	}

	public List<EntitySquadLink> getEntityFormerSquadLinks() {
		return entityFormerSquadLinks;
	}

	public List<RelationshipProfile> getRelationshipProfiles() {
		return relationshipProfiles;
	}

	public List<RelationshipProfile> getRelevantRelationshipProfiles() {
		return relationshipProfiles.stream().filter(p -> p.getMeetCount() > 0).collect(Collectors.toList());
	}

	public List<String> getInteractionKnowledges() {
		return interactionKnowledges;
	}

	public String getActiveInteraction() {
		return activeInteraction;
	}

	public void setActiveInteraction(String activeInteraction) {
		this.activeInteraction = activeInteraction;
		if (activeInteraction.startsWith("DEITY_CURSE_VAMPIRE_"))
			vampire = true;
		if (activeInteraction.startsWith("DEITY_CURSE_WEREBEAST_"))
			werebeast = true;
		if (activeInteraction.startsWith("SECRET_"))
			necromancer = true;
	}

	public String getJourneyPet() {
		return journeyPet;
	}

	public void setJourneyPet(String journeyPet) {
		this.journeyPet = journeyPet;
	}

	public boolean isDeity() {
		return deity;
	}

	public void setDeity(boolean deity) {
		this.deity = deity;
	}

	public boolean isForce() {
		return force;
	}

	public void setForce(boolean force) {
		this.force = force;
	}

	public boolean isGhost() {
		return ghost;
	}

	public void setGhost(boolean ghost) {
		this.ghost = ghost;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public boolean isVampire() {
		return vampire;
	}

	public void setVampire(boolean vampire) {
		this.vampire = vampire;
	}

	public boolean isWerebeast() {
		return werebeast;
	}

	public void setWerebeast(boolean werebeast) {
		this.werebeast = werebeast;
	}

	public boolean isNecromancer() {
		return necromancer;
	}

	public void setNecromancer(boolean necromancer) {
		this.necromancer = necromancer;
	}

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}

	public String getAnimatedString() {
		return animatedString;
	}

	public void setAnimatedString(String animatedString) {
		this.animatedString = animatedString;
	}

	public int getUsedIdentityId() {
		return usedIdentityId;
	}

	public void setUsedIdentityId(int usedIdentityId) {
		this.usedIdentityId = usedIdentityId;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + race + ")";
	}

	public String getURL() {
		return "/hf/" + id;
	}

	public String getLink() {
		String type = "";
		if (vampire)
			type = " vampire";
		if (werebeast)
			type = " werebeast";
		if (necromancer)
			type = " necromancer";
		if (deity)
			type = " deity";
		if (force)
			type = " force";

		if (id == -1)
			return "<i>UNKNOWN HISTORICAL FIGURE</i>";

		if (race != null)
			return "the " + race.toLowerCase() + type + " <a href=\"/hf/" + id + "\" class=\"historical-figure\">"
					+ getName() + "</a>";
		else
			return "<a href=\"" + getURL() + "\" class=\"historical-figure\">" + getName() + "</a>";
	}

	public String getPronoun() {
		if (caste.equals("FEMALE"))
			return "her";
		else
			return "his";
	}

}
