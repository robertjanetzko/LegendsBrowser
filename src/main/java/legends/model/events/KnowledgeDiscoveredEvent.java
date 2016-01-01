package legends.model.events;

import legends.model.World;
import legends.model.events.basic.HfEvent;

public class KnowledgeDiscoveredEvent extends HfEvent {
	private String knowledge;
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
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "knowledge":
			setKnowledge(value);
			break;
		case "first":
			setFirst(true);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		if(first)
			return hf + " was the very first to discover " + knowledge;
		else
		return hf + " independently discovered " + knowledge;
	}

}
