package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class ChangedCreatureTypeEvent extends Event implements HfRelatedEvent {
	private int changeeHfId = -1;
	private int changerHfId = -1;
	private String oldRace;
	private String oldCaste;
	private String newRace;
	private String newCaste;

	public int getChangeeHfId() {
		return changeeHfId;
	}

	public void setChangeeHfId(int changeeHfId) {
		this.changeeHfId = changeeHfId;
	}

	public int getChangerHfId() {
		return changerHfId;
	}

	public void setChangerHfId(int changerHfId) {
		this.changerHfId = changerHfId;
	}

	public String getOldRace() {
		return oldRace;
	}

	public void setOldRace(String oldRace) {
		this.oldRace = oldRace;
	}

	public String getOldCaste() {
		return oldCaste;
	}

	public void setOldCaste(String oldCaste) {
		this.oldCaste = oldCaste;
	}

	public String getNewRace() {
		return newRace;
	}

	public void setNewRace(String newRace) {
		this.newRace = newRace;
	}

	public String getNewCaste() {
		return newCaste;
	}

	public void setNewCaste(String newCaste) {
		this.newCaste = newCaste;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "changee_hfid":
			setChangeeHfId(Integer.parseInt(value));
			break;
		case "changer_hfid":
			setChangerHfId(Integer.parseInt(value));
			break;
		case "old_race":
			setOldRace(value);
			break;
		case "old_caste":
			setOldCaste(value);
			break;
		case "new_race":
			setNewRace(value);
			break;
		case "new_caste":
			setNewCaste(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return changeeHfId == hfId || changerHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String changee = World.getHistoricalFigure(getChangeeHfId()).getLink();
		String changer = World.getHistoricalFigure(getChangerHfId()).getLink();
		return changer + " changed " + changee + " from a " + oldRace.toLowerCase() + " into a "
				+ newRace.toLowerCase();
	}

}
