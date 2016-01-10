package legends.model;

public class MusicalForm extends ArtForm {
	public String getUrl() {
		return "/musicalform/" + id;
	}

	public String getLink() {
		if(id==-1)
			return "<i>UNKNOWN MUSICAL FORM</i>";
		return "<a href=\"" + getUrl() + "\" class=\"art-form musicalform\">" + getName() + "</a>";
	}

}
