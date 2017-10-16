package legends;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.Velocity;

import legends.helper.TemplateLoader;
import legends.model.World;

public class Application {
	private static final Log LOG = LogFactory.getLog(Application.class);

	private static Properties applicationProperties = new Properties();
	private static boolean serverMode = false;
	private static String subUri = null;
	private static Integer port = null;

	public static void main(String[] args) throws Exception {
		loadProperties();
		initVelocity();

		parseCommandLine(args);

		initWebServer(58881);
	}

	private static void initVelocity() {
		try {
			Application.class.getClassLoader();
			LogManager.getLogManager().readConfiguration(ClassLoader.getSystemResourceAsStream("logging.properties"));
		} catch (SecurityException | IOException e) {
		}

		Properties velocityProperties = new Properties();

		velocityProperties.setProperty("resource.loader", "classpath");
		velocityProperties.setProperty("classpath.resource.loader.class", TemplateLoader.class.getName());
		velocityProperties.setProperty("userdirective", "legends.helper.Decorate");

		Velocity.init(velocityProperties);
	}

	private static void initWebServer(int port) throws IOException, URISyntaxException {
		WebServer server = new WebServer(port);
		if (!serverMode)
			Desktop.getDesktop().browse(new URI("http://localhost:" + server.getPort()));
	}

	private static void loadProperties() {
		try {
			applicationProperties.load(Files.newBufferedReader(Paths.get("legendsbrowser.properties")));
		} catch (IOException e) {
			System.err.println("properties not found: " + e.getMessage());
		}
	}

	public static String getProperty(String key) {
		return applicationProperties.getProperty(key);
	}

	public static void setProperty(String key, String value) {
		applicationProperties.setProperty(key, value);
		try {
			applicationProperties.store(Files.newBufferedWriter(Paths.get("legendsbrowser.properties")), "");
		} catch (IOException e) {
			System.err.println("properties not saved: " + e.getMessage());
		}
	}

	private static void parseCommandLine(String[] args) {
		Options options = new Options();
		options.addOption("s", "serverMode", false, "run in server mode (disables file chooser)");
		options.addOption("w", "world", true, "path to legends.xml or archive");
		options.addOption("p", "port", true, "use specific port");
		options.addOption("u", "subUri", true, "run on /<subUri>");
    options.addOption("h", "help", false, "display this help and exit");

		HelpFormatter formatter = new HelpFormatter();

		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("help")) {
        formatter.printHelp("legends", options);
        System.exit(0);
      }

			subUri = cmd.getOptionValue("subUri");
			port = cmd.hasOption("port") ? Integer.parseInt(cmd.getOptionValue("port")) : null;
			serverMode = cmd.hasOption("serverMode");

			String world = cmd.getOptionValue("world");
			if (world != null) {
				LOG.info("loading world: " + world);
				World.load(Paths.get(world));
			} else if (serverMode) {
				LOG.warn("you need to specify a world if running in server mode");
				System.exit(0);
			}
    } catch (UnrecognizedOptionException e) {
      LOG.error("Unrecognized exception: " + e.getOption());
      formatter.printHelp("legends", options);
      System.exit(1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static boolean isServerMode() {
		return serverMode;
	}

	public static Integer getPort() {
		return port;
	}
	
	public static boolean hasSubUri() {
		return subUri != null;
	}

	public static String getSubUri() {
		return subUri != null ? subUri : "";
	}

}
