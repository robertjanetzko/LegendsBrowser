package legends.model.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.LocalEvent;

public class CompetitionEvent extends Event implements LocalEvent, EntityRelatedEvent, HfRelatedEvent {
	private int civId = -1;
	private int occasionId = -1;
	private int scheduleId = -1;
	private int winnerHfId = -1;
	private List<Integer> competitorHfIds = new ArrayList<>();

	private EventLocation location = new EventLocation();

	public int getCivId() {
		return civId;
	}

	public void setCivId(int civId) {
		this.civId = civId;
	}

	public int getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(int occasionId) {
		this.occasionId = occasionId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getWinnerHfId() {
		return winnerHfId;
	}

	public void setWinnerHfId(int winnerHfId) {
		this.winnerHfId = winnerHfId;
	}

	public List<Integer> getCompetitorHfIds() {
		return competitorHfIds;
	}

	@Override
	public EventLocation getLocation() {
		return location;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "civ_id":
			setCivId(Integer.parseInt(value));
			break;
		case "occasion_id":
			setOccasionId(Integer.parseInt(value));
			break;
		case "schedule_id":
			setScheduleId(Integer.parseInt(value));
			break;
		case "winner_hfid":
			setWinnerHfId(Integer.parseInt(value));
			break;
		case "competitor_hfid":
			getCompetitorHfIds().add(Integer.parseInt(value));
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return civId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return winnerHfId == hfId || competitorHfIds.contains(hfId);
	}

	@Override
	public String getShortDescription() {
		String civ = World.getEntity(civId).getLink();

		String competitors = "";
		if (competitorHfIds.size() > 0) {
			competitors = ". Competing were ";
			List<String> list = competitorHfIds.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink)
					.collect(Collectors.toList());
			String last = list.remove(list.size() - 1);
			competitors += list.stream().collect(Collectors.joining(", ")) + " and " + last;
		}
		String winner = "";
		if (winnerHfId != -1)
			winner = ". " + World.getHistoricalFigure(winnerHfId).getLink() + " was the victor";

		return civ + " held a UNKNOWN competition in " + location.getLink("in") + " as part of the " + occasionId+"-"+scheduleId
				+ competitors + winner;
	}

}
