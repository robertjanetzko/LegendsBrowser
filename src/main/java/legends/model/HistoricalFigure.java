package legends.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class HistoricalFigure extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("race")
	private String race = "UNKNOWN RACE";
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

	@Xml(value = "used_identity_id", elementClass = Integer.class, multiple = true)
	private List<Integer> usedIdentityIds = new ArrayList<>();
	@Xml("current_identity_id")
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
	@Xml(value = "relationship_profile_hf_visual", elementClass = RelationshipProfile.class, multiple = true)
	private List<RelationshipProfile> relationshipProfilesVisual = new ArrayList<>();
	@Xml(value = "relationship_profile_hf_historical", elementClass = RelationshipProfile.class, multiple = true)
	private List<RelationshipProfile> relationshipProfilesHistorical = new ArrayList<>();
	@Xml(value = "relationship_profile_hf_identity", elementClass = RelationshipProfile.class, multiple = true)
	private List<RelationshipProfile> relationshipProfilesIdentity = new ArrayList<>();

	@Xml(value = "site_property", elementClass = SitePropertyLink.class, multiple = true)
	private List<SitePropertyLink> sitePropertyLinks = new ArrayList<>();
	@Xml(value = "vague_relationship", elementClass = VagueRelationship.class, multiple = true)
	private List<VagueRelationship> vagueRelationships = new ArrayList<>();

	@Xml(value = "goal", elementClass = String.class, multiple = true)
	private List<String> goals = new ArrayList<>();
	@Xml(value = "holds_artifact", elementClass = Integer.class, multiple = true)
	private List<Integer> artifacts = new ArrayList<>();
	@Xml(value = "sphere", elementClass = String.class, multiple = true)
	private List<String> spheres = new ArrayList<>();

	@Xml(value = "interaction_knowledge", elementClass = String.class, multiple = true)
	private List<String> interactionKnowledges = new ArrayList<>();
	private String activeInteraction;
	
	@Xml(value = "journey_pet", elementClass = String.class, multiple = true)
	private List<String> journeyPets = new ArrayList<>();

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

	private int kills = 0;

	@Xml(value = "intrigue_actor", elementClass = IntrigueActor.class, multiple = true)
	private List<IntrigueActor> intrigueActors = new ArrayList<>();

	@Xml(value = "intrigue_plot", elementClass = IntriguePlot.class, multiple = true)
	private List<IntriguePlot> intriguePlots = new ArrayList<>();

	private static HistoricalFigure context = null;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return EventHelper.race(race);
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
		List<RelationshipProfile> relationshipProfiles = relationshipProfilesVisual;
		relationshipProfiles.addAll(relationshipProfilesHistorical);
		relationshipProfiles.addAll(relationshipProfilesIdentity);
		return relationshipProfiles;
	}

	public List<RelationshipProfile> getRelevantRelationshipProfiles() {
		List<RelationshipProfile> relationshipProfiles = relationshipProfilesVisual;
		relationshipProfiles.addAll(relationshipProfilesHistorical);
		relationshipProfiles.addAll(relationshipProfilesIdentity);
		return relationshipProfiles.stream().filter(p -> p.getMeetCount() > 0).collect(Collectors.toList());
	}

	public List<SitePropertyLink> getSitePropertyLinks() {
		return sitePropertyLinks;
	}

	public List<VagueRelationship> getVagueRelationships() {
		return vagueRelationships;
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

	public List<String> getJourneyPets() {
		return journeyPets.stream().map(animal -> EventHelper.race(animal)).collect(Collectors.toList());
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

	public List<Integer> getUsedIdentityIds() {
		return usedIdentityIds;
	}

	public int getCurrentIdentityId() {
		return currentIdentityId;
	}

	public void setCurrentIdentityId(int currentIdentityId) {
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
		return Application.getSubUri() + "/hf/" + id;
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
				return "a <a href=\"" + getURL() + "\" class=\"historical-figure\">" + getRace() + type + "</a>";
			else
				return "the " + getRace() + type + " <a href=\"" + getURL() + "\" class=\"historical-figure\">"
						+ getName() + "</a>";
		else
			return getShortLink();
	}

	public String getFirstName() {
		String name = getName();
		if (name.contains(" "))
			return name.substring(0, name.indexOf(" "));
		return name;
	}

	public String getShortLink() {
		if (context != null && context.id == id) {
			String firstName = getFirstName();
			return "<a href=\"" + getURL() + "\" class=\"historical-figure\">" + firstName + "</a>";
		}
		return "<a href=\"" + getURL() + "\" class=\"historical-figure\">" + getName() + "</a>";
	}

	public String getPronoun() {
		if (caste != null && caste.equals("FEMALE"))
			return "she";
		else
			return "he";
	}

	public String getPronounWithCapitalFirst() {
		String pro = getPronoun();
		return pro.substring(0, 1).toUpperCase() + pro.substring(1);
	}

	public String getPossesivePronoun() {
		if (caste != null && caste.equals("FEMALE"))
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
				.map(HistoricalFigureLink::getHistoricalFigureId).map(World::getHistoricalFigure).sorted(byAge())
				.collect(Collectors.toList());
	}

	public static Comparator<HistoricalFigure> byAge() {
		return (h1, h2) -> (h1.birthYear < h2.birthYear ? -1 : 1);
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

	public boolean isMale() {
		return sex == 1 || "MALE".equals(caste);
	}

	public void process() {
		Stream.concat(entityFormerPositionLinks.stream(), entityPositionLinks.stream()).forEach(l -> {
			World.getEntity(l.getEntityId()).getHfPositions().put(l, id);
		});

	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void addKill() {
		this.kills++;
	}

	public List<IntrigueActor> getIntrigueActors() {
		return intrigueActors;
	}

	public List<IntriguePlot> getIntriguePlots() {
		return intriguePlots;
	}

}
