package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Artifact;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class RegionsController {

	@RequestMapping("/artifacts")
	public Template artifacts(VelocityContext context) {
		context.put("title", "Artifacts");
		context.put("elements", World.getArtifacts());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/artifact/{id}")
	public Template artifact(VelocityContext context, int id) {
		Artifact a = World.getArtifact(id);
		
		context.put("artifact", a);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(a, e))
				.collect(Collectors.toList()));
		return Velocity.getTemplate("artifact.vm");
	}
}
