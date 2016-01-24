package legends.model;

import legends.helper.EventHelper;

public class Leader {
	String position;
	HistoricalFigure hf;
	Entity entity;
	int from = -1;
	int till = -1;

	public String getPosition() {
		return EventHelper.fixPositionGender(position, hf, entity);
	}

	public void setPosition(String position) {
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
