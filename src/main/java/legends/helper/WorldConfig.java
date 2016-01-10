package legends.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class WorldConfig {
	private Path legendsPath;
	private Path legendsPlusPath;
	private Path worldGenPath;
	private Path historyPath;
	private Path sitesAndPropsPath;
	private Path imagePath;

	public WorldConfig(Path path) throws IOException {
		if (path.toString().endsWith(".xml")) {
			worldGenPath = makeWorldGenPath(path);
			legendsPath = path;
			legendsPlusPath = makeLegendsPlusPath(path);
			historyPath = makeHistoryPath(path);
			sitesAndPropsPath = makeSitesAndPropsPath(path);
			Path dir = path.getParent();
			Files.newDirectoryStream(dir, "*-world_map.*").forEach(this::setImagePath);
			Files.newDirectoryStream(dir, "*-detailed.*").forEach(this::setImagePath);
		} else if (path.toString().endsWith(".zip")) {
			Map<String, String> env = new HashMap<>();
			env.put("create", "false");

			URI uri = URI.create("jar:file:" + path.toString());
			FileSystem fs;
			try {
				fs = FileSystems.getFileSystem(uri);
			} catch (FileSystemNotFoundException e) {
				fs = FileSystems.newFileSystem(uri, env);
			}

			Path dir = fs.getPath("/");
			Files.list(dir).forEach(p -> {
				String s = p.toString();
				if (s.endsWith("-legends.xml"))
					legendsPath = p;
				else if (s.endsWith("-legends_plus.xml"))
					legendsPlusPath = p;
				else if (s.endsWith("-world_history.txt"))
					historyPath = p;
				else if (s.endsWith("-world_sites_and_pops.txt"))
					sitesAndPropsPath = p;
				else if (s.endsWith("-world_sites_and_pops.txt"))
					sitesAndPropsPath = p;
			});
			
			Files.newDirectoryStream(dir, "*-world_map.*").forEach(this::setImagePath);
			Files.newDirectoryStream(dir, "*-detailed.*").forEach(this::setImagePath);

			if (worldGenPath == null)
				worldGenPath = makeWorldGenPath(path);
		}
	}

	private Path makeSitesAndPropsPath(Path path) {
		String cp = path.getFileName().toString().toLowerCase();
		return path.resolveSibling(cp.substring(0, cp.lastIndexOf("-")) + "-world_sites_and_pops.txt");
	}

	private Path makeHistoryPath(Path path) {
		String cp = path.getFileName().toString().toLowerCase();
		return path.resolveSibling(cp.substring(0, cp.lastIndexOf("-")) + "-world_history.txt");
	}

	private Path makeLegendsPlusPath(Path path) {
		String cp = path.getFileName().toString().toLowerCase();
		return path.resolveSibling(cp.substring(0, cp.lastIndexOf("-")) + "-legends_plus.xml");
	}

	private Path makeWorldGenPath(Path path) {
		String cp = path.getFileName().toString().toLowerCase();
		return path.resolveSibling(cp.substring(0, cp.lastIndexOf("-") - 12) + "-world_gen_param.txt");
	}

	public Path getLegendsPath() {
		return legendsPath;
	}

	public Path getLegendsPlusPath() {
		return legendsPlusPath;
	}

	public Path getWorldGenPath() {
		return worldGenPath;
	}

	public Path getHistoryPath() {
		return historyPath;
	}

	public Path getSitesAndPropsPath() {
		return sitesAndPropsPath;
	}

	public Path getImagePath() {
		return imagePath;
	}
	
	private void setImagePath(Path imagePath) {
		this.imagePath = imagePath;
	}

	public boolean plusAvailable() {
		return legendsPath != null && Files.exists(legendsPlusPath);
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		sw.append("legendsPath:       ").append(""+legendsPath).append("\n");
		sw.append("legendsPlusPath:   ").append(""+legendsPlusPath).append("\n");
		sw.append("worldGenPath       ").append(""+worldGenPath).append("\n");
		sw.append("historyPath:       ").append(""+historyPath).append("\n");
		sw.append("sitesAndPropsPath: ").append(""+sitesAndPropsPath).append("\n");
		sw.append("imagePath:         ").append(""+imagePath).append("\n");
		return sw.toString();
	}

}