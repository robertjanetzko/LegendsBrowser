package legends.xml.classes;

import legends.xml.annotation.Xml;

public class SimpleComponent {
	@Xml("value1")
	private int value1 = -1;
	@Xml("value2")
	private String value2;

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
