package legends.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;
import legends.web.util.SearchResult;

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

			return Templates.get("search.vm");

		} else
			return Templates.get("index.vm");
	}

	@RequestMapping("/search.json")
	public Template searchJSON(VelocityContext context) {
		String query = context.get("query").toString().toLowerCase();

		List<SearchResult> results = new ArrayList<>();

		World.getEntities().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getSites().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getStructures().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getHistoricalFigures().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getRegions().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getArtifacts().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);
		World.getWorldConstructions().stream().filter(e -> e.getName().toLowerCase().contains(query))
				.map(e -> new SearchResult(e.getName(), e.getURL())).forEach(results::add);

		context.put("results", results);
		context.put("contentType", "application/json");

		return Templates.get("searchjson.vm");
	}

}
