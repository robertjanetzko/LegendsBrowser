package legends.helper;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import legends.model.Artifact;
import legends.model.Entity;
import legends.model.HistoricalEvent;
import legends.model.HistoricalFigure;
import legends.model.Region;
import legends.model.Site;
import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;
import legends.model.events.basic.RegionRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;

public class EventHelper {
	private static World world;

	public static void setWorld(World world) {
		EventHelper.world = world;
	}

	public String getLocation(Event event) {
		if (event instanceof LocalEvent) {
			EventLocation location = ((LocalEvent) event).getLocation();
			if (location.getSubregionId() != -1)
				return World.getRegion(location.getSubregionId()).getLink();
			else if (location.getSiteId() != -1)
				return World.getSite(location.getSiteId()).getLink();
		}

		return "no region";
	}

	public String getGroup2(HistoricalEvent event) {
		if (event.getGroup2HfIds().size() == 1)
			return world.getHistoricalFigure(event.getGroup2HfIds().get(0)).getLink();
		else
			return event.getGroup2HfIds().stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink)
					.collect(Collectors.joining(" and "));
	}

	public static String name(String name) {
		if (name != null)
			return capitalize(Stream.of(name.split(" ")).map(EventHelper::capitalize).collect(Collectors.joining(" ")));
		return "UNKNOWN";
	}

	public static String capitalize(String word) {
		if(word.length()==0)
			return word;
		switch (word) {
		case "of":
		case "the":
		case "in":
		case "with":
		case "and":
		case "for":
		case "by":
			return word;
		default:
			return word.substring(0, 1).toUpperCase() + word.substring(1);
		}
	}
	
	public static String ascii(String name) {
            name = name.replace("\u017D", "a");
            name = name.replace("\u017E", "a");
            name = name.replace("\u201E", "a");
            name = name.replace("\u0192", "a");
            name = name.replace("\u008F", "a");
            name = name.replace("\u2020", "a");
            name = name.replace("\u00A0", "a");
            name = name.replace("\u2026", "a");
            name = name.replace("\u0083", "a");
            name = name.replace("\u0084", "a");
            name = name.replace("\u0086", "a");
            name = name.replace("\u008E", "a");
            
            name = name.replace("\u02C6", "e");
            name = name.replace("\u2030", "e");
            name = name.replace("\u201A", "e");
            name = name.replace("\u0220", "e");
            name = name.replace("\u0160", "e");
            name = name.replace("\u0090", "e");
            name = name.replace("\u008A", "e");
            name = name.replace("\u0089", "e");
                       
            name = name.replace("\u2039", "i");
            name = name.replace("\u00A1", "i");
            name = name.replace("\u008D", "i");
            name = name.replace("\u0152", "i");
            
            name = name.replace("\u00A4", "n");
            name = name.replace("\u00A5", "n");
            
            name = name.replace("\u201D", "o");
            name = name.replace("\u00A2", "o");
            name = name.replace("\u2022", "o");
            name = name.replace("\u201C", "o");
            name = name.replace("\u2122", "o");
            name = name.replace("\u0093", "o");
            name = name.replace("\u0095", "o");
            
            name = name.replace("\u2014", "u");
            name = name.replace("\u2013", "u");
            name = name.replace("\u00A3", "u");
            name = name.replace("\u0096", "u");
            name = name.replace("\u0097", "u");
            
            name = name.replace("\u02DC", "y");
            name = name.replace("\u0098", "y");
            return name;
 	}

	public static boolean related(Object obj, Event e) {
		if (obj instanceof HistoricalFigure && e instanceof HfRelatedEvent)
			return ((HfRelatedEvent) e).isRelatedToHf(((HistoricalFigure)obj).getId());
		else if (obj instanceof Entity && e instanceof EntityRelatedEvent)
			return ((EntityRelatedEvent) e).isRelatedToEntity(((Entity)obj).getId());
		else if (obj instanceof Site && e instanceof SiteRelatedEvent)
			return ((SiteRelatedEvent) e).isRelatedToSite(((Site)obj).getId());
		else if (obj instanceof Region && e instanceof RegionRelatedEvent)
			return ((RegionRelatedEvent) e).isRelatedToRegion(((Region)obj).getId());
		else if (obj instanceof Artifact && e instanceof ArtifactRelatedEvent)
			return ((ArtifactRelatedEvent) e).isRelatedToArtifact(((Artifact)obj).getId());
		else
			return false;
	}
	
	public static Collector<CharSequence,?,String> listCollector() {
		return Collectors.joining("</li><li>", "<ul><li>","</li></ul>");
	}

}
