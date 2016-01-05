package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.WorldConstruction;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class WorldConstructionsController {

	@RequestMapping("/worldconstructions")
	public Template sites(VelocityContext context) {
		context.put("title", "World Constructions");
		context.put("elements", World.getWorldConstructions());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/worldconstruction/{id}")
	public Template site(VelocityContext context, int id) {
		WorldConstruction wc = World.getWorldConstruction(id);

		context.put("wc", wc);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(wc, e))
				.collect(Collectors.toList()));

		return Velocity.getTemplate("worldconstruction.vm");
	}
}
