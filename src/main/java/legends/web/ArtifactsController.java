package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Region;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class ArtifactsController {

	@RequestMapping("/regions")
	public Template regions(VelocityContext context) {
		context.put("title", "Regions");
		context.put("elements", World.getRegions());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/region/{id}")
	public Template artifact(VelocityContext context, int id) {
		Region r = World.getRegion(id);
		
		context.put("region", r);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(r, e))
				.collect(Collectors.toList()));
		
		return Velocity.getTemplate("region.vm");
	}
}
