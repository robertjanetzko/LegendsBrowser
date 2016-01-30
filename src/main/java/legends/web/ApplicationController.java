package legends.web;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.WorldState;
import legends.helper.Templates;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.ANY)
public class ApplicationController {

	@RequestMapping("/exit")
	public Template exit(VelocityContext context) {
		return Templates.get("exit.vm");
	}

	@RequestMapping("/loading.json")
	public Template currentState(VelocityContext context) {
		context.put("ready", World.getState() == WorldState.READY);
		context.put("message", StringEscapeUtils.escapeJavaScript(World.getLoadingState()));

		return Templates.get("loadingState.vm");
	}

}
