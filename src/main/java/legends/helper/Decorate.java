package legends.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Parse;
import org.apache.velocity.runtime.parser.node.Node;

public class Decorate extends Parse {
	public String getName() {
		return "decorate";
	}

	public int getType() {
		return BLOCK;
	}

	public boolean render(InternalContextAdapter context, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		StringWriter sw = new StringWriter();
		node.jjtGetChild(1).render(context, sw);
		// store the contents against the variable
		context.put("body_content", sw.toString());
		return super.render(context, writer, node);
	}
}