package legends.xml.classes;

import java.util.ArrayList;
import java.util.List;

import legends.xml.annotation.XmlComponent;

public class SimpleClassWithMultipleComponent {
	@XmlComponent(elementClass = SimpleComponent.class, multiple = true, consumeOn = "value2")
	private List<SimpleComponent> components = new ArrayList<>();
	
	public List<SimpleComponent> getComponents() {
		return components;
	}
}
