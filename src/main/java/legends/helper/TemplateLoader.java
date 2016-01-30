package legends.helper;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TemplateLoader extends ClasspathResourceLoader {
	@Override
	public InputStream getResourceStream(String name) throws ResourceNotFoundException {
		return super.getResourceStream("templates/" + name);
	}
}
