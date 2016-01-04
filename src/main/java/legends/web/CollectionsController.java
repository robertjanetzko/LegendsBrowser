package legends.web;

import java.util.Arrays;
import java.util.List;
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
		List<String> types = Arrays.asList("war", "battle", "beast attack", "duel", "journey", "abduction", "theft");
		context.put("types", types);
		context.put("events", World.getHistoricalEventCollections().stream().filter(e -> types.contains(e.getType()))
				.collect(Collectors.groupingBy(EventCollection::getType)));

		return Velocity.getTemplate("collections.vm");
	}

	@RequestMapping("/collection/{id}")
	public Template collection(VelocityContext context, int id) {
		context.put("event", World.getHistoricalEventCollection(id));

		return Velocity.getTemplate("collection.vm");
	}

}
