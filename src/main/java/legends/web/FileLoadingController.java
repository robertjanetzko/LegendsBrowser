package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.WorldState;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.LOADING)
public class FileLoadingController {

	@RequestMapping("")
	public Template loading(VelocityContext context) {
		context.put("state", World.getLoadingState());

		return Velocity.getTemplate("loading.vm");
	}
}
