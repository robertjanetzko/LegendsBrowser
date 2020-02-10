package legends.model.events;

import java.util.HashMap;
import java.util.Map;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;
import legends.helper.EventHelper;

@XmlSubtype("hf new pet")
public class HfNewPetEvent extends HfEvent implements LocalEvent {
	@XmlComponent
	private EventLocation location = new EventLocation("the depths of the world");

	private String pet;
	private static Map<Integer, Integer> hfPetCount = new HashMap<Integer, Integer>();
	
	@Override
	@Xml("group,group_hfid")
	public void setHfId(int hfId) {
		super.setHfId(hfId);
		
		// keep a count of pet events for each HF
		int petId = HfNewPetEvent.hfPetCount.getOrDefault(hfId, 0);
		pet = (World.getHistoricalFigure(hfId).getJourneyPets().size() > petId)
			? World.getHistoricalFigure(hfId).getJourneyPets().get(petId)
			: "UNKNOWN_PET "+petId;
		HfNewPetEvent.hfPetCount.put(hfId, petId+1);
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(getHfId()).getLink();
		String loc = location.getLink("of");
		return hf + " tamed " + EventHelper.prependIndefiniteArticle(pet) + loc;
	}
}
