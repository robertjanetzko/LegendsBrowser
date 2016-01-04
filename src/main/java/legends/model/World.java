package legends.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import legends.HistoryReader;
import legends.WorldGenReader;
import legends.WorldState;
import legends.model.collections.basic.EventCollection;
import legends.model.events.ArtFormCreatedEvent;
import legends.model.events.ChangeHfBodyStateEvent;
import legends.model.events.ChangeHfStateEvent;
import legends.model.events.EntityLawEvent;
import legends.model.events.HfConfrontedEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfDoesInteractionEvent;
import legends.model.events.HfRelationshipDeniedEvent;
import legends.model.events.HfSimpleBattleEvent;
import legends.model.events.SiteDisputeEvent;
import legends.model.events.basic.Event;
import legends.xml.WorldContentHandler;
import legends.xml.handlers.XMLContentHandler;

public class World {
	private static WorldState state = WorldState.FILE_SELECT;
	private static String loadingState = "";

	private static String name;
	private static int endYear = 250;

	private static Map<Integer, Region> regions;
	private static List<UndergroundRegion> undergroundRegions;
	private static Map<Integer, Site> sites;
	private static Map<Integer, Artifact> artifacts;
	private static Map<Integer, HistoricalFigure> historicalFigures;
	private static Map<String, HistoricalFigure> historicalFigureNames;
	private static List<EntityPopulation> entityPopulations;
	private static Map<Integer, Entity> entities;
	private static List<Event> historicalEvents;
	private static Map<Integer, Event> historicalEventsMap;
	private static List<EventCollection> historicalEventCollections;
	private static Map<Integer, EventCollection> historicalEventCollectionsMap;
	private static List<HistoricalEra> historicalEras;

	private static File mapFile;
	private static int mapWidth;
	private static int mapHeight;
	private static int mapTileWidth = 129;
	private static int mapTileHeight = 129;

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
		World.regions = regions.stream().collect(Collectors.toMap(Region::getId, Function.identity()));
	}

	public static List<UndergroundRegion> getUndergroundRegions() {
		return undergroundRegions;
	}

	public static void setUndergroundRegions(List<UndergroundRegion> undergroundRegions) {
		World.undergroundRegions = undergroundRegions;
	}

	public static Site getSite(int id) {
		return sites.get(id);
	}

	public static Collection<Site> getSites() {
		return sites.values();
	}

	public static void setSites(List<Site> sites) {
		World.sites = sites.stream().collect(Collectors.toMap(Site::getId, Function.identity()));
	}

	public static Artifact getArtifact(int id) {
		return artifacts.get(id);
	}

	public static Collection<Artifact> getArtifacts() {
		return artifacts.values();
	}

	public static void setArtifacts(List<Artifact> artifacts) {
		World.artifacts = artifacts.stream().collect(Collectors.toMap(Artifact::getId, Function.identity()));
	}

	public static HistoricalFigure getHistoricalFigure(int id) {
		return historicalFigures.get(id);
	}

	public static HistoricalFigure getHistoricalFigure(String name) {
		return historicalFigureNames.get(name.toLowerCase());
	}

	public static Collection<HistoricalFigure> getHistoricalFigures() {
		return historicalFigures.values();
	}

	public static void setHistoricalFigures(List<HistoricalFigure> historicalFigures) {
		World.historicalFigures = historicalFigures.stream()
				.collect(Collectors.toMap(HistoricalFigure::getId, Function.identity()));
		
		historicalFigureNames = new HashMap<>();
		for(HistoricalFigure hf : historicalFigures) {
			historicalFigureNames.put(hf.getName().toLowerCase(), hf);
		}
	}

	public static List<EntityPopulation> getEntityPopulations() {
		return entityPopulations;
	}

	public static void setEntityPopulations(List<EntityPopulation> entityPopulations) {
		World.entityPopulations = entityPopulations;
	}

	public static Entity getEntity(int id) {
		return entities.get(id);
	}

	public static Collection<Entity> getEntities() {
		return entities.values();
	}

	public static List<Entity> getMainCivilizations() {
		return World.getEntities().stream().filter(e -> e.getParent() == null && e.getSites().size() > 0)
				.collect(Collectors.toList());
	}

	public static void setEntities(List<Entity> entities) {
		World.entities = entities.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));
	}

	public static List<Event> getHistoricalEvents() {
		return historicalEvents;
	}

	public static Event getHistoricalEvent(int id) {
		return historicalEventsMap.get(id);
	}

	public static void setHistoricalEvents(List<Event> historicalEvents) {
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
		World.historicalEventCollections = historicalEventCollections;
		World.historicalEventCollectionsMap = historicalEventCollections.stream()
				.collect(Collectors.toMap(EventCollection::getId, Function.identity()));
	}

	public static List<HistoricalEra> getHistoricalEras() {
		return historicalEras;
	}

	public static void setHistoricalEras(List<HistoricalEra> historicalEras) {
		World.historicalEras = historicalEras;
	}

	public static void process() {
		historicalEventCollections.forEach(EventCollection::process);
		historicalEvents.forEach(Event::process);
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

	public static void setImage(BufferedImage image) throws IOException {
		mapFile = File.createTempFile("map", ".png");

		int size = Math.min(image.getWidth(), image.getHeight());
		mapWidth = size;
		mapHeight = size;

		BufferedImage output = new BufferedImage(size, size, image.getType());
		Graphics2D g = output.createGraphics();
		g.drawImage(image.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);

		ImageIO.write(output, "png", mapFile);
	}

	public static String checkFile(File file) {
		String result = "";
		if (file.getAbsolutePath().endsWith("-legends.xml")) {
			String path = file.getAbsolutePath();

			File worldgen = new File(path.substring(0, path.indexOf("-")) + "-world_gen_param.txt");
			if (!worldgen.exists()) {
				if (!result.equals(""))
					result += ", ";
				result += "world gen param missing";
			}

			File map = new File(path.replace("-legends.xml", "-world_map.bmp"));
			if (!map.exists()) {
				if (!result.equals(""))
					result += ", ";
				result += "map image missing";
			}

			File history = new File(path.replace("-legends.xml", "-world_history.txt"));
			if (!history.exists()) {
				if (!result.equals(""))
					result += ", ";
				result += "world history missing";
			}
		}
		return result;
	}

	public static void load(Path currentPath) {
		new Thread() {
			@Override
			public void run() {
				try {
					World.setState(WorldState.LOADING);
					World.setLoadingState("loading " + currentPath);

					XMLReader xmlReader = XMLReaderFactory.createXMLReader();
					XMLContentHandler contentHandler = new XMLContentHandler("", xmlReader);
					WorldContentHandler worldContentHandler = new WorldContentHandler("df_world", xmlReader);
					contentHandler.registerContentHandler(worldContentHandler);
					xmlReader.setContentHandler(contentHandler);

					InputSource inputSource = new InputSource(new FileReader(currentPath.toFile()));
					xmlReader.parse(inputSource);

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

					String p = currentPath.getFileName().toString();

					World.setLoadingState("loading world gen params");
					WorldGenReader
							.read(currentPath.resolveSibling(p.substring(0, p.indexOf("-")) + "-world_gen_param.txt"));

					World.setLoadingState("loading world history");
					HistoryReader.read(currentPath.resolveSibling(p.replace("-legends.xml", "-world_history.txt")));

					World.setLoadingState("loading map image");
					World.setImage(ImageIO
							.read(currentPath.resolveSibling(p.replace("-legends.xml", "-world_map.bmp")).toFile()));

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

}
