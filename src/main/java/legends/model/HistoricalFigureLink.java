package legends.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoricalFigureLink {
	private String linkType;
	private int historicalFigureId;

	private static Set<String> linkTypes = new HashSet<>();

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		linkTypes.add(linkType);
		this.linkType = linkType;
	}

	public int getHistoricalFigureId() {
		return historicalFigureId;
	}

	public void setHistoricalFigureId(int historicalFigureId) {
		this.historicalFigureId = historicalFigureId;
	}

	@Override
	public String toString() {
		return linkType + " " + historicalFigureId;
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("former apprentice");
		linkTypes.remove("mother");
		linkTypes.remove("former master"); 
		linkTypes.remove("father");
		linkTypes.remove("prisoner"); 
		linkTypes.remove("deity");
		linkTypes.remove("apprentice"); 
		linkTypes.remove("spouse");
		linkTypes.remove("imprisoner");
		linkTypes.remove("child");
		linkTypes.remove("master");
		
		if (linkTypes.size() > 0)
			System.out.println("unknown hf link types: " + linkTypes);
	}

	private static List<String> family = Arrays.asList("father", "mother", "child");

	public static boolean isNotFamily(HistoricalFigureLink link) {
		return !family.contains(link.getLinkType());
	}

}
