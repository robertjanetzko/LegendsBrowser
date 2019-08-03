package legends.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import legends.model.Artifact;
import legends.model.Entity;
import legends.model.EntityPosition;
import legends.model.HistoricalFigure;
import legends.model.Identity;
import legends.model.Region;
import legends.model.Site;
import legends.model.Structure;
import legends.model.WorldConstruction;
import legends.model.basic.AbstractObject;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.IdentityRelatedEvent;
import legends.model.events.basic.RegionRelatedEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.model.events.basic.WorldConstructionRelatedEvent;

public class EventHelper {
	public static String name(String name) {
		if (name != null) {
			name = name.replace("`", "").replace("'", "");
			name = capitalize(Stream.of(name.split(" ")).map(EventHelper::capitalize).collect(Collectors.joining(" ")));
			return capitalize(
					Stream.of(name.split("\"")).map(EventHelper::capitalize).collect(Collectors.joining("\"")));
		}
		return "UNKNOWN";
	}
	
	public static String name(String name, String nameString) {
		if (name != null) {
			return name(name);
		}
		else if (nameString != null) {
			name = nameString.replace("®", "").replace("¯", "");
			return "unnamed " + name;
		}
		return "UNKNOWN";
	}

	public static String capitalize(String word) {
		if (word.length() == 0)
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

	public static String getSeason(int seconds) {
		String r = "";
		int month = seconds % 100800;
		if (month <= 33600)
			r += "early ";
		else if (month <= 67200)
			r += "mid";
		else if (month <= 100800)
			r += "late ";

		int season = seconds % 403200;
		if (season < 100800)
			r += "spring";
		else if (season < 201600)
			r += "summer";
		else if (season < 302400)
			r += "autumn";
		else if (season < 403200)
			r += "winter";

		return r;
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
		name = name.replace("\u0082", "e");
		name = name.replace("\u0089", "e");

		name = name.replace("\u2039", "i");
		name = name.replace("\u00A1", "i");
		name = name.replace("\u008D", "i");
		name = name.replace("\u0152", "i");
		name = name.replace("\u0152", "i");
		name = name.replace("\u008C", "i");

		name = name.replace("\u00A4", "n");
		name = name.replace("\u00A5", "n");

		name = name.replace("\u201D", "o");
		name = name.replace("\u00A2", "o");
		name = name.replace("\u2022", "o");
		name = name.replace("\u201C", "o");
		name = name.replace("\u2122", "o");
		name = name.replace("\u0093", "o");
		name = name.replace("\u0094", "o");
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

	public static boolean related(AbstractObject obj, Event e) {
		if (obj.getId() == -1)
			return false;
		if (obj instanceof HistoricalFigure && e instanceof HfRelatedEvent)
			return ((HfRelatedEvent) e).isRelatedToHf(obj.getId());
		else if (obj instanceof Entity && e instanceof EntityRelatedEvent)
			return ((EntityRelatedEvent) e).isRelatedToEntity(obj.getId());
		else if (obj instanceof Site && e instanceof SiteRelatedEvent)
			return ((SiteRelatedEvent) e).isRelatedToSite(obj.getId());
		else if (obj instanceof Structure && e instanceof StructureRelatedEvent)
			return ((StructureRelatedEvent) e).isRelatedToStructure(obj.getId(), ((Structure) obj).getSiteId());
		else if (obj instanceof Region && e instanceof RegionRelatedEvent)
			return ((RegionRelatedEvent) e).isRelatedToRegion(obj.getId());
		else if (obj instanceof Artifact && e instanceof ArtifactRelatedEvent)
			return ((ArtifactRelatedEvent) e).isRelatedToArtifact(obj.getId());
		else if (obj instanceof WorldConstruction && e instanceof WorldConstructionRelatedEvent)
			return ((WorldConstructionRelatedEvent) e).isRelatedToWorldConstruction(obj.getId());
		else if (obj instanceof Identity && e instanceof IdentityRelatedEvent)
			return ((IdentityRelatedEvent) e).isRelatedToIdentity(obj.getId());
		else
			return false;
	}

	public static Collector<CharSequence, ?, String> listCollector() {
		return Collectors.joining("</li><li>", "<ul><li>", "</li></ul>");
	}

	public static ListCollector stringList() {
		return new ListCollector();
	}

	public static String list(Collection<String> list) {
		return list.stream().collect(stringList());
	}

	public static HfListCollector hfList() {
		return new HfListCollector();
	}

	private static Comparator<Event> eventComparator = new Comparator<Event>() {

		@Override
		public int compare(Event e1, Event e2) {
			return e1.getId() < e2.getId() ? -1 : 1;
		}

	};

	public static Comparator<Event> getComparator() {
		return eventComparator;
	}

	public static String fixPositionGender(String position, HistoricalFigure historicalFigure, Entity entity) {
		for (EntityPosition p : entity.getPositions().values()) {
			if (p.getName().equals(position)) {
				if (historicalFigure.isFemale() && p.getNameFemale() != null)
					return p.getNameFemale();
				if (historicalFigure.isMale() && p.getNameMale() != null)
					return p.getNameMale();
				else
					return p.getName();
			}
		}
		return position;
	}

	public static String formatSize(long size) {
		if (size > 1000000000)
			return DecimalFormat.getNumberInstance().format(size / 1000000000) + " GB";
		if (size > 1000000)
			return DecimalFormat.getNumberInstance().format(size / 1000000) + " MB";
		if (size > 1000)
			return DecimalFormat.getNumberInstance().format(size / 1000) + " KB";
		return DecimalFormat.getNumberInstance().format(size) + " B";
	}

	public static String formatDate(FileTime time) {
		return SimpleDateFormat.getDateTimeInstance().format(new Date(time.toMillis()));
	}

	public static List<String> getTypes(Collection<Event> events) {
		return events.stream().map(Event::getType).distinct().sorted().collect(Collectors.toList());
	}
	
	public static String escapePath(Path path) {
		try {
			return URLEncoder.encode(path.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return path.toString();
		}
	}

}
