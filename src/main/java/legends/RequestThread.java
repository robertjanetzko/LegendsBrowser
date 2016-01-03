package legends;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.Artifact;
import legends.model.Entity;
import legends.model.HistoricalFigure;
import legends.model.Region;
import legends.model.Site;
import legends.model.World;
import legends.model.collections.basic.EventCollection;
import legends.model.events.basic.Event;

public class RequestThread extends Thread {

	private static void sendError(final BufferedOutputStream out, final int code, String message) throws IOException {
		message = message + "<hr>" + PortalServer.VERSION;
		sendHeader(out, code, "text/html", message.length(), System.currentTimeMillis());
		out.write(message.getBytes());
		out.flush();
		out.close();
	}

	private static void sendHeader(final BufferedOutputStream out, final int code, final String contentType,
			final long contentLength, final long lastModified) throws IOException {
		out.write(("HTTP/1.0 " + code + " OK\r\n" + "Date: " + new Date().toString() + "\r\n"
				+ "Server: Legends Browser/1.0\r\n" + "Content-Type: " + contentType + "\r\n"
				+ "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n"
				+ ((contentLength != -1) ? "Content-Length: " + contentLength + "\r\n" : "") + "Last-modified: "
				+ new Date(lastModified).toString() + "\r\n" + "\r\n").getBytes());
	}

	private final Socket _socket;

	public RequestThread(final Socket socket) {
		_socket = socket;
	}

