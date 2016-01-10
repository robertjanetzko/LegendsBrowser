package legends.model;

public class DanceForm extends ArtForm {
	public String getUrl() {
		return "/danceform/" + id;
	}

	public String getLink() {
		if(id==-1)
			return "<i>UNKNOWN DANCE FORM</i>";
		return "<a href=\"" + getUrl() + "\" class=\"art-form danceform\">" + getName() + "</a>";
	}
}
