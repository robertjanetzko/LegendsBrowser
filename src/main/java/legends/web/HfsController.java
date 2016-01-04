package legends.web;

import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.HistoricalFigure;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class HfsController {

	@RequestMapping("/hfs")
	public Template hfs(VelocityContext context) {
		context.put("title", "Historical Figures");
		context.put("elements", World.getHistoricalFigures());

		return Velocity.getTemplate("list.vm");
	}

	@RequestMapping("/hf/{id}")
	public Template hf(VelocityContext context, int id) {
		HistoricalFigure hf = World.getHistoricalFigure(id);
		
		context.put("hf", hf);
		context.put("events", World.getHistoricalEvents().stream()
				.filter(e -> EventHelper.related(hf, e)).collect(Collectors.toList()));
		
		return Velocity.getTemplate("hf.vm");
	}
}
