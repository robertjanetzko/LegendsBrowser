package legends.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class CollectionsController {

	@RequestMapping("/collections")
	public Template collections(VelocityContext context) {
		Map<String, List<EventCollection>> collections = World.getHistoricalEventCollections().stream()
				.collect(Collectors.groupingBy(EventCollection::getType));
		context.put("events", collections);
		context.put("types", collections.keySet());

		return Templates.get("collections.vm");
	}

	@RequestMapping("/collection/{id}")
	public Template collection(VelocityContext context, int id) {
		context.put("event", World.getHistoricalEventCollection(id));

		return Templates.get("collection.vm");
	}

}
