package legends.model.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import legends.model.Entity;
import legends.model.HistoricalFigure;
import legends.model.Occasion;
import legends.model.Schedule;
import legends.model.World;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("competition")
public class CompetitionEvent extends OccasionEvent implements HfRelatedEvent {
	@Xml("winner_hfid")
	private int winnerHfId = -1;
	@Xml(value = "competitor_hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> competitorHfIds = new ArrayList<>();

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
	public boolean isRelatedToHf(int hfId) {
		return winnerHfId == hfId || competitorHfIds.contains(hfId);
	}

	@Override
	public String getShortDescription() {
		Entity civ = World.getEntity(civId);
		Occasion occ = civ.getOccasion(occasionId);
		Schedule sch = occ.getSchedule(scheduleId);

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

		String type = sch.getType();
		switch (type) {
		case "poetry competition":
			type = "competition involving " + World.getPoeticForm(sch.getReference()).getLink();
			break;
		case "musical competition":
			type = "competition involving " + World.getMusicalForm(sch.getReference()).getLink();
			break;
		case "dance competition":
			type = "competition involving " + World.getDanceForm(sch.getReference()).getLink();
			break;
		case "":
			type = "competition";
		}

		return civ.getLink() + " held a " + type + " " + location.getLink("in") + " as part of " + occ.getName()
				+ competitors + winner;
	}

}
