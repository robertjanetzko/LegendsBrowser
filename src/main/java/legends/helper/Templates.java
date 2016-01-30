package legends.helper;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

public class Templates {
	private Templates() {
	}

	public static Template get(String path) {
		return Velocity.getTemplate(path);
	}
}
