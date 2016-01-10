package legends.web;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.WorldState;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.FILE_SELECT)
public class FileChooserController {
	@RequestMapping("")
	public Template currentState(VelocityContext context) {
		Path currentPath = Paths.get(System.getProperty("user.home"));
		if (context.containsKey("path"))
			currentPath = Paths.get((String) context.get("path"));

		if (currentPath.toString().toLowerCase().endsWith(".xml") || currentPath.toString().toLowerCase().endsWith(".zip")) {
			World.load(currentPath);
			context.put("state", World.getLoadingState());
			return Velocity.getTemplate("loading.vm");
		} else {
			Path parent = currentPath.getParent();
			Path root = currentPath.getRoot();

			List<Path> roots = new ArrayList<>();
			FileSystems.getDefault().getRootDirectories().forEach(roots::add);

			context.put("roots", roots);
			context.put("file", currentPath.toFile());
			if (parent != null)
				context.put("parent", parent.toFile());
			if (root != null)
				context.put("root", root.toFile());

			return Velocity.getTemplate("load.vm");
		}
	}
}
