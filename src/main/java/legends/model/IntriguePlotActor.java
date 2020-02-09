package legends.model;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class IntriguePlotActor extends AbstractObject {
	@Xml("actor_id")
	private int localId = -1;
	@Xml("plot_role")
	private String role;
	@Xml("agreement_id")
	private int agreementId = -1;
	@Xml("agreement_has_messenger")
	private boolean agreementHasMessenger;
	

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}
	
	public boolean getAgreementHasMessenger() {
		return agreementHasMessenger;
	}

	public void setAgreementHasMessenger(boolean agreementHasMessenger) {
		this.agreementHasMessenger = agreementHasMessenger;
	}
}