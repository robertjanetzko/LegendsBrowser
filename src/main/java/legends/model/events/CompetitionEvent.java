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
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
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

		return civ + " held a UNKNOWN competition in " + location.getLink("in") + " as part of the " + occasionId + "-"
				+ scheduleId + competitors + winner;
	}

}
