package legends.model.events;

import java.util.Optional;

import legends.model.HistoricalFigure;
import legends.model.HistoricalFigureLink;
import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("remove hf hf link")
public class RemoveHfHfLinkEvent extends HfEvent {
	@Xml("hf_target,hfid_target")
	private int hfIdTarget = -1;

	public int getHfIdTarget() {
		return hfIdTarget;
	}

	public void setHfIdTarget(int hfIdTarget) {
		this.hfIdTarget = hfIdTarget;
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
			case "former spouse":
				return hf.getLink() + " and " + target.getLink() + " broke up";
			default:
				return hf.getLink() + " removed link " + link.get().getLinkType() + " " + target.getLink();
			}
		}
		return hf.getLink() + " imprisoned " + target.getLink();
	}

}
