package legends;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import legends.model.Entity;
import legends.model.HistoricalFigure;
import legends.model.Leader;
import legends.model.World;

public class HistoryReader {
	enum State {
		RACES, CIV, LIST, WORSHIPS, LEADERS;
	}

	public static void read(String fileName) {
		try (BufferedReader fr = Files.newBufferedReader(Paths.get(fileName), Charset.forName("ISO-8859-1"))) {

			String worldName = fr.readLine();
			worldName += ", " + fr.readLine();

			World.setName(worldName);

			State state = State.RACES;

			String line = "";
			String name, race, position = "", listType;
			
			Entity entity = null;
			Leader leader = null;
			
			while (true) {
				if(line == null)
					break;
				
				switch (state) {
				case RACES:
					line = fr.readLine();
					if (!line.contains(", "))
						continue;
					state = State.CIV;
					break;
					
				case CIV:
					final String civName = line.substring(0, line.indexOf(", "));
					final String civRace = line.substring(line.indexOf(", ") + 2);
					
					entity = World.getEntities().stream().filter(e -> e.getName().equals(civName)).findFirst().orElse(null);
					entity.setRace(civRace);

					line = fr.readLine();
					if (line.endsWith(" List"))
						state = State.LIST;
					
					break;
					
				case LIST:
					leader = null;
					if (line.equals(" Worship List"))
						state = State.WORSHIPS;
					else if (line.endsWith(" List")) {
						position = line.substring(1, line.length() - 5);
						state = State.LEADERS;
					} else {
						System.out.println("unknown state");
					}
					break;

				case WORSHIPS:
					line = fr.readLine();
					if(line == null)
						return;
					if (line.contains(", deity: ") || line.contains(" force:")) {
					} else if (line.endsWith(" List")) {
						state = State.LIST;
					} else
						state = State.CIV;
					break;

				case LEADERS:
					line = fr.readLine();
					if(line == null)
						return;
					if (line.startsWith("  [*] ")) {
						String leaderName = line.substring(6,line.indexOf(" (b."));
						HistoricalFigure hf = World.getHistoricalFigures().stream().filter(e-> e.getName().toLowerCase().equals(leaderName.toLowerCase())).findFirst().orElse(null);
						if(hf == null)
							System.out.println("unknown hf: "+leaderName);

						int beganInd = line.indexOf("Reign Began: ");
						int from = Integer.parseInt(line.substring(beganInd+"Reign Began: ".length(), line.indexOf("), ",beganInd)));
						
						if(leader != null) {
							leader.setTill(from);
						}

						leader = new Leader();
						leader.setPosition(position);
						leader.setFrom(from);
						leader.setHf(hf);				
						
						entity.getLeaders().add(leader);
						
						
					} else if (line.startsWith("      ")) {

					} else if (line.endsWith(" List")) {
						state = State.LIST;
					} else
						state = State.CIV;
					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
