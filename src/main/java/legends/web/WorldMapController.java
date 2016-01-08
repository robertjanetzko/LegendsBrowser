package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class WorldMapController {

	@RequestMapping("/worldmap")
	public Template structures(VelocityContext context) {
		return Velocity.getTemplate("worldmap.vm");
	}

}
