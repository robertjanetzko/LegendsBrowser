package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.model.basic.AbstractObject;
import legends.xml.annotation.Xml;

public class WrittenContent extends AbstractObject {
	@Xml("title")
	private String title;
	@Xml("page_start")
	private int pageStart = -1;
	@Xml("page_end")
	private int pageEnd = -1;
	@Xml("type")
	private String type;
	@Xml(value = "reference", elementClass = Reference.class, multiple = true)
	private List<Reference> references = new ArrayList<>();
	@Xml(value = "style", elementClass = String.class, multiple = true)
	private List<String> styles = new ArrayList<>();
	@Xml("author")
	private int authorHfId;
	@Xml("form")
	private int form = -1;

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
		case "14":
			return "two hfs";
		case "18":
			return "occasion";
		case "19":
			return "two civs";
		case "21":
			return "mechanics";
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
