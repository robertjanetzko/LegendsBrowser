package legends.model.events.basic;

import legends.xml.annotation.Xml;

public abstract class HfEvent extends Event implements HfRelatedEvent {
	@Xml("hist_figure_id")
	protected int hfId = -1;

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.hfId == hfId;
	}

}
