package legends.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import legends.HistoryReader;
import legends.LegendsReader;
import legends.SiteReader;
import legends.WorldGenReader;
import legends.WorldState;
import legends.helper.WorldConfig;
import legends.model.collections.basic.EventCollection;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.AddHfHfLinkEvent;
import legends.model.events.AddHfSiteLinkEvent;
import legends.model.events.ArtFormCreatedEvent;
import legends.model.events.ChangeHfBodyStateEvent;
import legends.model.events.ChangeHfStateEvent;
import legends.model.events.EntityLawEvent;
import legends.model.events.HfConfrontedEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfDoesInteractionEvent;
import legends.model.events.HfRelationshipDeniedEvent;
import legends.model.events.HfSimpleBattleEvent;
import legends.model.events.PeaceEvent;
import legends.model.events.RemoveHfEntityLinkEvent;
import legends.model.events.RemoveHfSiteLinkEvent;
import legends.model.events.SiteDisputeEvent;
import legends.model.events.basic.Event;
import legends.xml.HistoricalEventContentHandler;

public class World {
	private static WorldState state = WorldState.FILE_SELECT;
	private static String loadingState = "";

	private static boolean plusMode = false;

	private static String name;
	private static int endYear = 250;

	private static Map<Integer, Region> regions;
	private static Map<Integer, UndergroundRegion> undergroundRegions;
	private static Map<Integer, WorldConstruction> worldConstructions;
	private static Map<Integer, Site> sites;
	private static Map<Integer, Artifact> artifacts;
	private static Map<Integer, HistoricalFigure> historicalFigures;
	private static Map<String, HistoricalFigure> historicalFigureNames;
	private static Map<Integer, EntityPopulation> entityPopulations;
	private static Map<Integer, Entity> entities;
	private static List<Event> historicalEvents;
	private static Map<Integer, Event> historicalEventsMap;
	private static List<EventCollection> historicalEventCollections;
	private static Map<Integer, EventCollection> historicalEventCollectionsMap;
	private static List<HistoricalEra> historicalEras;

	private static List<Population> populations = new ArrayList<>();

	private static Map<Integer, PoeticForm> poeticFormsMap;
	private static Map<Integer, MusicalForm> musicalFormsMap;
	private static Map<Integer, DanceForm> danceFormsMap;

	private static File mapFile;
	private static int mapWidth;
	private static int mapHeight;
	private static int mapTileWidth = 129;
	private static int mapTileHeight = 129;

	private static final Entity UNKNOWN_ENTITY = new Entity();
	private static final HistoricalFigure UNKNOWN_HISTORICAL_FIGURE = new HistoricalFigure();
	private static final Structure UNKNOWN_STRUCTURE = new Structure();
	private static final WorldConstruction UNKNOWN_WORLD_CONSTRUCTION = new WorldConstruction();
	private static final PoeticForm UNKNOWN_POETIC_FORM = new PoeticForm();
	private static final MusicalForm UNKNOWN_MUSICAL_FORM = new MusicalForm();
	private static final DanceForm UNKNOWN_DANCE_FORM = new DanceForm();

	public static WorldState getState() {
		return state;
	}

	public static void setState(WorldState state) {
		World.state = state;
	}

	public static String getLoadingState() {
		return loadingState;
	}

	public static void setLoadingState(String loadingState) {
		World.loadingState = loadingState;
	}

	public static boolean isPlusMode() {
		return plusMode;
	}

	public static void setPlusMode(boolean plusMode) {
		World.plusMode = plusMode;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		World.name = name;
	}

	public static int getEndYear() {
		return endYear;
	}

	public static void setEndYear(int endYear) {
		World.endYear = endYear;
	}

	public static Region getRegion(int id) {
		return regions.get(id);
	}

	public static Collection<Region> getRegions() {
		return regions.values();
	}

	public static void setRegions(List<Region> regions) {
		if (World.regions == null)
			World.regions = regions.stream().collect(Collectors.toMap(Region::getId, Function.identity()));
	}

	public static UndergroundRegion getUndergroundRegion(int id) {
		return undergroundRegions.get(id);
	}

