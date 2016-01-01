package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.LocalEvent;

public class HfDiedEvent extends HfEvent implements LocalEvent {
	private int slayerHfId;
	private String slayerRace;
	private String slayerCaste;
	private int slayerItemId;
	private int slayerShooterItemId;
	private String cause;

	private EventLocation location = new EventLocation("");

	private static Set<String> causes = new HashSet<>();

	public int getSlayerHfId() {
		return slayerHfId;
	}

	public void setSlayerHfId(int slayerHfId) {
		this.slayerHfId = slayerHfId;
	}

	public String getSlayerRace() {
		return slayerRace;
	}

	public void setSlayerRace(String slayerRace) {
		this.slayerRace = slayerRace;
	}

	public String getSlayerCaste() {
		return slayerCaste;
	}

	public void setSlayerCaste(String slayerCaste) {
		this.slayerCaste = slayerCaste;
	}

	public int getSlayerItemId() {
		return slayerItemId;
	}

	public void setSlayerItemId(int slayerItemId) {
		this.slayerItemId = slayerItemId;
	}

	public int getSlayerShooterItemId() {
		return slayerShooterItemId;
	}

	public void setSlayerShooterItemId(int slayerShooterItemId) {
		this.slayerShooterItemId = slayerShooterItemId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "slayer_hfid":
			setSlayerHfId(Integer.parseInt(value));
			break;
		case "slayer_race":
			setSlayerRace(value);
			break;
		case "slayer_caste":
			setSlayerCaste(value);
			break;
		case "slayer_item_id":
			setSlayerItemId(Integer.parseInt(value));
			break;
		case "slayer_shooter_item_id":
			setSlayerShooterItemId(Integer.parseInt(value));
			break;
		case "cause":
			causes.add(value);
			setCause(value);
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		String hf = World.getHistoricalFigure(hfId).getLink();
		String slayer = "UNKNOWN";
		if (slayerHfId != -1)
			slayer = World.getHistoricalFigure(slayerHfId).getLink();
		else
			slayer = "a " + slayerRace;
		String loc = location.getLink("in");

		switch (cause) {
		case "old age":
			return hf + " died of old age" + loc;
		case "struck":
			return hf + " was struck down by " + slayer + loc;
		case "murdered":
			return hf + " was murdered by " + slayer + loc;
		case "shot":
			return hf + " was shot and killed by " + slayer + loc;
		case "exec beheaded":
			return hf + " was beheaded by " + slayer + loc;
		case "exec drowned":
			return hf + " was drowned by " + slayer + loc;
		case "exec hacked to pieces":
			return hf + " was hacked to pieces by " + slayer + loc;
		case "exec buried alive":
			return hf + " was buried alive by " + slayer + loc;
		case "exec fed to beasts":
			return hf + " was fed to beasts by " + slayer + loc;
		default:
			return super.getShortDescription() + " : " + cause;
		}
	}

	public static void printUnknownCauses() {
		causes.remove("old age");
		causes.remove("struck");
		causes.remove("murdered");
		causes.remove("shot");
		causes.remove("exec beheaded");
		causes.remove("exec drowned");
		causes.remove("exec hacked to pieces");
		causes.remove("exec buried alive");
		causes.remove("exec fed to beasts");

		if (causes.size() > 0)
			System.out.println("unknown hf died causes: " + causes);
	}

}
