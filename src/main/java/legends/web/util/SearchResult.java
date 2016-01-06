package legends.web.util;

import org.apache.commons.lang.StringEscapeUtils;

public class SearchResult {
	String link;
	String url;

	public SearchResult(String link, String url) {
		this.link = link;
		this.url = url;
	}

	public String getLink() {
		return StringEscapeUtils.escapeJavaScript(link);
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUrl() {
		return StringEscapeUtils.escapeJavaScript(url);
	}

	public void setUrl(String url) {
		this.url = url;
	}

}