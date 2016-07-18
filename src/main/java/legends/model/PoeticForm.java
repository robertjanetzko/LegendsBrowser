package legends.model;

import legends.Application;

public class PoeticForm extends ArtForm {
	public String getUrl() {
		return Application.getSubUri() + "/poeticform/" + id;
	}

	public String getLink() {
		if(id==-1)
			return "<i>UNKNOWN POETIC FORM</i>";
		return "<a href=\"" + getUrl() + "\" class=\"art-form poeticform\"><i class=\"fa fa-comment-o\"></i> " + getName() + "</a>";
	}

	@Override
	public String getType() {
		return "poetic form";
	}
}
