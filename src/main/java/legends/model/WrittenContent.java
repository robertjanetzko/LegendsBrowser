package legends.model;

import java.util.ArrayList;
import java.util.List;

public class WrittenContent {
	private int id = -1;
	private String title;
	private int pageStart = -1;
	private int pageEnd = -1;
	private String type;
	private List<Reference> references = new ArrayList<>();
	private List<String> styles = new ArrayList<>();
	private int authorHfId;
	private int form = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		if (title.equals(""))
			return "untitled " + type;
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public String getType() {
		switch (type) {
		case "12":
			return "musical composition";
		case "13":
			return "choreography";
		default:
			return type;
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAuthorHfId() {
		return authorHfId;
	}

	public void setAuthorHfId(int authorHfId) {
		this.authorHfId = authorHfId;
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public List<String> getStyles() {
		return styles;
	}

	public String getUrl() {
		return "/writtencontent/" + id;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN WRITTEN CONTENT</i>";
		return "<a href=\"" + getUrl() + "\" class=\"writtencontent\">" + getTitle() + "</a>";
	}

}
