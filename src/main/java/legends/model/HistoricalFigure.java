package legends.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class HistoricalFigure extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("race")
	private String race;
	@Xml("caste")
	private String caste;
	@Xml("sex")
	private int sex = -1;
	@Xml("breed_id")
	private int breedId = -1;
	@Xml("appeared")
	private int appeared;
	@Xml("birth_year")
	private int birthYear;
	@Xml("birth_seconds72")
	private int birthSeconds;
	@Xml("death_year")
	private int deathYear;
	@Xml("death_seconds72")
	private int deathSeconds;
	@Xml("associated_type")
	private String associatedType;
	@Xml("ent_pop_id")
	private int entPopId;
	private int entityId;
	private int usedIdentityId;
	private int currentIdentityId;

	@Xml(value = "entity_link", elementClass = EntityLink.class, multiple = true)
	private List<EntityLink> entityLinks = new ArrayList<>();
	@Xml(value = "hf_link", elementClass = HistoricalFigureLink.class, multiple = true)
	private List<HistoricalFigureLink> historicalFigureLinks = new ArrayList<>();
	@Xml(value = "hf_skill", elementClass = HistoricalFigureSkill.class, multiple = true)
	private List<HistoricalFigureSkill> historicalFigureSkills = new ArrayList<>();
	@Xml(value = "site_link", elementClass = SiteLink.class, multiple = true)
	private List<SiteLink> siteLinks = new ArrayList<>();
	@Xml(value = "entity_position_link", elementClass = EntityPositionLink.class, multiple = true)
	private List<EntityPositionLink> entityPositionLinks = new ArrayList<>();
	@Xml(value = "entity_former_position_link", elementClass = EntityPositionLink.class, multiple = true)
	private List<EntityPositionLink> entityFormerPositionLinks = new ArrayList<>();

	@Xml(value = "entity_reputation", elementClass = EntityReputation.class, multiple = true)
	private List<EntityReputation> entityReputations = new ArrayList<>();
	@Xml(value = "entity_squad_link", elementClass = EntitySquadLink.class, multiple = true)
	private List<EntitySquadLink> entitySquadLinks = new ArrayList<>();
	@Xml(value = "entity_former_squad_link", elementClass = EntitySquadLink.class, multiple = true)
	private List<EntitySquadLink> entityFormerSquadLinks = new ArrayList<>();
	@Xml(value = "relationship_profile_hf", elementClass = RelationshipProfile.class, multiple = true)
	private List<RelationshipProfile> relationshipProfiles = new ArrayList<>();

	@Xml(value = "goal", elementClass = String.class, multiple = true)
	private List<String> goals = new ArrayList<>();
	@Xml(value = "holds_artifact", elementClass = Integer.class, multiple = true)
	private List<Integer> artifacts = new ArrayList<>();
	@Xml(value = "sphere", elementClass = String.class, multiple = true)
	private List<String> spheres = new ArrayList<>();

	@Xml(value = "interaction_knowledge", elementClass = String.class, multiple = true)
	private List<String> interactionKnowledges = new ArrayList<>();
	private String activeInteraction;
	@Xml("journey_pet")
	private String journeyPet;

	@Xml("adventurer")
	private boolean adventurer = false;
	@Xml("deity")
	private boolean deity = false;
	@Xml("force")
	private boolean force = false;
	@Xml("ghost")
	private boolean ghost = false;

	private boolean leader = false;
	private boolean vampire = false;
	private boolean werebeast = false;
	private boolean necromancer = false;

	@Xml("animated")
	private boolean animated = false;
	@Xml("animated_string")
	private String animatedString;

	private static HistoricalFigure context = null;

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getBreedId() {
		return breedId;
	}

	public void setBreedId(int breedId) {
		this.breedId = breedId;
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

	public List<EntityPositionLink> getEntityFormerPositionLinks() {
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

	@Xml("active_interaction")
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

	public boolean isAdventurer() {
		return adventurer;
	}

	public void setAdventurer(boolean adventurer) {
		this.adventurer = adventurer;
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

	@Xml("used_identity_id")
	public void setUsedIdentityId(int usedIdentityId) {
		System.out.println(id + " used " + usedIdentityId);
		this.usedIdentityId = usedIdentityId;
	}

	public int getCurrentIdentityId() {
		return currentIdentityId;
	}

	@Xml("current_identity_id")
	public void setCurrentIdentityId(int currentIdentityId) {
		System.out.println(id + " current " + currentIdentityId);
		this.currentIdentityId = currentIdentityId;
	}

	public static void setContext(HistoricalFigure context) {
		HistoricalFigure.context = context;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + race + ")";
	}

	public String getURL() {
		return "/hf/" + id;
	}

	public String getLink() {
		if (context != null && context.id == id) {
			String firstName = getName();
			if (firstName.contains(" "))
				firstName = firstName.substring(0, firstName.indexOf(" "));
			return "<a href=\"" + getURL() + "\" class=\"historical-figure\">" + firstName + "</a>";
		}
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
			if (getName().equals("UNKNOWN"))
				return "a <a href=\"" + getURL() + "\" class=\"historical-figure\">" + race.toLowerCase() + type
						+ "</a>";
			else
				return "the " + race.toLowerCase() + type + " <a href=\"" + getURL() + "\" class=\"historical-figure\">"
						+ getName() + "</a>";
		else
			return "<a href=\"" + getURL() + "\" class=\"historical-figure\">" + getName() + "</a>";
	}

	public String getPronoun() {
		if (caste.equals("FEMALE"))
			return "he";
		else
			return "she";
	}

	public String getPossesivePronoun() {
		if (caste.equals("FEMALE"))
			return "her";
		else
			return "his";
	}

	public List<EntityPositionLink> getPositions() {
		return getPositions(null);
	}

	public List<EntityPositionLink> getPositions(Entity entity) {
		return Stream.concat(entityFormerPositionLinks.stream(), entityPositionLinks.stream())
				.filter(l -> entity == null || l.getEntityId() == entity.getId()).collect(Collectors.toList());
	}

	public List<HistoricalFigure> getHfLinks(String link) {
		return historicalFigureLinks.stream().filter(l -> link.equals(l.getLinkType()))
				.map(HistoricalFigureLink::getHistoricalFigureId).map(World::getHistoricalFigure)
				.collect(Collectors.toList());
	}

	public HistoricalFigure getHfLink(String link) {
		List<HistoricalFigure> list = getHfLinks(link);
		if (list.isEmpty())
			return World.UNKNOWN_HISTORICAL_FIGURE;
		else
			return list.get(0);
	}

	public boolean isFemale() {
		return sex == 0 || "FEMALE".equals(caste);
	}

}
