package legends.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
		List<String> types = Arrays.asList("war", "battle", "beast attack", "duel", "journey", "abduction", "theft", "purge");
		Map<String,List<EventCollection>> collections = World.getHistoricalEventCollections().stream().collect(Collectors.groupingBy(EventCollection::getType));
		context.put("events", collections);
		context.put("types", collections.keySet());
//		context.put("types", types.stream().filter(collections.keySet()::contains).collect(Collectors.toList()));

		return Velocity.getTemplate("collections.vm");
	}

	@RequestMapping("/collection/{id}")
	public Template collection(VelocityContext context, int id) {
		context.put("event", World.getHistoricalEventCollection(id));

		return Velocity.getTemplate("collection.vm");
	}

}
