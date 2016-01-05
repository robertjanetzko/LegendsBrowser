package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Site;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class SitesController {

	@RequestMapping("/sites")
	public Template artifacts(VelocityContext context) {
		context.put("title", "Sites");
		context.put("elements", World.getSites());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/site/{id}")
	public Template artifact(VelocityContext context, int id) {
		Site s = World.getSite(id);
		
		context.put("site", s);
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> EventHelper.related(s, e)).collect(Collectors.toList()));
		
		return Velocity.getTemplate("site.vm");
	}
}
