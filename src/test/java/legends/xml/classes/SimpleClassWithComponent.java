package legends.xml.classes;

import legends.xml.annotation.XmlComponent;

public class SimpleClassWithComponent {
	@XmlComponent
	private SimpleComponent component = new SimpleComponent();

	public SimpleComponent getComponent() {
		return component;
	}
}