	public static Collection<UndergroundRegion> getUndergroundRegions() {
		return undergroundRegions.values();
	}

	public static void setUndergroundRegions(List<UndergroundRegion> undergroundRegions) {
		if (World.undergroundRegions == null)
			World.undergroundRegions = undergroundRegions.stream()
					.collect(Collectors.toMap(UndergroundRegion::getId, Function.identity()));
	}

	public static WorldConstruction getWorldConstruction(int id) {
		WorldConstruction wc = worldConstructions.get(id);
		if (wc == null)
			return UNKNOWN_WORLD_CONSTRUCTION;
		return wc;
	}

	public static Collection<WorldConstruction> getWorldConstructions() {
		return worldConstructions.values();
	}

	public static Collection<WorldConstruction> getPointWorldConstructions() {
		return worldConstructions.values().stream().filter(wc -> wc.getCoords().size() == 1)
				.collect(Collectors.toList());
	}

	public static Collection<WorldConstruction> getLineWorldConstructions() {
		return worldConstructions.values().stream().filter(wc -> wc.getCoords().size() > 1)
				.collect(Collectors.toList());
	}

	public static void setWorldConstructions(List<WorldConstruction> worldConstructions) {
		if (World.worldConstructions == null)
			World.worldConstructions = worldConstructions.stream()
					.collect(Collectors.toMap(WorldConstruction::getId, Function.identity()));
		else
			for (WorldConstruction wc : worldConstructions)
				World.worldConstructions.put(wc.getId(), wc);
	}

	public static Site getSite(int id) {
		return sites.get(id);
	}

	public static Collection<Site> getSites() {
		return sites.values();
	}

	public static void setSites(List<Site> sites) {
		if (World.sites == null)
			World.sites = sites.stream().collect(Collectors.toMap(Site::getId, Function.identity()));
	}

	public static Structure getStructure(int structureId, int siteId) {
		Site site = getSite(siteId);
		if (site == null || site.getStructures() == null || structureId >= site.getStructures().size())
			return UNKNOWN_STRUCTURE;

		Structure s = getSite(siteId).getStructures().get(structureId);
		if (s == null)
			return UNKNOWN_STRUCTURE;
		return s;
	}

	public static List<Structure> getStructures() {
		return World.getSites().stream().flatMap(s -> s.getStructures().stream()).collect(Collectors.toList());
	}

	public static Artifact getArtifact(int id) {
		return artifacts.get(id);
	}

	public static Collection<Artifact> getArtifacts() {
		return artifacts.values();
	}

	public static void setArtifacts(List<Artifact> artifacts) {
		if (World.artifacts == null)
			World.artifacts = artifacts.stream().collect(Collectors.toMap(Artifact::getId, Function.identity()));
	}

	public static HistoricalFigure getHistoricalFigure(int id) {
		HistoricalFigure hf = historicalFigures.get(id);
		if (hf == null)
			return UNKNOWN_HISTORICAL_FIGURE;
		return hf;
	}

	public static HistoricalFigure getHistoricalFigure(String name) {
		return historicalFigureNames.get(name.toLowerCase());
	}

	public static Collection<HistoricalFigure> getHistoricalFigures() {
		return historicalFigures.values();
	}

	public static void setHistoricalFigures(List<HistoricalFigure> historicalFigures) {
		if (World.historicalFigures != null)
			return;
		World.historicalFigures = historicalFigures.stream()
				.collect(Collectors.toMap(HistoricalFigure::getId, Function.identity()));

		historicalFigureNames = new HashMap<>();
		for (HistoricalFigure hf : historicalFigures) {
			historicalFigureNames.put(hf.getName().toLowerCase(), hf);
		}
	}

	public static EntityPopulation getEntityPopulation(int id) {
		return entityPopulations.get(id);
	}

	public static Collection<EntityPopulation> getEntityPopulations() {
		return entityPopulations.values();
	}

	public static void setEntityPopulations(List<EntityPopulation> entityPopulations) {
		if (World.entityPopulations != null)
			return;
		World.entityPopulations = entityPopulations.stream()
				.collect(Collectors.toMap(EntityPopulation::getId, Function.identity()));
	}

