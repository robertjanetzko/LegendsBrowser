package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.events.CreatedSiteEvent;
import legends.model.events.DestroyedSiteEvent;
import legends.model.events.HfDestroyedSiteEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.Filters;

public class Site {
	private int id;
	private String name;
	private String type;
	private int x;
	private int y;

	private List<Structure> structures = new ArrayList<>();

	private List<Event> events = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return EventHelper.name(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setStructures(List<Structure> structures) {
		for(Structure s : structures)
			s.setSiteId(id);
		this.structures = structures;
	}

	public List<Structure> getStructures() {
		return structures;
	}

	public List<Event> getEvents() {
		return events;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " (" + type + ")" + " " + x + "," + y;
	}
	
	public String getURL() {
		return "/site/" + id;
	}

	public String getLink() {
		return "<a href=\"" + getURL() + "\" class=\"site\">" + getName() + "</a>";
	}

	public String getFounded() {
		;
		return events.stream()
				.collect(Filters.get(CreatedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
					return e.getYear() + " by " + World.getEntity(e.getSiteCivId()!=-1?e.getSiteCivId():e.getCivId()).getLink();
				}).findFirst().orElse("");
	}

	public String getDestroyed() {
		return events.stream()
				.collect(Filters.get(DestroyedSiteEvent.class, e -> e.getSiteId() == id))
				.map(e -> {
					return e.getYear() + " by " + World.getEntity(e.getAttackerCivId()).getLink();
				}).findFirst().orElse(World.getHistoricalEvents().stream()
						.collect(Filters.get(HfDestroyedSiteEvent.class, e -> e.getSiteId() == id)).map(e -> {
							return e.getYear() + " by " + World.getHistoricalFigure(e.getAttackerHfId()).getLink();
						}).findFirst().orElse(""));
	}
}
