package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class EntitiesController {

	@RequestMapping("")
	public Template index(VelocityContext context) {
		context.put("entityMap", World.getMainCivilizations().stream().collect(Collectors.groupingBy(Entity::getRace)));

		return Velocity.getTemplate("index.vm");
	}

	@RequestMapping("/entities.json")
	public Template chordData(VelocityContext context) {
		context.put("entities", World.getMainCivilizations().stream().sorted((e1, e2) -> {
			return e1.getRace().compareTo(e2.getRace());
		}).collect(Collectors.toList()));

		return Velocity.getTemplate("chord.vm");
	}

	@RequestMapping("/entity/{id}")
	public Template entity(VelocityContext context, int id) {
		Entity en = World.getEntity(id);

		context.put("entity", en);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(en, e))
				.collect(Collectors.toList()));

		return Velocity.getTemplate("entity.vm");
	}
}
