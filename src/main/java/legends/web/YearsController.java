package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.World;
import legends.model.events.basic.Event;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class YearsController {

	@RequestMapping("/years")
	public Template years(VelocityContext context) {
		context.put("events", World.getHistoricalEvents());
		context.put("years", World.getHistoricalEvents().stream()
				.collect(Collectors.groupingBy(Event::getYear)));
		
		return Templates.get("years.vm");
	}
	
	@RequestMapping("/year/{id}")
	public Template year(VelocityContext context, int id) {
		context.put("year", id);
		context.put("events",
				World.getHistoricalEvents().stream().filter(e -> e.getYear() == id).collect(Collectors.toList()));
		context.put("types", World.getEventTypes());
		
		return Templates.get("year.vm");
	}
	
	@RequestMapping("/type/{name}")
	public Template type(VelocityContext context, String name) {
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> e.getType().equals(name)).collect(Collectors.toList()));
		context.put("types", World.getEventTypes());
		context.put("type_name", name);
		
		return Templates.get("type.vm");
	}


}
