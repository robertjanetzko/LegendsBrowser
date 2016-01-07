package legends.web;

import java.util.List;
import java.util.Map;
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
		Map<String,List<WorldConstruction>> sites = World.getWorldConstructions().stream().collect(Collectors.groupingBy(WorldConstruction::getType));
		context.put("worldconstructions", sites);
		List<String> types = sites.keySet().stream().sorted((t1,t2) -> (sites.get(t1).size() < sites.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Velocity.getTemplate("worldconstructions.vm");
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
