package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.EventHelper;
import legends.helper.Templates;
import legends.model.Artifact;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class ArtifactsController {

	@RequestMapping("/artifacts")
	public Template artifacts(VelocityContext context) {
		context.put("title", "Artifacts");
		context.put("elements", World.getArtifacts());

		return Templates.get("list.vm");
	}

	@RequestMapping("/artifact/{id}")
	public Template artifact(VelocityContext context, int id) {
		Artifact a = World.getArtifact(id);
		
		context.put("artifact", a);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(a, e))
				.collect(Collectors.toList()));
		return Templates.get("artifact.vm");
	}
}