	public static Entity getEntity(int id) {
		Entity entity = entities.get(id);
		if (entity == null)
			entity = UNKNOWN_ENTITY;
		return entity;
	}

	public static Collection<Entity> getEntities() {
		return entities.values();
	}

	public static List<Entity> getMainCivilizations() {
		return World.getEntities().stream()
				.filter(e -> e.getParent() == null && !"UNKNOWN".equals(e.getName())
						&& (e.getSites().size() > 0 || "civilization".equals(e.getType())))
				.collect(Collectors.toList());
	}

	public static void setEntities(List<Entity> entities) {
		if (World.entities != null)
			return;
		World.entities = entities.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
	}

	public static List<Event> getHistoricalEvents() {
		return historicalEvents;
	}

	public static Event getHistoricalEvent(int id) {
		return historicalEventsMap.get(id);
	}

	public static void setHistoricalEvents(List<Event> historicalEvents) {
		if (World.historicalEvents != null)
			return;
		World.historicalEvents = historicalEvents;
		World.historicalEventsMap = historicalEvents.stream()
				.collect(Collectors.toMap(Event::getId, Function.identity()));
	}

	public static List<String> getEventTypes() {
		return World.getHistoricalEvents().stream().map(Event::getType).distinct().sorted()
				.collect(Collectors.toList());
	}

	public static List<EventCollection> getHistoricalEventCollections() {
		return historicalEventCollections;
	}

	public static EventCollection getHistoricalEventCollection(int id) {
		return historicalEventCollectionsMap.get(id);
	}

	public static void setHistoricalEventCollections(List<EventCollection> historicalEventCollections) {
		if (World.historicalEventCollections != null)
			return;
		World.historicalEventCollections = historicalEventCollections;
		World.historicalEventCollectionsMap = historicalEventCollections.stream()
				.collect(Collectors.toMap(EventCollection::getId, Function.identity()));
	}

	public static List<HistoricalEra> getHistoricalEras() {
		return historicalEras;
	}

	public static void setHistoricalEras(List<HistoricalEra> historicalEras) {
		if (World.historicalEras != null)
			return;
		World.historicalEras = historicalEras;
	}

	public static List<Population> getPopulations() {
		return populations;
	}

	public static Collection<PoeticForm> getPoeticForms() {
		return poeticFormsMap.values();
	}

	public static PoeticForm getPoeticForm(int id) {
		PoeticForm f = poeticFormsMap.get(id);
		if (f == null)
			return UNKNOWN_POETIC_FORM;
		return f;
	}

	public static void setPoeticForms(List<PoeticForm> poeticForms) {
		World.poeticFormsMap = poeticForms.stream().collect(Collectors.toMap(PoeticForm::getId, Function.identity()));
	}

	public static Collection<MusicalForm> getMusicalForms() {
		return musicalFormsMap.values();
	}

	public static MusicalForm getMusicalForm(int id) {
		MusicalForm f = musicalFormsMap.get(id);
		if (f == null)
			return UNKNOWN_MUSICAL_FORM;
		return f;
	}

	public static void setMusicalForms(List<MusicalForm> musicalForms) {
		World.musicalFormsMap = musicalForms.stream()
				.collect(Collectors.toMap(MusicalForm::getId, Function.identity()));
	}

	public static Collection<DanceForm> getDanceForms() {
		return danceFormsMap.values();
	}

	public static DanceForm getDanceForm(int id) {
		DanceForm f = danceFormsMap.get(id);
		if (f == null)
			return UNKNOWN_DANCE_FORM;
		return f;
	}

	public static void setDanceForms(List<DanceForm> danceForms) {
		World.danceFormsMap = danceForms.stream().collect(Collectors.toMap(DanceForm::getId, Function.identity()));
	}

	public static void process() {
		historicalEventCollections.forEach(EventCollection::process);
		historicalEvents.forEach(Event::process);
		entities.values().forEach(Entity::process);
	}

	public static File getMapFile() {
		return mapFile;
	}

