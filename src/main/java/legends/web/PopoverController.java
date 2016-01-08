package legends.web;

import org.apache.velocity.VelocityContext;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class PopoverController {

	@RequestMapping("/popover/collection/{id}")
	public String collection(VelocityContext context, int id) {
		EventCollection collection = World.getHistoricalEventCollection(id);
		return "In "+collection.getStartYear()+", "+collection.getShortDescription();
	}

}
