package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.HfEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf gains secret goal")
public class HfGainsSecretGoalEvent extends HfEvent {
	@Xml("secret_goal")
	private String secretGoal;

	private static Set<String> secretGoals = new HashSet<>();

	public String getSecretGoal() {
		return secretGoal;
	}

	public void setSecretGoal(String secretGoal) {
		this.secretGoal = secretGoal;
	}

	@Override
	public String getShortDescription() {
		HistoricalFigure hf = World.getHistoricalFigure(getHfId());
		String link = hf.getLink();
		switch (secretGoal) {
		case "immortality":
			return link + " became obsessed with " + hf.getPossesivePronoun() + " own mortality and sought to extend "
					+ hf.getPossesivePronoun() + " life by any means";
		default:
			return link + " gains secret goal " + secretGoal;
		}
	}

	public static void printUnknownSecretGoals() {
		secretGoals.remove("immortality");

		if (secretGoals.size() > 0)
			System.out.println("unknown secret goals: " + secretGoals);
	}

}
