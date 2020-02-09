package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class IntriguePlot extends AbstractObject {
	@Xml("local_id")
	private int localId = -1;
	@Xml("type")
	private String type;
	@Xml("on_hold")
	private boolean onHold;

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOnHold() {
		return onHold;
	}

	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}

}
