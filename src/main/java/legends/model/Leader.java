package legends.model;

import legends.helper.EventHelper;

public class Leader {
	String position;
	HistoricalFigure hf;
	Entity entity;
	int from = -1;
	int till = -1;

	public String getPosition() {
		// convert position_id=0 to a string that depends on race and sex
		return EventHelper.fixPositionGender(0, hf, entity);
	}

	public void setPosition(String position) {
		// not used any more in .47 ?
		this.position = position;
	}

	public HistoricalFigure getHf() {
		return hf;
	}

	public void setHf(HistoricalFigure hf) {
		this.hf = hf;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTill() {
		return till;
	}

	public void setTill(int till) {
		this.till = till;
	}

}
