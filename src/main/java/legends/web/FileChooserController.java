package legends.web;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import legends.Application;
import legends.WorldState;
import legends.helper.Templates;
import legends.helper.WorldConfig;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller(state = WorldState.FILE_SELECT)
public class FileChooserController {
	@RequestMapping("")
	public Template files(VelocityContext context) throws IOException {
		Path path = Paths.get(System.getProperty("user.home"));
		if (Application.getProperty("last") != null)
			path = Paths.get(Application.getProperty("last"));
		if (Application.getProperty("root") != null)
			path = Paths.get(Application.getProperty("root"));
		if (context.containsKey("path"))
			path = Paths.get((String) context.get("path"));
		try {
			path = path.toRealPath();
		} catch (Exception e) {
			path = Paths.get(System.getProperty("user.home")).toRealPath();
		}

		if (path.toString().toLowerCase().endsWith(".xml") || path.toString().toLowerCase().endsWith(".zip")) {
			World.load(path);
			context.put("state", World.getLoadingState());
			return Templates.get("loading.vm");
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
			Collections.sort(dirs);
			context.put("dirs", dirs);

			List<Path> files = new ArrayList<>();
			Files.newDirectoryStream(path, WorldConfig::isLegendsFile).forEach(files::add);
			Collections.sort(files);
			context.put("files", files);

			return Templates.get("load.vm");
		}
	}

	@RequestMapping("/compress")
	public Template compress(VelocityContext context) throws IOException {
		Path path = Paths.get((String) context.get("path"));
		WorldConfig config = new WorldConfig(path);

		try {
			Map<String, String> env = new HashMap<>();
			env.put("create", "true");
			URI uri = URI.create(
					"jar:" + Paths.get(path.toString().replace("-legends.xml", "-legends_archive.zip")).toUri());
			FileSystem fs;
			try {
				fs = FileSystems.getFileSystem(uri);
			} catch (FileSystemNotFoundException e) {
				fs = FileSystems.newFileSystem(uri, env);
			}
			move(fs, config.getLegendsPath());
			move(fs, config.getLegendsPlusPath());
			move(fs, config.getImagePath());
			move(fs, config.getHistoryPath());
			move(fs, config.getSitesAndPropsPath());
			copy(fs, config.getWorldGenPath());
			fs.close();
		} catch (Exception e) {
			context.put("error", e.toString());
		}

		context.put("path", path.getParent().toString());
		return files(context);
	}

	private void move(FileSystem fs, Path path) throws IOException {
		if (Files.exists(path))
			Files.move(path, fs.getPath(path.getFileName().toString()));
	}

	private void copy(FileSystem fs, Path path) throws IOException {
		if (Files.exists(path))
			Files.copy(path, fs.getPath(path.getFileName().toString()));
	}
}
