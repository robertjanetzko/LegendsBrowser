package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class WorldMapController {

	@RequestMapping("/worldmap")
	public Template worldmap(VelocityContext context) {
		return Templates.get("worldMap.vm");
	}

}
