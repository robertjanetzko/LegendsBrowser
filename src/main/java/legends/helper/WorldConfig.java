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
			imagePath = makeImagePath(path);
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

			Files.list(fs.getPath("/")).forEach(p -> {
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
				else if (s.endsWith("-detailed.png"))
					imagePath = p;
			});

			if (worldGenPath == null)
				worldGenPath = makeWorldGenPath(path);
		}
	}

	private Path makeImagePath(Path path) {
		String cp = path.getFileName().toString().toLowerCase();
		return path.resolveSibling(cp.substring(0, cp.lastIndexOf("-")) + "-world_map.bmp");
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

	public boolean plusAvailable() {
		return legendsPath != null && Files.exists(legendsPlusPath);
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		sw.append("legendsPath:       ").append(legendsPath.toString()).append("\n");
		sw.append("legendsPlusPath:   ").append(legendsPlusPath.toString()).append("\n");
		sw.append("worldGenPath       ").append(worldGenPath.toString()).append("\n");
		sw.append("historyPath:       ").append(historyPath.toString()).append("\n");
		sw.append("sitesAndPropsPath: ").append(sitesAndPropsPath.toString()).append("\n");
		sw.append("imagePath:         ").append(imagePath.toString()).append("\n");
		return sw.toString();
	}

}