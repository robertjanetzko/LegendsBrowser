package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.EventHelper;
import legends.helper.Templates;
import legends.model.Identity;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class IdentitiesController {

	@RequestMapping("/id/{id}")
	public Template identity(VelocityContext context, int id) {
		Identity i = World.getIdentity(id);

		context.put("identity", i);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(i, e))
				.collect(Collectors.toList()));

		return Templates.get("identity.vm");
	}
}
