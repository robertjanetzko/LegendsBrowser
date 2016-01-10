package legends;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Application {
	private static Properties applicationProperties = new Properties();

	public static void main(String[] args) throws IOException, URISyntaxException {
		loadProperties();
		initVelocity();
		initWebServer(58881);
	}

	private static void initVelocity() {
		Properties velocityProperties = new Properties();

		velocityProperties.setProperty("resource.loader", "classpath");
		velocityProperties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
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
		System.out.println(key+ "= "+value);
		applicationProperties.setProperty(key, value);
		try {
			applicationProperties.store(Files.newBufferedWriter(Paths.get("legendsbrowser.properties")), "");
		} catch (IOException e) {
			System.err.println("properties not saved: "+e.getMessage());
		}
	}

}
