package legends.model;

public class Leader {
	String position;
	HistoricalFigure hf;
	int from = -1;
	int till = -1;

	public String getPosition() {
		return position;
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
