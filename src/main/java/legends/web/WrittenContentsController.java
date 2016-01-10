package legends.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.model.World;
import legends.model.WrittenContent;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class WrittenContentsController {

	@RequestMapping("/writtencontents")
	public Template writtencontents(VelocityContext context) {
		Map<String,List<WrittenContent>> writtencontents = World.getWrittenContents().stream().collect(Collectors.groupingBy(WrittenContent::getType));
		context.put("writtencontents", writtencontents);
		List<String> types = writtencontents.keySet().stream().sorted((t1,t2) -> (writtencontents.get(t1).size() < writtencontents.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Velocity.getTemplate("writtencontents.vm");
	}

	@RequestMapping("/writtencontent/{id}")
	public Template writtencontent(VelocityContext context, int id) {
		WrittenContent wc = World.getWrittenContent(id);
		
		context.put("wc", wc);
		
		return Velocity.getTemplate("writtencontent.vm");
	}
}
