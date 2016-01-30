package legends;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import legends.model.World;

public class WorldGenReader {
	private static final Log LOG = LogFactory.getLog(WorldGenReader.class);

	public static void read(Path path) {
		if (path == null || !Files.exists(path)) {
			LOG.warn("no world gen params");
			return;
		}

		try (BufferedReader fr = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
			String line = null;
			while ((line = fr.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("["))
					line = line.substring(1, line.length() - 1);
				String[] data = line.split(":");
				if (line.startsWith("DIM:")) {
					World.setDimension(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				} else if (line.startsWith("END_YEAR")) {
					World.setEndYear(Integer.parseInt(data[1]));
				}
			}

		} catch (Exception e) {
			LOG.error("error loading world gen", e);
		}
	}
}
