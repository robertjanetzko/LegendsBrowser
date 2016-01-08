package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class PopoverController {

	@RequestMapping("/popover/collection/{id}")
	public String collection(VelocityContext context, int id) {
		EventCollection collection = World.getHistoricalEventCollection(id);
		return "In " + collection.getStartYear() + ", " + collection.getLink();
	}

	@RequestMapping("/popover/entity/{id}")
	public Template entity(VelocityContext context, int id) {
		context.put("entity", World.getEntity(id));
		return Velocity.getTemplate("popover/entity.vm");
	}

	@RequestMapping("/popover/site/{id}")
	public Template site(VelocityContext context, int id) {
		context.put("site", World.getSite(id));
		return Velocity.getTemplate("popover/site.vm");
	}

	@RequestMapping("/popover/structure/{id}")
	public Template structure(VelocityContext context, int id) {
		context.put("structure", World.getStructure(id % 100, id / 100));
		return Velocity.getTemplate("popover/structure.vm");
	}

	@RequestMapping("/popover/region/{id}")
	public Template region(VelocityContext context, int id) {
		context.put("region", World.getRegion(id));
		return Velocity.getTemplate("popover/region.vm");
	}

	@RequestMapping("/popover/artifact/{id}")
	public Template artifact(VelocityContext context, int id) {
		context.put("artifact", World.getArtifact(id));
		return Velocity.getTemplate("popover/artifact.vm");
	}

	@RequestMapping("/popover/worldconstruction/{id}")
	public Template worldconstruction(VelocityContext context, int id) {
		context.put("wc", World.getWorldConstruction(id));
		return Velocity.getTemplate("popover/worldconstruction.vm");
	}

	@RequestMapping("/popover/hf/{id}")
	public Template hf(VelocityContext context, int id) {
		context.put("hf", World.getHistoricalFigure(id));
		return Velocity.getTemplate("popover/hf.vm");
	}

}
