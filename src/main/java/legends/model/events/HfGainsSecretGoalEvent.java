package legends.model.events;

import java.util.HashSet;
import java.util.Set;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.HfEvent;

public class HfGainsSecretGoalEvent extends HfEvent {
	private String secretGoal;

	private static Set<String> secretGoals = new HashSet<>();

	public String getSecretGoal() {
		return secretGoal;
	}

	public void setSecretGoal(String secretGoal) {
		this.secretGoal = secretGoal;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "secret_goal":
			secretGoals.add(value);
			setSecretGoal(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public String getShortDescription() {
		HistoricalFigure hf = World.getHistoricalFigure(getHfId());
		String link = hf.getLink();
		switch (secretGoal) {
		case "immortality":
			return link + " became obsessed with "+hf.getPronoun()+" own mortality and sought to extend "+hf.getPronoun()+" life by any means";
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
