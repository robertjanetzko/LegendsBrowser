package legends.xml.classes;

import java.util.ArrayList;
import java.util.List;

import legends.xml.annotation.XmlComponent;

public class SimpleClassWithMultiplePrefixedComponent {
	@XmlComponent(prefix = "1_", elementClass = SimpleComponent.class, multiple = true, consumeOn = "value2")
	private List<SimpleComponent> components1 = new ArrayList<>();

	@XmlComponent(prefix = "2_", elementClass = SimpleComponent.class, multiple = true, consumeOn = "value2")
	private List<SimpleComponent> components2 = new ArrayList<>();

	public List<SimpleComponent> getComponents1() {
		return components1;
	}

	public List<SimpleComponent> getComponents2() {
		return components2;
	}
}
