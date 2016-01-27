package legends.web;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.Application;
import legends.WorldState;
import legends.helper.WorldConfig;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.FILE_SELECT)
public class FileChooserController {
	@RequestMapping("")
	public Template currentState(VelocityContext context) throws IOException {
		Path path = Paths.get(System.getProperty("user.home"));
		if (Application.getProperty("last") != null)
			path = Paths.get(Application.getProperty("last"));
		if (Application.getProperty("root") != null)
			path = Paths.get(Application.getProperty("root"));
		if (context.containsKey("path"))
			path = Paths.get((String) context.get("path"));
		path = path.toRealPath();

		if (path.toString().toLowerCase().endsWith(".xml") || path.toString().toLowerCase().endsWith(".zip")) {
			World.load(path);
			context.put("state", World.getLoadingState());
			return Velocity.getTemplate("loading.vm");
		} else {
			context.put("Files", Files.class);
			
			Application.setProperty("last", path.toString());
			context.put("path", path);
			
			List<Path> roots = new ArrayList<>();
			FileSystems.getDefault().getRootDirectories().forEach(roots::add);
			context.put("roots", roots);
			context.put("parent", path.getParent());

			List<Path> dirs = new ArrayList<>();
			Files.newDirectoryStream(path, p -> Files.isDirectory(p)).forEach(dirs::add);
			context.put("dirs", dirs);

			List<Path> files = new ArrayList<>();
			Files.newDirectoryStream(path, WorldConfig::isLegendsFile).forEach(files::add);
			context.put("files", files);

			return Velocity.getTemplate("load.vm");
		}
	}
}
