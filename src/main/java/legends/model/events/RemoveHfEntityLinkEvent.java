package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class RemoveHfEntityLinkEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
	private int civId = -1;

	private int calcHfId = -1;
	private String calcLinkType = "";
	private String position;

	private static Set<String> linkTypes = new HashSet<>();

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getCalcHfId() {
		return calcHfId;
	}

	public void setCalcHfId(int calcHfId) {
		this.calcHfId = calcHfId;
	}

	public String getCalcLinkType() {
		return calcLinkType;
	}

	public void setCalcLinkType(String calcLinkType) {
		this.calcLinkType = calcLinkType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "histfig":
			setCalcHfId(Integer.parseInt(value));
			break;
		case "civ":
		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "link_type":
			linkTypes.add(value);
			setCalcLinkType(value);
			break;
		case "position":
			setPosition(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return calcHfId == hfId;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public void process() {
		Event next = World.getHistoricalEvent(getId() + 1);
		if (next instanceof AddHfHfLinkEvent) {
			AddHfHfLinkEvent event = (AddHfHfLinkEvent) next;
			if (getCalcHfId() == -1)
				setCalcHfId(event.getHfId());
		}
		if (next instanceof ChangeHfStateEvent) {
			ChangeHfStateEvent event = (ChangeHfStateEvent) next;
			if (getCalcHfId() == -1)
				setCalcHfId(event.getHfId());

			World.getHistoricalEvents().stream()
					.filter(e -> e.getId() < this.getId() && e instanceof AddHfEntityLinkEvent
							&& ((AddHfEntityLinkEvent) e).getCalcHfId() == this.getCalcHfId())
					.map(e -> (AddHfEntityLinkEvent) e).map(AddHfEntityLinkEvent::getCalcLinkType)
					.forEach(this::setCalcLinkType);

			if (getCalcLinkType().equals("prisoner") && event.getState().equals("wandering"))
				event.setState("refugee");
		}
	}

	@Override
	public String getDescription() {
		String civ = World.getEntity(civId).getLink();
		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (calcHfId != -1)
			hf = World.getHistoricalFigure(calcHfId).getLink();
		switch (calcLinkType) {
		case "prisoner":
			return hf + " escaped from the prisons of " + civ;
		case "member":
			return hf + " left " + civ;
		case "position":
			return hf + " ceased to be the " + position + " of" + civ;
		default:
			return hf + " left (" + calcLinkType + ") " + civ;
		}
	}

	@Override
	public String getShortDescription() {
		// if(calcHfId != -1)
		return getDescription();

		// String info = "<ul>";
		// Event prev = World.getHistoricalEvent(getId() - 1);
		// if (prev != null)
		// info += "<li>after: " + prev.getDescription() + "</li>";
		// Event next = World.getHistoricalEvent(getId() + 1);
		// if (next != null)
		// info += "<li>before: " + next.getDescription() + "</li>";
		// info += "</ul>";
		// return getDescription() + info;
	}

	public static void printUnknownLinkTypes() {
		linkTypes.remove("member");
		linkTypes.remove("prisoner");
		linkTypes.remove("position");

		if (linkTypes.size() > 0)
			System.out.println("unknown hf entity link types: " + linkTypes);
	}

}
