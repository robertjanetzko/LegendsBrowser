package legends.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import legends.Application;
import legends.helper.EventHelper;
import legends.model.basic.AbstractObject;
import legends.model.collections.OccasionCollection;
import legends.model.collections.WarCollection;
import legends.model.events.basic.Coords;
import legends.model.events.basic.Filters;
import legends.model.EntityPosition;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlConverter;
import legends.xml.converter.CoordListConverter;

public class Entity extends AbstractObject {
	@Xml("name")
	private String name;
	@Xml("race")
	private String race = "unknown";
	@Xml("type")
	private String type = "unknown";
	private Set<Site> sites = new LinkedHashSet<>();
	private Entity parent;
	private List<Leader> leaders = new ArrayList<>();
	@Xml(value = "child", elementClass = Integer.class, multiple = true)
	private List<Integer> children = new ArrayList<>();
	@Xml(value = "entity_link", elementClass = EntityLink.class, multiple = true)
	private List<EntityLink> entityLinks = new ArrayList<>();
	@Xml(value = "entity_position", elementClass = EntityPosition.class, multiple = true)
	private Map<Integer, EntityPosition> positions = new HashMap<>();
	@Xml(value = "entity_position_assignment", elementClass = EntityPositionAssignment.class, multiple = true)
	private Map<Integer, EntityPositionAssignment> assignments = new HashMap<>();
	@Xml(value = "histfig_id", elementClass = Integer.class, multiple = true)
	private List<Integer> hfIds = new ArrayList<>();
	@Xml(value = "worship_id", elementClass = Integer.class, multiple = true)
	private List<Integer> worshipIds = new ArrayList<>();
	@Xml("claims")
	@XmlConverter(CoordListConverter.class)
	private List<Coords> claims = new ArrayList<>();
	@Xml(value = "occasion", elementClass = Occasion.class, multiple = true)
	private Map<Integer, Occasion> occasions = new LinkedHashMap<>();

	@Xml(value = "honor", elementClass = EntityHonor.class, multiple = true)
	private List<EntityHonor> honors = new ArrayList<>();
	@Xml(value = "weapon", elementClass = String.class, multiple = true)
	private List<String> weapons = new ArrayList<>();
	@Xml(value = "profession")
	private String profession = "unknown profession";

	private Map<EntityPositionLink, Integer> hfPositions = new HashMap<>();

	private boolean fallen = false;

	private final static Occasion UNKNOWN_OCCASION = new Occasion();
	private final static EntityPosition UNKNOWN_POSITION = new EntityPosition();

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

