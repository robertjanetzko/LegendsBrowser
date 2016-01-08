package legends.model.events;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import legends.model.HistoricalFigure;
import legends.model.HistoricalFigureLink;
import legends.model.World;
import legends.model.events.basic.HfEvent;

public class AddHfHfLinkEvent extends HfEvent {
	private int hfIdTarget = -1;
	private String linkType;

	public int getHfIdTarget() {
		return hfIdTarget;
	}

	public void setHfIdTarget(int hfIdTarget) {
		this.hfIdTarget = hfIdTarget;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
	private static Set<String> linkTypes = new HashSet<>();

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "hf":
			setHfId(Integer.parseInt(value));
			break;
		case "hf_target":
		case "hfid_target":
			setHfIdTarget(Integer.parseInt(value));
			break;
		case "link_type":
			linkTypes.add(value);
			setLinkType(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || hfIdTarget == hfId;
	}

	@Override
	public String getShortDescription() {
		HistoricalFigure hf = World.getHistoricalFigure(getHfId());
		HistoricalFigure target = World.getHistoricalFigure(getHfIdTarget());
		Optional<HistoricalFigureLink> link = hf.getHistoricalFigureLinks().stream()
				.filter(HistoricalFigureLink::isNotFamily).filter(l -> l.getHistoricalFigureId() == hfIdTarget)
				.findFirst();
		if (link.isPresent()) {
			switch (link.get().getLinkType()) {
			case "deity":
				return hf.getLink() + " began worshipping " + target.getLink();
			case "spouse":
				return hf.getLink() + " married " + target.getLink();
			case "apprentice":
			case "former apprentice":
			case "former_apprentice":
				return hf.getLink() + " became the master of " + target.getLink();
			case "master":
			case "former master":
			case "former_master":
				return hf.getLink() + " began an apprenticeship under " + target.getLink();
			case "prisoner":
				return hf.getLink() + " imprisoned " + target.getLink();
			default:
				return hf.getLink() + " link " + link.get().getLinkType() + " " + target.getLink();
			}
		}
		return hf.getLink() + " imprisoned " + target.getLink();
	}
	
	public static void printUnknownLinkTypes() {
		linkTypes.remove("deity");
		linkTypes.remove("spouse");
		linkTypes.remove("apprentice");
		linkTypes.remove("former apprentice");
		linkTypes.remove("former_apprentice");
		linkTypes.remove("master");
		linkTypes.remove("former master");
		linkTypes.remove("former_master");
		linkTypes.remove("prisoner");
		
		if(linkTypes.size()>0)
			System.out.println("Unknown hf hf link types: "+linkTypes);
	}

}
