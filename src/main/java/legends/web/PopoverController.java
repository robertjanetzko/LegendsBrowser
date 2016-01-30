package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class PopoverController {

	@RequestMapping("/popover/collection/{id}")
	public String collection(VelocityContext context, int id) {
		EventCollection collection = World.getHistoricalEventCollection(id);
		String s = "In " + collection.getStartYear() + ", " + collection.getShortDescription();
		EventCollection parent = collection.getCollection();
		while (parent != null) {
			s += "<br> as part of: In " + parent.getStartYear() + ", " + parent.getShortDescription();
			parent = parent.getCollection();
		}

		return s;
	}

	@RequestMapping("/popover/entity/{id}")
	public Template entity(VelocityContext context, int id) {
		context.put("entity", World.getEntity(id));
		return Templates.get("popover/entity.vm");
	}

	@RequestMapping("/popover/site/{id}")
	public Template site(VelocityContext context, int id) {
		context.put("site", World.getSite(id));
		return Templates.get("popover/site.vm");
	}

	@RequestMapping("/popover/structure/{id}")
	public Template structure(VelocityContext context, int id) {
		context.put("structure", World.getStructure(id % 100, id / 100));
		return Templates.get("popover/structure.vm");
	}

	@RequestMapping("/popover/region/{id}")
	public Template region(VelocityContext context, int id) {
		context.put("region", World.getRegion(id));
		return Templates.get("popover/region.vm");
	}

	@RequestMapping("/popover/artifact/{id}")
	public Template artifact(VelocityContext context, int id) {
		context.put("artifact", World.getArtifact(id));
		return Templates.get("popover/artifact.vm");
	}

	@RequestMapping("/popover/worldconstruction/{id}")
	public Template worldconstruction(VelocityContext context, int id) {
		context.put("wc", World.getWorldConstruction(id));
		return Templates.get("popover/worldconstruction.vm");
	}

	@RequestMapping("/popover/hf/{id}")
	public Template hf(VelocityContext context, int id) {
		context.put("hf", World.getHistoricalFigure(id));
		return Templates.get("popover/hf.vm");
	}

}
