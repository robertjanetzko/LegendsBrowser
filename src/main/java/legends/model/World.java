package legends.model;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.Event;

public class World {
	private static String name;

	private static Map<Integer, Region> regions;
	private static List<UndergroundRegion> undergroundRegions;
	private static Map<Integer, Site> sites;
	private static Map<Integer, Artifact> artifacts;
	private static Map<Integer, HistoricalFigure> historicalFigures;
	private static List<EntityPopulation> entityPopulations;
	private static Map<Integer, Entity> entities;
	private static List<Event> historicalEvents;
	private static Map<Integer, Event> historicalEventsMap;
	private static List<EventCollection> historicalEventCollections;
	private static Map<Integer, EventCollection> historicalEventCollectionsMap;
	private static List<HistoricalEra> historicalEras;

	private static BufferedImage mapImage;
	private static int mapTileWidth;
	private static int mapTileHeight;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		World.name = name;
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

	public static Collection<HistoricalFigure> getHistoricalFigures() {
		return historicalFigures.values();
	}

	public static void setHistoricalFigures(List<HistoricalFigure> historicalFigures) {
		World.historicalFigures = historicalFigures.stream()
				.collect(Collectors.toMap(HistoricalFigure::getId, Function.identity()));
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

	public static BufferedImage getMapImage() {
		return mapImage;
	}

	public static int getMapTileWidth() {
		return mapTileWidth;
	}

	public static int getMapTileHeight() {
		return mapTileHeight;
	}

	public static void setImage(BufferedImage image, int tilesW, int tilesH) {
		mapImage = image;
		mapTileWidth = image.getWidth() / tilesW;
		mapTileHeight = image.getHeight() / tilesH;
	}

}
