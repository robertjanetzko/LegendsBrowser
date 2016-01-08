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
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || slayerHfId == hfId;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "victim_hf":
			setHfId(Integer.parseInt(value));
			break;
		case "slayer_hf":
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
		case "death_cause":
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
		String slayer = "";
		if (slayerHfId != -1)
			slayer = " by " + World.getHistoricalFigure(slayerHfId).getLink();
		else if (slayerRace != null && !slayerRace.equals("-1"))
			slayer = " by a " + slayerRace;
		String loc = location.getLink("in");

		switch (cause) {
		case "old age":
		case "old_age":
			return hf + " died of old age" + loc;
		case "struck":
		case "struck_down":
			return hf + " was struck down" + slayer + loc;
		case "murder":
		case "murdered":
			return hf + " was murdered" + slayer + loc;
		case "shot":
			return hf + " was shot and killed" + slayer + loc;
		case "behead":
		case "exec beheaded":
			return hf + " was beheaded" + slayer + loc;
		case "drown_alt":
		case "exec drowned":
			return hf + " was drowned" + slayer + loc;
		case "hack_to_pieces":
		case "exec hacked to pieces":
			return hf + " was hacked to pieces" + slayer + loc;
		case "bury_alive":
		case "exec buried alive":
			return hf + " was buried alive" + slayer + loc;
		case "burn_alive":
		case "exec burned alive":
			return hf + " was burned alive" + slayer + loc;
		case "feed_to_beasts":
		case "exec fed to beasts":
			return hf + " was fed to beasts" + slayer + loc;
		case "air":
		case "suffocate":
			return hf + " suffocated, slain by " + slayer + loc;
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
		causes.remove("air");

		causes.remove("old_age");
		causes.remove("murder");
		causes.remove("drown_alt");
		causes.remove("bury_alive");
		causes.remove("struck_down");
		causes.remove("hack_to_pieces");
		causes.remove("behead");
		causes.remove("feed_to_beasts");
		causes.remove("suffocate");

		if (causes.size() > 0)
			System.out.println("unknown hf died causes: " + causes);
	}

}
