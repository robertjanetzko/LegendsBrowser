package legends.model;

import legends.xml.annotation.Xml;

public class ScheduleFeature {
	@Xml("type")
	private String type = "";
	@Xml("reference")
	private int reference = -1;

	public String getType() {
		return type.replace("_", " ");
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public String getText() {
		switch (getType()) {
		case "poetry recital":
			return "a recital of " + World.getPoeticForm(reference).getLink();
		case "musical performance":
			return "a performance of " + World.getMusicalForm(reference).getLink();
		case "dance performance":
			return "a performance of " + World.getDanceForm(reference).getLink();
		case "storytelling":
			if (reference != -1)
				return "a telling of the story of " + World.getHistoricalEvent(reference).getShortDescription();
			else
				return "a story recital";
		case "images":
			if (reference == -1)
				return "images";
			else
				return "images of " + World.getHistoricalFigure(reference).getLink();
		default:
			return getType();
		}
	}

}
