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
		boolean leader = context.containsKey("leader");
		boolean deity = context.containsKey("deity");
		boolean force = context.containsKey("force");
		boolean vampire = context.containsKey("vampire");
		boolean werebeast = context.containsKey("werebeast");
		boolean necromancer = context.containsKey("necromancer");
		boolean alive = context.containsKey("alive");
		boolean ghost = context.containsKey("ghost");
		boolean adventurer = context.containsKey("adventurer");

		if (leader || deity || force || vampire || werebeast || necromancer || alive || ghost || adventurer) {
			context.put("elements", World.getHistoricalFigures().stream().filter(hf -> {
				if (leader && !hf.isLeader())
					return false;
				if (deity && !hf.isDeity())
					return false;
				if (force && !hf.isForce())
					return false;
				if (vampire && !hf.isVampire())
					return false;
				if (werebeast && !hf.isWerebeast())
					return false;
				if (necromancer && !hf.isNecromancer())
					return false;
				if (alive && hf.getDeathYear() != -1)
					return false;
				if (ghost && !hf.isGhost())
					return false;
				if (adventurer && !hf.isAdventurer())
					return false;
				return true;
			}).collect(Collectors.toList()));
		} else {
			context.put("elements", World.getHistoricalFigures());
		}

		return Velocity.getTemplate("hfs.vm");
	}

	@RequestMapping("/hf/{id}")
	public Template hf(VelocityContext context, int id) {
		HistoricalFigure hf = World.getHistoricalFigure(id);
		HistoricalFigure.setContext(hf);

		context.put("hf", hf);
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(hf, e))
				.collect(Collectors.toList()));

		return Velocity.getTemplate("hf.vm");
	}
}
