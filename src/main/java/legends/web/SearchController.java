package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class SearchController {

	@RequestMapping("/search")
	public Template search(VelocityContext context) {
		if (context.containsKey("query")) {
			String query = context.get("query").toString().toLowerCase();

			context.put("query", query);

			context.put("regions", World.getRegions().stream().filter(e -> e.getName().toLowerCase().contains(query))
					.collect(Collectors.toList()));
			context.put("sites", World.getSites().stream().filter(e -> e.getName().toLowerCase().contains(query))
					.collect(Collectors.toList()));
			context.put("artifacts", World.getArtifacts().stream()
					.filter(e -> e.getName().toLowerCase().contains(query)).collect(Collectors.toList()));
			context.put("entities", World.getEntities().stream().filter(e -> e.getName().toLowerCase().contains(query))
					.collect(Collectors.toList()));
			context.put("hfs", World.getHistoricalFigures().stream()
					.filter(e -> e.getName().toLowerCase().contains(query)).collect(Collectors.toList()));

			return Velocity.getTemplate("search.vm");

		} else
			return Velocity.getTemplate("index.vm");
	}

}
