package legends;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import legends.helper.EventHelper;
import legends.model.Entity;
import legends.model.HistoricalFigure;
import legends.model.Leader;
import legends.model.World;

public class HistoryReader {
	enum State {
		RACES, CIV, LIST, WORSHIPS, LEADERS;
	}

	public static void read(Path path) {
		try (BufferedReader fr = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {

			String worldName = fr.readLine();
			worldName += ", " + fr.readLine();

			World.setName(worldName);

			State state = State.RACES;

			String line = "";
			String position = "";

			Entity entity = null;
			Leader leader = null;

			while (true) {
				if (line == null)
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

					entity = World.getEntities().stream().filter(e -> e.getName().equals(civName)).findFirst()
							.orElse(null);
					if (entity != null) {
						entity.setRace(civRace);
						if (entity.getType().equals("unknown"))
							entity.setType("civilization");
					} else
						System.out.println("unknown civilization: " + civName);

					line = fr.readLine();
					if (line == null)
						return;
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
					if (line == null)
						return;
					if (line.contains(", deity: ") || line.contains(" force:")) {
					} else if (line.endsWith(" List")) {
						state = State.LIST;
					} else
						state = State.CIV;
					break;

				case LEADERS:
					line = fr.readLine();
					if (line == null)
						return;
					if (line.startsWith("  [*] ")) {
						String leaderName = line.substring(6, line.indexOf(" (b."));
						leaderName = EventHelper.ascii(leaderName);
						HistoricalFigure hf = World.getHistoricalFigure(leaderName);

						if (hf == null) {
							System.out.println("unknown hf: " + leaderName);
							for (byte b : leaderName.getBytes("ISO-8859-1"))
								System.out.print((char) b + " " + Integer.toHexString(b) + " ");
							System.out.println();
						} else {

							int beganInd = line.indexOf("Reign Began: ");
							int from = Integer.parseInt(
									line.substring(beganInd + "Reign Began: ".length(), line.indexOf("), ", beganInd)));

							if (leader != null) {
								leader.setTill(from);
							}

							leader = new Leader();
							leader.setPosition(position);
							leader.setFrom(from);
							leader.setHf(hf);
							hf.setLeader(true);

							entity.getLeaders().add(leader);
						}

					} else if (line.startsWith("      ")) {

					} else if (line.endsWith(" List")) {
						state = State.LIST;
					} else
						state = State.CIV;
					break;

				}

			}
		} catch (NoSuchFileException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