	public Entity getRoot() {
		if (id == -1)
			return this;
		if (parent == null)
			return this;
		else
			return parent.getRoot();
	}

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity parent) {
		if (id != -1 && parent.id != -1 && this != parent && parent != null)
			this.parent = parent;
	}

	public Set<Site> getSites() {
		return sites;
	}

	public List<Leader> getLeaders() {
		return leaders;
	}

	public String getType() {
		switch (type) {
		case "merchantcompany":
			return "merchant company";
		case "migratinggroup":
			return "group";
		case "nomadicgroup":
			return "bandit gang";
		case "militaryunit":
			return "mercenary order";
		case "outcast":
			return "collection of outcasts";
		case "performancetroupe":
			return "performance troupe";
		case "sitegovernment":
			return "group";
		case "guild":
		case "religion":
		default:
			return type;
		}
	}
	
	public String getShortDescription() {
		String description = "";
		if (race != "unknown")
			description += race + " ";
		description += getType();
		switch (type) {
		case "religion":
			if (worshipIds.size() > 0)
				description += " centered around the worship of " + World.getHistoricalFigure(worshipIds.get(0)).getLink();
			break;
		case "militaryunit":
			if (worshipIds.size() > 0)
				description += " devoted to the worship of " + World.getHistoricalFigure(worshipIds.get(0)).getLink();
			if (weapons.size() > 0)
				description += ", dedicated to the mastery of " + weapons.stream().map(s -> "the "+s).collect(EventHelper.stringList());
			break;
		case "guild":
			description += " of " + profession + "s";
			break;
		}
		return EventHelper.capitalize(description);
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getChildren() {
		return children;
	}

	public List<EntityLink> getEntityLinks() {
		return entityLinks;
	}

	private final static String[][] DWARF_POSITIONS = {
			// these positions are defined in raw/objects/entity_default.txt
			{ "king", "queen", "king consort", "queen consort" }, { "general" }, { "lieutenant" }, { "captain" },
			{ "militia commander" }, { "militia captain" }, { "sheriff" }, { "captain of the guard" },
			{ "expedition leader" }, { "mayor" }, { "manager" }, { "chief medical dwarf" }, { "broker" },
			{ "bookkeeper" }, { "outpost liaison" }, { "diplomat" },
			{ "duke", "duchess", "duke consort", "duchess consort" },
			{ "count", "countess", "count consort", "countess consort" },
			{ "baron", "baroness", "baron consort", "baroness consort" }, { "champion" }, { "hammerer" },
			{ "dungeon master" }, { "administrator" } };
	private final static String[][] ELF_POSITIONS = {
			// these positions are defined in raw/objects/entity_default.txt
			{ "druid" }, { "acolyte" }, { "princess" }, { "queen" }, { "diplomat" }, { "ranger captain" } };
	private final static String[][] HUMAN_POSITIONS = { { "law-giver" }
			// other human positions are generated by the game but not exported in 47.01
	};
	private final static String[][] GOBLIN_POSITIONS = { { "master" }
			// other human positions are generated by the game but not exported in 47.01
	};
	private final static String[][] NECROMANCER_POSITIONS = { { "overlord" }
			// other necro positions are generated by the game but not exported in 47.01
	};

	public EntityPosition getPosition(int id) {
		String[][] positionSource = {};
		if (positions.size() == 0) {
			// if no positions were found in the export, fill-in default positions by race:
			switch (getRace().toLowerCase()) {
			case "goblin":
			case "goblins":
				positionSource = GOBLIN_POSITIONS;
				break;
			case "human":
			case "humans":
				positionSource = HUMAN_POSITIONS;
				break;
			case "dwarf":
			case "dwarves":
				positionSource = DWARF_POSITIONS;
				break;
			case "elf":
			case "elves":
				positionSource = ELF_POSITIONS;
				break;
			case "necromancer":
			case "necromancers":
				positionSource = NECROMANCER_POSITIONS;
				break;
			default:
				break;
			}
			int posId = 0;
			for (String[] dp : positionSource) {
				EntityPosition pos = new EntityPosition();
				positions.put(posId, pos);
				posId++;
				if (dp.length < 1)
					continue;
				pos.setName(dp[0]);
				pos.setNameMale(dp[0]);
				if (dp.length < 2)
					continue;
				pos.setNameFemale(dp[1]);
				if (dp.length < 4)
					continue;
				pos.setSpouseMale(dp[2]);
				pos.setSpouseFemale(dp[3]);
			}
		}

		if (!positions.containsKey(id)) {
			UNKNOWN_POSITION.setName("UNKNOWN POSITION " + String.valueOf(id));
			return UNKNOWN_POSITION;
		}
		return positions.get(id);
	}

	public Map<Integer, EntityPosition> getPositions() {
		return positions;
	}

	public EntityPositionAssignment getAssignment(int id) {
		return assignments.get(id);
	}

	public Map<Integer, EntityPositionAssignment> getAssignments() {
		return assignments;
	}

	public List<Integer> getHfIds() {
		return hfIds;
	}

	public List<Integer> getWorshipIds() {
		return worshipIds;
	}

	public List<Coords> getClaims() {
		return claims;
	}

	public Occasion getOccasion(int id) {
		return occasions.getOrDefault(id, UNKNOWN_OCCASION);
	}

	public Collection<Occasion> getOccasions() {
		return occasions.values();
	}

	public boolean isFallen() {
		return fallen;
	}

	public void setFallen(boolean fallen) {
		this.fallen = fallen;
	}

	public static String getColor(String race) {
		String raceColor = Application.getProperty("race.color." + race);
		if (raceColor != null)
			return "#" + raceColor;

		switch (race.toLowerCase()) {
		case "kobold":
		case "kobolds":
			return "#333";
		case "goblin":
		case "goblins":
			return "#CC0000";
		case "elf":
		case "elves":
			return "#99FF00";
		case "dwarf":
		case "dwarves":
			return "#FFCC33";
		case "human":
		case "humans":
			return "#0000CC";
		case "necromancer":
		case "necromancers":
			return "#A0A";
		default:
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(race.getBytes());
				long l = 0;
				for (byte b : md.digest())
					l += b;
				Random rand = new Random(l);
				int r = (int) (rand.nextFloat() * 255f) + 256;
				int g = (int) (rand.nextFloat() * 255f) + 256;
				int b = (int) (rand.nextFloat() * 255f) + 256;
				return "#" + Integer.toHexString(r).substring(1) + Integer.toHexString(g).substring(1)
						+ Integer.toHexString(b).substring(1);
			} catch (NoSuchAlgorithmException e) {
				return "#0FF";
			}
		}
	}

	public static void setRaceColor(String race, String color) {
		Application.setProperty("race.color." + race, color);
	}

	public void process() {
		// entities that only own towers are shown as necromancers
		if (getSites().stream().filter(s -> "tower".equals(s.getType())).collect(Collectors.counting()) > 0)
			setRace("necromancers");

		// mark sites pillaged to have no remaining population as ruin
		if (World.isPopulationavailable())
			getSites().stream().filter(s -> s.getPopulations().isEmpty()).forEach(s -> s.setRuin(true));

		// mark civilizations that own no sites or only ruins as fallen
		if (type.equals("civilization")) {
			long siteCount = getSites().stream()
					.filter(s -> !s.isRuin() && s.getOwner() != null && this.equals(s.getOwner().getRoot()))
					.collect(Collectors.counting());
			if (siteCount == 0)
				setFallen(true);
		}
	}

	public String getColor() {
		if (id == -1)
			return "#ddf";

		return Entity.getColor(race);
	}

	@Override
	public String toString() {
		return "[" + id + "] " + getName();
	}

	public String getURL() {
		return Application.getSubUri() + "/entity/" + id;
	}

	public static String getGlyph(String type) {
		switch (type) {
		case "sitegovernment":
			return "fas fa-balance-scale";
		case "outcast":
			return "glyphicon glyphicon-tent";
		case "nomadicgroup":
			return "glyphicon glyphicon-tree-deciduous";
		case "religion":
			return "fas fa-university";
		case "performancetroupe":
			return "glyphicon glyphicon-cd";
		case "migratinggroup":
			return "glyphicon glyphicon-transfer";
		case "guild":
			return "glyphicon glyphicon-wrench";
		case "militaryunit":
			return "glyphicon glyphicon-knight";
		case "merchantcompany":
			return "fas fa-coins";

		case "civilization":
		default:
			return "glyphicon glyphicon-star";
		}
	}

	public String getGlyph() {
		if (isFallen())
			return "glyphicon glyphicon-star-empty";
		return getGlyph(type);
	}

	private String getIcon() {
		return "<span class=\"" + getGlyph() + "\" style=\"color: " + getColor() + "\" aria-hidden=\"true\"></span> ";
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN ENTITY</i>";

		return "<a href=\"" + getURL() + "\" class=\"entity\">" + getIcon() + getName() + "</a>";
	}

	public List<Entity> getWarEnemies() {
		return World.getHistoricalEventCollections().stream().filter(e -> e instanceof WarCollection)
				.map(e -> (WarCollection) e)
				.filter(e -> e.getAggressorEntId() == getId() || e.getDefenderEntId() == getId()).map(e -> {
					if (e.getAggressorEntId() == getId())
						return e.getDefenderEntId();
					else
						return e.getAggressorEntId();
				}).map(World::getEntity).collect(Collectors.toList());

	}

	public List<WarCollection> getWars() {
		return World.getHistoricalEventCollections().stream().filter(e -> e instanceof WarCollection)
				.map(e -> (WarCollection) e)
				.filter(e -> e.getAggressorEntId() == getId() || e.getDefenderEntId() == getId())
				.collect(Collectors.toList());
	}

	public List<Entity> getGroups() {
		return World.getEntities().stream()
				.filter(e -> !e.equals(this) && (this.equals(e.getParent()) || this.equals(e.getRoot())))
				.collect(Collectors.toList());
	}

	public List<OccasionCollection> getOccasionCollections() {
		return World.getHistoricalEventCollections().stream()
				.collect(Filters.filterCollection(OccasionCollection.class, c -> c.getCivId() == id))
				.collect(Collectors.toList());
	}

	public Map<EntityPositionLink, Integer> getHfPositions() {
		return hfPositions;
	}

	public EntityHonor getHonor(int id) {
		return honors.stream().filter(h -> id == h.getId()).findFirst().get();
	}

}
