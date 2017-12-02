package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("knowledge discovered")
public class KnowledgeDiscoveredEvent extends HfEvent {
	@Xml(value = "knowledge", track = true)
	private String knowledge;
	@Xml("first")
	private boolean first;

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		if (first)
			return hf + " was the very first to discover " + knowledge;
		else
			return hf + " independently discovered " + knowledge;
	}

}
