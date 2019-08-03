package legends.model.events;

import legends.model.World;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;
import legends.helper.EventHelper;

@XmlSubtype("changed creature type")
public class ChangedCreatureTypeEvent extends Event implements HfRelatedEvent {
	@Xml("changee,changee_hfid")
	private int changeeHfId = -1;
	@Xml("changer,changer_hfid")
	private int changerHfId = -1;
	@Xml("old_race")
	private String oldRace;
	@Xml("old_caste")
	private String oldCaste;
	@Xml("new_race")
	private String newRace;
	@Xml("new_caste")
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
	public boolean isRelatedToHf(int hfId) {
		return changeeHfId == hfId || changerHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String changee = World.getHistoricalFigure(getChangeeHfId()).getLink();
		String changer = World.getHistoricalFigure(getChangerHfId()).getLink();
		return changer + " changed " + changee + " from a " + EventHelper.race(oldRace) + " into a " + EventHelper.race(newRace);
	}
}