	public static int getMapWidth() {
		return mapWidth;
	}

	public static int getMapHeight() {
		return mapHeight;
	}

	public static int getMapTileWidth() {
		return mapTileWidth;
	}

	public static int getMapTileHeight() {
		return mapTileHeight;
	}

	public static void setDimension(int w, int h) {
		mapTileWidth = w;
		mapTileHeight = h;
	}

	public static void setImage(Path path) throws IOException {
		if (path == null || !Files.exists(path)) {
			System.out.println("no map image found");
			return;
		}

		BufferedImage image = ImageIO.read(Files.newInputStream(path));

		mapFile = File.createTempFile("map", ".png");

		int size = Math.min(image.getWidth(), image.getHeight());
		mapWidth = size;
		mapHeight = size;

		BufferedImage output = new BufferedImage(size, size, image.getType());
		Graphics2D g = output.createGraphics();
		g.drawImage(image.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);

		ImageIO.write(output, "png", mapFile);
	}

	public static WorldConfig checkPath(Path path) {
		try {
			return new WorldConfig(path);
		} catch (IOException e) {
			return null;
		}
	}

	public static void load(Path currentPath) {
		new Thread() {
			@Override
			public void run() {
				try {
					WorldConfig config = new WorldConfig(currentPath);
					System.out.println(config);

					World.setState(WorldState.LOADING);
					World.setLoadingState("loading legends.xml");
					LegendsReader.read(config.getLegendsPath());

					if (config.plusAvailable()) {
						World.setLoadingState("loading legends_plus.xml");
						World.setPlusMode(true);
						LegendsReader.read(config.getLegendsPlusPath());
					}

					if (worldConstructions == null)
						setWorldConstructions(new ArrayList<>());
					if (poeticFormsMap == null)
						setPoeticForms(new ArrayList<>());
					if (musicalFormsMap == null)
						setMusicalForms(new ArrayList<>());
					if (danceFormsMap == null)
						setDanceForms(new ArrayList<>());

					printUnknownElements();

					World.setLoadingState("loading world gen params: " + config.getWorldGenPath());
					WorldGenReader.read(config.getWorldGenPath());

					World.setLoadingState("loading world history");
					HistoryReader.read(config.getHistoryPath());

					World.setLoadingState("loading sites and props");
					SiteReader.read(config.getSitesAndPropsPath());

					World.setLoadingState("loading map image");
					World.setImage(config.getImagePath());

					World.setLoadingState("processing " + currentPath);
					World.process();

					System.out.println("world ready");
					World.setState(WorldState.READY);
				} catch (Exception e) {
					e.printStackTrace();
					World.setLoadingState(e.getMessage());
				}
			}
		}.start();
	}

	private static void printUnknownElements() {
		HistoricalEventContentHandler.printUnknownTypes();

		EntityLink.printUnknownLinkTypes();
		HistoricalFigureLink.printUnknownLinkTypes();

		ChangeHfStateEvent.printUnknownStates();
		HfDiedEvent.printUnknownCauses();
		HfSimpleBattleEvent.printUnknownSubtypes();
		EntityLawEvent.printUnknownLaws();
		SiteDisputeEvent.printUnknownDisputes();
		HfConfrontedEvent.printUnknownSituations();
		HfConfrontedEvent.printUnknownReasons();
		HfRelationshipDeniedEvent.printUnknownRelationships();
		HfRelationshipDeniedEvent.printUnknownReasons();
		HfDoesInteractionEvent.printUnknownInteractions();
		ChangeHfBodyStateEvent.printUnknownBodyStates();
		ArtFormCreatedEvent.printUnknownCircumstances();
		ArtFormCreatedEvent.printUnknownReasons();
		AddHfHfLinkEvent.printUnknownLinkTypes();
		PeaceEvent.printUnknownTopics();
		AddHfSiteLinkEvent.printUnknownLinkTypes();
		RemoveHfSiteLinkEvent.printUnknownLinkTypes();
		AddHfEntityLinkEvent.printUnknownLinkTypes();
		RemoveHfEntityLinkEvent.printUnknownLinkTypes();
	}
}