	@Override
	public void run() {
		InputStream reader = null;
		try {
			_socket.setSoTimeout(30000);
			final BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			final BufferedOutputStream out = new BufferedOutputStream(_socket.getOutputStream());

			final String request = in.readLine();
			if (request == null || !request.startsWith("GET ")
					|| !(request.endsWith(" HTTP/1.0") || request.endsWith("HTTP/1.1"))) {
				// Invalid request type (no "GET")
				sendError(out, 500, "Invalid Method.");
				return;
			}
			String path = request.substring(4, request.length() - 9);

			String arguments = "";
			HashMap<String, String> params = new HashMap<>();
			if (path.contains("?")) {
				arguments = path.substring(path.indexOf("?") + 1);
				path = path.substring(0, path.indexOf("?"));

				for (String kv : arguments.split("&")) {
					String[] d = kv.split("=");
					params.put(URLDecoder.decode(d[0], "UTF-8"), URLDecoder.decode(d[1], "UTF-8"));
				}
			}
			path = path.replace("+", "%2B");
			path = URLDecoder.decode(path, "UTF-8");

			File file = new File(path).getCanonicalFile();

			if (file.isDirectory()) {
				// Check to see if there is an index file in the directory.
				final File indexFile = new File(file, "index.html");
				if (indexFile.exists() && !indexFile.isDirectory()) {
					file = indexFile;
				}
			}

			if (path.startsWith("/map")) {
				int id;
				String par = "";
				if (path.contains("/")) {
					try {
						par = path.substring(path.lastIndexOf("/") + 1);
						id = Integer.parseInt(par);
					} catch (NumberFormatException e) {
						id = -1;
					}
				} else {
					id = -1;
				}

				File temp = File.createTempFile("map", ".png");
				BufferedImage image = new BufferedImage(World.getMapImage().getWidth(), World.getMapImage().getHeight(),
						World.getMapImage().getType());
				Graphics2D g = image.createGraphics();
				g.drawImage(World.getMapImage(), 0, 0, null);

//				if (path.startsWith("/map/site/")) {
//					Site site = World.getSite(id);
//					g.setColor(Color.yellow);
//					g.setStroke(new BasicStroke(10));
//					g.drawRect(site.getX() * World.getMapTileWidth(), site.getY() * World.getMapTileHeight(),
//							World.getMapTileWidth(), World.getMapTileHeight());
//				} else if (path.startsWith("/map/entity/")) {
//					Entity entity = World.getEntity(id);
//					for (Site site : entity.getSites()) {
//						g.setColor(Color.yellow);
//						g.setStroke(new BasicStroke(10));
//						g.drawRect(site.getX() * World.getMapTileWidth(), site.getY() * World.getMapTileHeight(),
//								World.getMapTileWidth(), World.getMapTileHeight());
//					}
//				} else {
//					g.setStroke(new BasicStroke(10));
//					for (Entity entity : World.getMainCivilizations()) {
//						switch (entity.getRace()) {
//						case "Goblins":
//							g.setColor(Color.red);
//							break;
//						case "Elves":
//							g.setColor(Color.green);
//							break;
//						case "Dwarves":
//							g.setColor(Color.yellow);
//							break;
//						case "Humans":
//							g.setColor(Color.blue);
//							break;
//
//						default:
//							g.setColor(new Color(200,0,200));
//							break;
//						}
//						for (Site site : entity.getSites()) {
//							g.drawRect(site.getX() * World.getMapTileWidth(), site.getY() * World.getMapTileHeight(),
//									World.getMapTileWidth(), World.getMapTileHeight());
//						}
//
//					}
//				}
				
				int size = 400;
				if(params.containsKey("size")) {
					size = Integer.parseInt(params.get("size"));
				}

				BufferedImage output = new BufferedImage(size, size, World.getMapImage().getType());
				g = output.createGraphics();
				g.drawImage(image.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);

				ImageIO.write(output, "png", temp);
				writeFile(out, temp, "image/png");

			} else if (!path.startsWith("/resources")) {
				try {
					StringWriter sw = new StringWriter();

					Template template;
					VelocityContext context = new VelocityContext();
					context.put("world", World.class);
					context.put("Event", EventHelper.class);

					int id;
					String par = "";
					if (path.contains("/")) {
						try {
							par = path.substring(path.lastIndexOf("/") + 1);
							id = Integer.parseInt(par);
						} catch (NumberFormatException e) {
							id = -1;
						}
					} else {
						id = -1;
					}

					final int pid = id;
					final String param = par;

					if (path.startsWith("/hf/")) {
						HistoricalFigure hf = World.getHistoricalFigure(pid);
						template = Velocity.getTemplate("hf.vm");
						context.put("hf", hf);
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> EventHelper.related(hf, e)).collect(Collectors.toList()));

					} else if (path.startsWith("/artifact/")) {
						Artifact a = World.getArtifact(pid);
						template = Velocity.getTemplate("artifact.vm");
						context.put("artifact", a);
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> EventHelper.related(a, e)).collect(Collectors.toList()));

					} else if (path.startsWith("/entity/")) {
						Entity en = World.getEntity(pid);
						template = Velocity.getTemplate("entity.vm");
						context.put("entity", en);
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> EventHelper.related(en, e)).collect(Collectors.toList()));

					} else if (path.startsWith("/site/")) {
						Site s = World.getSite(pid);
						template = Velocity.getTemplate("site.vm");
						context.put("site", s);
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> EventHelper.related(s, e)).collect(Collectors.toList()));

					} else if (path.startsWith("/region/")) {
						Region r = World.getRegion(pid);
						template = Velocity.getTemplate("region.vm");
						context.put("region", r);
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> EventHelper.related(r, e)).collect(Collectors.toList()));

					} else if (path.startsWith("/year/")) {
						template = Velocity.getTemplate("year.vm");
						context.put("year", pid);
						context.put("events", World.getHistoricalEvents().stream().filter(e -> e.getYear() == pid)
								.collect(Collectors.toList()));
						context.put("types", World.getHistoricalEvents().stream().map(Event::getType).distinct()
								.collect(Collectors.toList()));

					} else if (path.startsWith("/type/")) {
						template = Velocity.getTemplate("type.vm");
						context.put("events", World.getHistoricalEvents().stream()
								.filter(e -> e.getType().equals(param)).collect(Collectors.toList()));
						context.put("types", World.getHistoricalEvents().stream().map(Event::getType).distinct()
								.collect(Collectors.toList()));

					} else if (path.startsWith("/collections")) {
						template = Velocity.getTemplate("collections.vm");
						context.put("events",
								World.getHistoricalEventCollections().stream().collect(Collectors.toList()));
						context.put("types", World.getHistoricalEventCollections().stream()
								.map(EventCollection::getType).distinct().collect(Collectors.toList()));

					} else if (path.startsWith("/collection/")) {
						template = Velocity.getTemplate("collections.vm");
						context.put("events", Arrays.asList(World.getHistoricalEventCollection(pid)));
						context.put("types", World.getHistoricalEventCollections().stream()
								.map(EventCollection::getType).distinct().collect(Collectors.toList()));

					} else if (path.startsWith("/ctype/")) {
						template = Velocity.getTemplate("collections.vm");
						context.put("events", World.getHistoricalEventCollections().stream()
								.filter(e -> e.getType().equals(param)).collect(Collectors.toList()));
						context.put("types", World.getHistoricalEventCollections().stream()
								.map(EventCollection::getType).distinct().collect(Collectors.toList()));

					} else if (path.startsWith("/search")) {
						if (params.containsKey("query")) {
							String query = params.get("query").toLowerCase();

							template = Velocity.getTemplate("search.vm");
							context.put("query", query);

							context.put("regions",
									World.getRegions().stream().filter(e -> e.getName().toLowerCase().contains(query))
											.collect(Collectors.toList()));
							context.put("sites",
									World.getSites().stream().filter(e -> e.getName().toLowerCase().contains(query))
											.collect(Collectors.toList()));
							context.put("artifacts",
									World.getArtifacts().stream().filter(e -> e.getName().toLowerCase().contains(query))
											.collect(Collectors.toList()));
							context.put("entities",
									World.getEntities().stream().filter(e -> e.getName().toLowerCase().contains(query))
											.collect(Collectors.toList()));
							context.put("hfs",
									World.getHistoricalFigures().stream()
											.filter(e -> e.getName().toLowerCase().contains(query))
											.collect(Collectors.toList()));

						} else
							template = Velocity.getTemplate("index.vm");

					} else {
						context.put("entityMap",
								World.getMainCivilizations().stream().collect(Collectors.groupingBy(Entity::getRace)));

						template = Velocity.getTemplate("index.vm");
					}

					template.merge(context, sw);
					String content = sw.toString();

					sendHeader(out, 200, "text/html", content.length(), file.lastModified());
					out.write(content.getBytes());
				} catch (Exception e) {
					e.printStackTrace();
					String content = e.getMessage();
					sendHeader(out, 200, "text/html", content.length(), file.lastModified());
					out.write(content.getBytes());
				}
			} else {
				String contentType = PortalServer.MIME_TYPES.get(path.substring(path.lastIndexOf(".")).toLowerCase());
				if (contentType == null) {
					contentType = "application/octet-stream";
				}

				try {
					final URLConnection c = ClassLoader.getSystemResource("portal/" + path.substring(1))
							.openConnection();
					sendHeader(out, 200, contentType, c.getContentLength(), c.getLastModified());
					reader = new BufferedInputStream(c.getInputStream());

					final byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = reader.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					reader.close();
				} catch (final Exception e) {
					System.err.println(path);
					e.printStackTrace();
				}
			}

			out.flush();
			out.close();
		} catch (final IOException e) {
			if (reader != null) {
				try {
					reader.close();
				} catch (final Exception anye) {
				}
			}
		}
	}

	private void writeFile(BufferedOutputStream out, File file, String contentType)
			throws MalformedURLException, IOException {
		final URLConnection c = file.toURI().toURL().openConnection();
		sendHeader(out, 200, contentType, c.getContentLength(), c.getLastModified());
		BufferedInputStream reader = new BufferedInputStream(c.getInputStream());

		final byte[] buffer = new byte[640000];
		int bytesRead;
		while ((bytesRead = reader.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		reader.close();
	}

}