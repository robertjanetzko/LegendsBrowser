package legends;

import java.awt.Desktop;
import java.net.URI;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Application {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			
			props.setProperty("resource.loader", "classpath");
			props.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			props.setProperty("userdirective", "legends.helper.Decorate");
		
			Velocity.init(props);
			PortalServer server = new PortalServer(58881);
			
			Desktop.getDesktop().browse(new URI("http://localhost:"+server.getPort()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
