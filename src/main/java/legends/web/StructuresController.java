package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Structure;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class StructuresController {

	@RequestMapping("/structures")
	public Template structures(VelocityContext context) {
		context.put("title", "Structures");
		context.put("elements", World.getStructures());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/structure/{id}")
	public Template structure(VelocityContext context, int id) {
		int structureId = id % 100;
		int siteId = id / 100;
		
		Structure s = World.getStructure(structureId, siteId);
		
		context.put("structure", s);
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> EventHelper.related(s, e)).collect(Collectors.toList()));
		
		return Velocity.getTemplate("structure.vm");
	}
}
