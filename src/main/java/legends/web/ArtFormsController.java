package legends.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.ArtForm;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class ArtFormsController {

	@RequestMapping("/artforms")
	public Template structures(VelocityContext context) {
		Map<String,Collection<? extends ArtForm>> artForms = new HashMap<>();
		artForms.put("poetic form", World.getPoeticForms());
		artForms.put("musical form", World.getMusicalForms());
		artForms.put("dance form", World.getDanceForms());

		context.put("artForms", artForms);
		List<String> types = artForms.keySet().stream().sorted((t1,t2) -> (artForms.get(t1).size() < artForms.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Templates.get("artforms.vm");
	}

	@RequestMapping("/poeticform/{id}")
	public Template poeticform(VelocityContext context, int id) {
		context.put("artform", World.getPoeticForm(id));
		return Templates.get("artform.vm");
	}

	@RequestMapping("/musicalform/{id}")
	public Template musicalform(VelocityContext context, int id) {
		context.put("artform", World.getMusicalForm(id));
		return Templates.get("artform.vm");
	}

	@RequestMapping("/danceform/{id}")
	public Template danceform(VelocityContext context, int id) {
		context.put("artform", World.getDanceForm(id));
		return Templates.get("artform.vm");
	}

}
