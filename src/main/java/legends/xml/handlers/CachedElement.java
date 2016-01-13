package legends.xml.handlers;

public class CachedElement {
	private String element;
	private String value;

	public CachedElement(String element, String value) {
		this.element = element;
		this.value = value;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
