package legends.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.helper.Templates;
import legends.model.World;
import legends.model.WrittenContent;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class WrittenContentsController {

	@RequestMapping("/writtencontents")
	public Template writtencontents(VelocityContext context) {
		Collection<WrittenContent> allWrittenContents = World.getWrittenContents();

		context.put("authoredplaces", new TreeSet<String>(allWrittenContents.stream().map(hf -> {
			return hf.getAuthoredIn().getName();
		}).collect(Collectors.toList())));

		String authoredIn = (String)context.get("site-authored");

		Map<String,List<WrittenContent>> writtencontents = allWrittenContents.stream().filter(wc -> {
				if (authoredIn != null) {
					String wc_authoredIn = wc.getAuthoredIn().getName();

					if (wc_authoredIn == null) {
						if (!authoredIn.equals("UNKNOWN")) {
							return false;
						}
					} else if (!wc_authoredIn.equals(authoredIn)) {
						return false;
					}
				}

				return true;
			}).collect(Collectors.groupingBy(WrittenContent::getType));
		context.put("writtencontents", writtencontents);
		List<String> types = writtencontents.keySet().stream().sorted((t1,t2) -> (writtencontents.get(t1).size() < writtencontents.get(t2).size()) ? 1 : -1).collect(Collectors.toList());
		context.put("types", types);
		return Templates.get("writtencontents.vm");
	}

	@RequestMapping("/writtencontent/{id}")
	public Template writtencontent(VelocityContext context, int id) {
		WrittenContent wc = World.getWrittenContent(id);
		
		context.put("wc", wc);
		
		return Templates.get("writtencontent.vm");
	}
}
