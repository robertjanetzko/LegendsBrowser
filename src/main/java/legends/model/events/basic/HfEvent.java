package legends.model.events.basic;

public abstract class HfEvent extends Event implements HfRelatedEvent {
	protected int hfId = -1;

	public int getHfId() {
		return hfId;
	}

	public void setHfId(int hfId) {
		this.hfId = hfId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "hfid":
		case "hist_fig_id":
		case "hist_figure_id":
			setHfId(Integer.parseInt(value));
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}
	
	@Override
	public boolean isRelatedToHf(int hfId) {
		return this.hfId == hfId;
	}

}
