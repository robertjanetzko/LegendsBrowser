package legends.model;

public class PoeticForm extends ArtForm {
	public String getUrl() {
		return "/poeticform/" + id;
	}

	public String getLink() {
		if(id==-1)
			return "<i>UNKNOWN POETIC FORM</i>";
		return "<a href=\"" + getUrl() + "\" class=\"art-form poeticform\">" + getName() + "</a>";
	}

}
