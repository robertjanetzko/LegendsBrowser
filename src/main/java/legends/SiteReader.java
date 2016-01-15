package legends;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import legends.model.Population;
import legends.model.Site;
import legends.model.World;

public class SiteReader {
	public static void read(Path path) {
		try (BufferedReader fr = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {

			Site site = null;

			String line = null;
			while ((line = fr.readLine()) != null) {
				if (line.startsWith("Civilized World Population")) {
					fr.readLine();
					while ((line = fr.readLine()) != null) {
						line = line.trim();
						if (line.equals(""))
							break;
						World.getPopulations().add(parseLine(line));
					}
					fr.readLine(); // Total
					fr.readLine(); //
					fr.readLine(); // Sites
					fr.readLine(); //
					continue;
				} else if (line.trim().equals("")) {
					return;
				} else if (!line.startsWith("\t")) { // New Site
					site = World.getSite(Integer.parseInt(line.substring(0, line.indexOf(":"))));
				} else if (line.contains(":")) {
				} else {
					site.getPopulations().add(parseLine(line.trim()));
				}
			}
			
			World.setPopulationAvailable(true);

		} catch (NoSuchFileException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Population parseLine(String line) {
		Population pop = new Population();
		pop.setNumbers(Integer.parseInt(line.substring(0, line.indexOf(" "))));
		pop.setRace(line.substring(line.indexOf(" ") + 1));
		return pop;
	}
}
