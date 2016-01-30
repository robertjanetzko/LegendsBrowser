package legends;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.velocity.app.Velocity;

import legends.helper.TemplateLoader;

public class Application {
	private static Properties applicationProperties = new Properties();

	public static void main(String[] args) throws Exception {
		loadProperties();
		initVelocity();
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
		Desktop.getDesktop().browse(new URI("http://localhost:" + server.getPort()));
	}

	private static void loadProperties() {
		try {
			applicationProperties.load(Files.newBufferedReader(Paths.get("legendsbrowser.properties")));
		} catch (IOException e) {
			System.err.println("properties not found: "+e.getMessage());
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
			System.err.println("properties not saved: "+e.getMessage());
		}
	}

}
