package legends.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.EventHelper;
import legends.helper.Templates;
import legends.model.Structure;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class StructuresController {

	@RequestMapping("/structures")
	public Template structures(VelocityContext context) {
		Map<String,List<Structure>> structures = World.getStructures().stream().collect(Collectors.groupingBy(Structure::getType));
		context.put("structures", structures);
		List<String> types = structures.keySet().stream().sorted((t1,t2) -> (structures.get(t1).size() < structures.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Templates.get("structures.vm");
	}

	@RequestMapping("/structure/{id}")
	public Template structure(VelocityContext context, int id) {
		int structureId = id % 100;
		int siteId = id / 100;
		
		Structure s = World.getStructure(structureId, siteId);
		
		context.put("structure", s);
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> EventHelper.related(s, e)).collect(Collectors.toList()));
		
		return Templates.get("structure.vm");
	}
}
