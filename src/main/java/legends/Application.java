package legends;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException {
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

}
