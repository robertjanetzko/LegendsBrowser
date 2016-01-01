package legends.xml;

import org.xml.sax.XMLReader;

import legends.model.Artifact;
import legends.model.Entity;
import legends.model.EntityPopulation;
import legends.model.HistoricalEra;
import legends.model.HistoricalFigure;
import legends.model.Region;
import legends.model.Site;
import legends.model.UndergroundRegion;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.Event;
import legends.xml.handlers.ListContentHandler;
import legends.xml.handlers.XMLContentHandler;

public class WorldContentHandler extends XMLContentHandler {
	public WorldContentHandler(String name, XMLReader xmlReader) {
		super(xmlReader);
		setName(name);

		registerContentHandler(new ListContentHandler<Region>("regions", xmlReader,
				new RegionContentHandler("region", xmlReader), World::setRegions));

		registerContentHandler(new ListContentHandler<UndergroundRegion>("underground_regions", xmlReader,
				new UndergroundRegionContentHandler("underground_region", xmlReader), World::setUndergroundRegions));

		registerContentHandler(new ListContentHandler<Site>("sites", xmlReader,
				new SiteContentHandler("site", xmlReader), World::setSites));

		// registerContentHandler(new
		// ListContentHandler<Site>("world_constructions", xmlReader,
		// new SiteContentHandler("world_construction", xmlReader),
		// World::set));

		registerContentHandler(new ListContentHandler<Artifact>("artifacts", xmlReader,
				new ArtifactContentHandler("artifact", xmlReader), World::setArtifacts));

		registerContentHandler(new ListContentHandler<HistoricalFigure>("historical_figures", xmlReader,
				new HistoricalFigureContentHandler("historical_figure", xmlReader), World::setHistoricalFigures));

		registerContentHandler(new ListContentHandler<EntityPopulation>("entity_populations", xmlReader,
				new EntityPopulationContentHandler("entity_population", xmlReader), World::setEntityPopulations));

		registerContentHandler(new ListContentHandler<Entity>("entities", xmlReader,
				new EntityContentHandler("entity", xmlReader), World::setEntities));

		registerContentHandler(new ListContentHandler<Event>("historical_events", xmlReader,
				new HistoricalEventContentHandler("historical_event", xmlReader), World::setHistoricalEvents));

		registerContentHandler(new ListContentHandler<EventCollection>("historical_event_collections",
				xmlReader, new HistoricalEventCollectionContentHandler("historical_event_collection", xmlReader),
				World::setHistoricalEventCollections));

		registerContentHandler(new ListContentHandler<HistoricalEra>("historical_eras", xmlReader,
				new HistoricalEraContentHandler("historical_era", xmlReader), World::setHistoricalEras));
	}

}
