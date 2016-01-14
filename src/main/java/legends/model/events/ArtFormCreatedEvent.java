package legends.model.events;

import legends.model.World;
import legends.model.events.basic.InspiredEvent;
import legends.model.events.basic.SiteRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("poetic form created,musical form created,dance form created")
public class ArtFormCreatedEvent extends InspiredEvent implements SiteRelatedEvent {
	@Xml("form_id")
	private int formId = -1;
	@Xml("site_id")
	private int siteId = -1;

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.siteId == siteId;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String site = World.getSite(siteId).getLink();
		String form = "unknown art form";
		switch (type) {
		case "poetic form created":
			form = World.getPoeticForm(formId).getLink();
			break;
		case "musical form created":
			form = World.getMusicalForm(formId).getLink();
			break;
		case "dance form created":
			form = World.getDanceForm(formId).getLink();
			break;
		}

		return form + " was created by " + hf + " in " + site + getReasonString() + getCircumstanceString();
	}

}
