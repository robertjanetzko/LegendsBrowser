package legends.web;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class CollectionsController {

	@RequestMapping("/collections")
	public Template collections(VelocityContext context) {
		context.put("events",
				World.getHistoricalEventCollections().stream().collect(Collectors.toList()));
		context.put("types", World.getHistoricalEventCollections().stream()
				.map(EventCollection::getType).distinct().collect(Collectors.toList()));
		
		return Velocity.getTemplate("collections.vm");
	}
	
	@RequestMapping("/collection/{id}")
	public Template collection(VelocityContext context, int id) {
		context.put("events", Arrays.asList(World.getHistoricalEventCollection(id)));
		context.put("types", World.getHistoricalEventCollections().stream()
				.map(EventCollection::getType).distinct().collect(Collectors.toList()));
		
		return Velocity.getTemplate("collections.vm");
	}
	
	@RequestMapping("/ctype/{name}")
	public Template ctype(VelocityContext context, String name) {
		context.put("events", World.getHistoricalEventCollections().stream()
				.filter(e -> e.getType().equals(name)).collect(Collectors.toList()));
		context.put("types", World.getHistoricalEventCollections().stream()
				.map(EventCollection::getType).distinct().collect(Collectors.toList()));
		
		return Velocity.getTemplate("collections.vm");
	}

}
