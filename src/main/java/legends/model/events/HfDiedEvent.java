package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.Item;
import legends.model.events.basic.LocalEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf died")
public class HfDiedEvent extends HfEvent implements LocalEvent, ArtifactRelatedEvent {
	@Xml("slayer_hfid,slayer_hf")
	private int slayerHfId;
	@Xml("slayer_race")
	private String slayerRace;
	@Xml("slayer_caste")
	private String slayerCaste;
	@Xml("slayer_item_id")
	private int slayerItemId;
	@Xml("slayer_shooter_item_id")
	private int slayerShooterItemId;
	private String cause;

	@XmlComponent
	private Item item = new Item();
	@XmlComponent(prefix = "shooter_")
	private Item shooterItem = new Item();
	@Xml("artifact_id")
	private int artifactId = -1;

	@XmlComponent
	private EventLocation location = new EventLocation("");

	private static Set<String> causes = new HashSet<>();

	@Xml("victim_hf")
	public void setHfId(int id) {
		super.setHfId(id);
	}

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

	@Xml(value = "cause,death_cause", track = true)
	public void setCause(String cause) {
		causes.add(cause);
		this.cause = cause;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
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
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
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

		if (artifactId != -1) {
			slayer += " with " + World.getArtifact(artifactId).getLink();
		} else {
			slayer += item.getText("with a");
			slayer += shooterItem.getText("from a");
		}
		switch (cause) {
		case "old age":
		case "old_age":
			return hf + " died of old age" + loc;
		case "melt":
			return hf + " melted" + loc;
		case "drown":
			return hf + " drowned" + loc;
		case "freezing water":
		case "encase_ice":
			return hf + " was encased in ice" + loc;
		case "struck":
		case "struck_down":
			return hf + " was struck down" + slayer + loc;
		case "murder":
		case "murdered":
			return hf + " was murdered" + slayer + loc;
		case "shot":
			return hf + " was shot and killed" + slayer + loc;
		case "exec generic":
			return hf + " was executed" + slayer + loc;
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
		case "crucify":
		case "exec crucified":
			return hf + " was crucified" + slayer + loc;
		case "air":
		case "suffocate":
			return hf + " suffocated, slain by " + slayer + loc;
		case "blood":
		case "bleed":
			return hf + " bled to death, slain by " + slayer + loc;
		case "obstacle":
		case "collision":
			return hf + " died after colliding with an obstacle, slain by " + slayer + loc;
		case "drawbridge":
		case "crushed bridge":
			return hf + " was crushed by a drawbridge" + loc;
		case "drain_blood":
		case "blood drained":
			return hf + " was drained of blood by " + slayer + loc;
		case "slaughter":
		case "slaughtered":
			return hf + " was slaughtered by " + slayer + loc;
		case "thirst":
			return hf + " died of thirst" + loc;
		case "scuttle":
		case "scuttled":
			return hf + " was scuttled" + loc;
		case "memorialize":
		case "put to rest":
			return hf + " was put to rest" + loc;
		case "suicide drowned":
			return hf + " drowned " + (World.getHistoricalFigure(hfId).getSex() == -1 ? "himself " : "herself ") + loc;
		case "suicide leaping":
			return hf + " leapt from a great height" + loc;
		case "chasm":
			return hf + " fell into a deep chasm" + loc;
		case "hunger":
		case "quitdead":
			return hf + " starved" + loc;
		case "infection":
			return hf + " succumbed to infection" + loc;
		case "trap":
			return hf + " was killed by a trap" + loc;
		case "dragonfire":
			return hf + " burned up in "+slayer+"'s dragon fire" + loc;
		default:
			return hf + " died: " + cause + slayer + loc;
		}
	}

	public static void printUnknownCauses() {
		causes.remove("old age");
		causes.remove("melt");
		causes.remove("drown");
		causes.remove("struck");
		causes.remove("murdered");
		causes.remove("shot");
		causes.remove("exec beheaded");
		causes.remove("exec drowned");
		causes.remove("exec hacked to pieces");
		causes.remove("exec buried alive");
		causes.remove("exec fed to beasts");
		causes.remove("air");
		causes.remove("bleed");
		causes.remove("obstacle");
		causes.remove("freezing water");

		causes.remove("old_age");
		causes.remove("murder");
		causes.remove("drown_alt");
		causes.remove("bury_alive");
		causes.remove("struck_down");
		causes.remove("hack_to_pieces");
		causes.remove("behead");
		causes.remove("feed_to_beasts");
		causes.remove("suffocate");
		causes.remove("blood");
		causes.remove("collision");
		causes.remove("encase_ice");

		causes.remove("crushed bridge");
		causes.remove("drawbridge");
		causes.remove("slaughter");
		causes.remove("slaughtered");
		causes.remove("thirst");
		causes.remove("scuttle");
		causes.remove("scuttled");
		causes.remove("burn_alive");
		causes.remove("exec burned alive");
		causes.remove("drain_blood");
		causes.remove("blood drained");
		causes.remove("crucify");
		causes.remove("exec crucified");
		causes.remove("memorialize");
		causes.remove("put to rest");

		if (causes.size() > 0)
			LOG.debug("unknown hf died causes: " + causes);
	}
	
	@Override
	public void process() {
		if(slayerHfId != -1)
			World.getHistoricalFigure(slayerHfId).addKill();
	}

}
