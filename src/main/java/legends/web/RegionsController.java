
package legends.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Landmass;
import legends.model.MountainPeak;
import legends.model.Region;
import legends.model.World;
import legends.model.events.HfReachSummitEvent;
import legends.model.events.basic.Filters;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class RegionsController {

	@RequestMapping("/regions")
	public Template regions(VelocityContext context) {
		Map<String, Collection<?>> regions = new HashMap<>();
		regions.put("regions", World.getRegions());
		regions.put("landmasses", World.getLandmasses());
		regions.put("mountain peaks", World.getMountainPeaks());

		context.put("regions", regions);
		List<String> types = regions.keySet().stream()
				.sorted((t1, t2) -> (regions.get(t1).size() < regions.get(t2).size()) ? 1 : -1)
				.collect(Collectors.toList());
		context.put("types", types);
		return Velocity.getTemplate("regions.vm");
	}

	@RequestMapping("/region/{id}")
	public Template region(VelocityContext context, int id) {
		Region r = World.getRegion(id);

		context.put("region", r);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(r, e))
				.collect(Collectors.toList()));

		return Velocity.getTemplate("region.vm");
	}

	@RequestMapping("/landmass/{id}")
	public Template landmass(VelocityContext context, int id) {
		Landmass l = World.getLandmass(id);
		context.put("landmass", l);

		return Velocity.getTemplate("landmass.vm");
	}

	@RequestMapping("/mountain/{id}")
	public Template mountain(VelocityContext context, int id) {
		MountainPeak m = World.getMountainPeak(id);
		context.put("mountain", m);
		context.put("events", World.getHistoricalEvents().stream()
				.collect(Filters.filterEvent(HfReachSummitEvent.class, e -> e.getLocation().getCoords().equals(m.getCoords()))).collect(Collectors.toList()));

		return Velocity.getTemplate("mountain.vm");
	}
}
