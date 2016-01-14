package legends.model.events;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;
@XmlSubtype("hf new pet")
public class HfNewPetEvent extends HfEvent implements LocalEvent {
	@XmlComponent
	private EventLocation location = new EventLocation("the depths of the world");
	@Xml("pets")
	private String pets = "UNKNOWN PET";
	
	
	@Override
	@Xml("group,group_hfid")
	public void setHfId(int hfId) {
		super.setHfId(hfId);
	}

	public String getPets() {
		return pets;
	}

	public void setPets(String pets) {
		this.pets = pets;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("of");
		return hf + " tamed a "+pets + loc;
	}

}
