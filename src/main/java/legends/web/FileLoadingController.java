package legends.web;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.WorldState;
import legends.helper.Templates;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.LOADING)
public class FileLoadingController {

	@RequestMapping("")
	public Template loading(VelocityContext context) {
		context.put("state", World.getLoadingState());

		return Templates.get("loading.vm");
	}
}
