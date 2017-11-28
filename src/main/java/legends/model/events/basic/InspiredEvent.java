package legends.model.events.basic;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.xml.annotation.Xml;

public abstract class InspiredEvent extends HfEvent {
	@Xml("circumstance")
	private String circumstance;
	@Xml("circumstance_id")
	private int circumstanceId = -1;
	@Xml("reason")
	private String reason;
	@Xml("reason_id")
	private int reasonId = -1;

	private static Set<String> circumstances = new HashSet<>();
	private static Set<String> reasons = new HashSet<>();

	public String getCircumstance() {
		return circumstance;
	}

	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

	public int getCircumstanceId() {
		return circumstanceId;
	}

	public void setCircumstanceId(int circumstanceId) {
		this.circumstanceId = circumstanceId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || circumstanceId == hfId || reasonId == hfId;
	}

	protected String getCircumstanceString() {
		String circ = "";
		if (circumstance != null)
			switch (circumstance) {
			case "dream":
				circ = " after a dream";
				break;
			case "dream about hf":
				circ = " after dreaming of " + World.getHistoricalFigure(circumstanceId).getLink();
				break;
			case "nightmare":
				circ = " after a nightmare";
				break;
			case "pray to hf":
				circ = " after praying to " + World.getHistoricalFigure(circumstanceId).getLink();
				break;
			default:
				circ = " after unknown circumstances";
				break;
			}
		return circ;
	}

	protected String getReasonString() {
		String reas = "";
		if (reason != null)
			switch (reason) {
			case "glorify hf":
				reas = " in order to glorify " + World.getHistoricalFigure(circumstanceId).getLink();
				break;
			default:
				reas = " for unknown reasons";
				break;
			}
		return reas;
	}

	public static void printUnknownCircumstances() {
		circumstances.remove("pray to hf");
		circumstances.remove("dream");
		circumstances.remove("nightmare");
		circumstances.remove("dream about hf");

		if (circumstances.size() > 0)
			LOG.debug("unknown art circumstances: " + circumstances);
	}

	public static void printUnknownReasons() {
		reasons.remove("glorify hf");

		if (reasons.size() > 0)
			LOG.debug("unknown art reasons: " + reasons);
	}

}
