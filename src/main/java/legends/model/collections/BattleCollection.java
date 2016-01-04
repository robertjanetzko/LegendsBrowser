package legends.model.collections;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import legends.helper.EventHelper;
import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.EventLocation;

public class BattleCollection extends EventCollection {
	private String name;
	private int warEventCol = -1;
	private EventLocation location = new EventLocation();
	private List<Integer> attackingHfIds = new ArrayList<>();
	private List<Integer> defendingHfIds = new ArrayList<>();
	private List<Integer> nocomHfIds = new ArrayList<>();

	private List<Squad> attackingSquads = new ArrayList<>();
	private List<Squad> defendingSquads = new ArrayList<>();
	private Squad squad = new Squad();

	// private List<String> attackingSquadRaces = new ArrayList<>();
	// private List<Integer> attackingSquadEntityPops = new ArrayList<>();
	// private List<Integer> attackingSquadNumbers = new ArrayList<>();
	// private List<Integer> attackingSquadDeaths = new ArrayList<>();
	// private List<Integer> attackingSquadSites = new ArrayList<>();
	// private List<String> defendingSquadRaces = new ArrayList<>();
	// private List<Integer> defendingSquadEntityPops = new ArrayList<>();
	// private List<Integer> defendingSquadNumbers = new ArrayList<>();
	// private List<Integer> defendingSquadDeaths = new ArrayList<>();
	// private List<Integer> defendingSquadSites = new ArrayList<>();
	private String outcome;

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWarEventCol() {
		return warEventCol;
	}

	public void setWarEventCol(int warEventCol) {
		this.warEventCol = warEventCol;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public EventLocation getLocation() {
		return location;
	}

	public List<Integer> getAttackingHfIds() {
		return attackingHfIds;
	}

	public List<Integer> getDefendingHfIds() {
		return defendingHfIds;
	}

	public List<Integer> getNocomHfIds() {
		return nocomHfIds;
	}

	public List<Squad> getAttackingSquads() {
		return attackingSquads;
	}

	public List<Squad> getDefendingSquads() {
		return defendingSquads;
	}

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {
		case "name":
			setName(value);
			break;
		case "war_eventcol":
			setWarEventCol(Integer.parseInt(value));
			break;
		case "attacking_hfid":
			getAttackingHfIds().add(Integer.parseInt(value));
			break;
		case "defending_hfid":
			getDefendingHfIds().add(Integer.parseInt(value));
			break;
		case "noncom_hfid":
			getNocomHfIds().add(Integer.parseInt(value));
			break;
		case "attacking_squad_race":
		case "defending_squad_race":
			squad.setRace(value);
			break;
		case "attacking_squad_entity_pop":
		case "defending_squad_entity_pop":
			squad.setEntityPop(Integer.parseInt(value));
			break;
		case "attacking_squad_number":
		case "defending_squad_number":
			squad.setNumber(Integer.parseInt(value));
			break;
		case "attacking_squad_deaths":
		case "defending_squad_deaths":
			squad.setDeaths(Integer.parseInt(value));
			break;
		case "attacking_squad_site":
		case "defending_squad_site":
			squad.setSite(Integer.parseInt(value));
			if (property.equals("attacking_squad_site"))
				getAttackingSquads().add(squad);
			else
				getDefendingSquads().add(squad);
			squad = new Squad();
			break;
		case "outcome":
			setOutcome(value);
			break;

		default:
			if (!location.setProperty(property, value))
				return super.setProperty(property, value);
			break;
		}
		return true;
	}

	class Squad {
		private String race;
		private int entityPop = -1;
		private int number = -1;
		private int deaths = -1;
		private int site = -1;

		public String getRace() {
			return race;
		}

		public void setRace(String race) {
			this.race = race;
		}

		public int getEntityPop() {
			return entityPop;
		}

		public void setEntityPop(int entityPop) {
			this.entityPop = entityPop;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getDeaths() {
			return deaths;
		}

		public void setDeaths(int deaths) {
			this.deaths = deaths;
		}

		public int getSite() {
			return site;
		}

		public void setSite(int site) {
			this.site = site;
		}

		@Override
		public String toString() {
			return number + " " + race.toLowerCase() + "s (" + deaths + " died)";
		}

	}

	@Override
	public String getShortDescription() {

		try {
			String attackers = "";
			if (attackingHfIds.size() > 0 || attackingSquads.size() > 0)
				attackers = "<li>Attackers: " + Stream
						.concat(attackingHfIds.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink),
								attackingSquads.stream().map(Squad::toString))

						.collect(EventHelper.listCollector()) + "</li>";
			String defenders = "";
			if (defendingHfIds.size() > 0 || defendingSquads.size() > 0)
				defenders = "<li>Defenders: " + Stream
						.concat(defendingHfIds.stream().map(World::getHistoricalFigure).map(HistoricalFigure::getLink),
								defendingSquads.stream().map(Squad::toString))

						.collect(EventHelper.listCollector()) + "</li>";
			String nocom = "";
			if (nocomHfIds.size() > 0)
				nocom = "<li>Non-Combat: " + nocomHfIds.stream().map(World::getHistoricalFigure)
						.map(HistoricalFigure::getLink).collect(EventHelper.listCollector()) + "</li>";

			return getLink() + " occurred<ul>" + "<li>" + outcome + "</li></ul>";
//			return getLink() + " occurred<ul>" + attackers + defenders + nocom + "<li>" + outcome + "</li></ul>";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errore";
		}
	}

	public String getLink() {
		return "<a href=\"/collection/" + getId() + "\" class=\"battle\">" + getName() + "</a>";
	}

}
