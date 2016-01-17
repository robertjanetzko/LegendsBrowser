package legends.web;

import java.nio.file.Files;
import java.util.List;
import java.util.Map;
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
	public Template sites(VelocityContext context) {
		Map<String,List<Site>> sites = World.getSites().stream().collect(Collectors.groupingBy(Site::getType));
		context.put("sites", sites);
		List<String> types = sites.keySet().stream().sorted((t1,t2) -> (sites.get(t1).size() < sites.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Velocity.getTemplate("sites.vm");
	}

	@RequestMapping("/site/{id}")
	public Template site(VelocityContext context, int id) {
		Site s = World.getSite(id);
		
		context.put("site", s);
		context.put("sitemap", Files.exists(World.getSiteMapPath(s.getId())));
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> EventHelper.related(s, e)).collect(Collectors.toList()));
		
		return Velocity.getTemplate("site.vm");
	}
}
