package legends;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebServer extends Thread {
	private static final Log LOG = LogFactory.getLog(WebServer.class);

	public static final String VERSION = "Legends Browser";
	public static final Hashtable<String, String> MIME_TYPES = new Hashtable<String, String>();

	static {
		final String image = "image/";
		MIME_TYPES.put(".gif", image + "gif");
		MIME_TYPES.put(".jpg", image + "jpeg");
		MIME_TYPES.put(".jpeg", image + "jpeg");
		MIME_TYPES.put(".png", image + "png");
		MIME_TYPES.put(".bmp", image + "bmp");
		final String text = "text/";
		MIME_TYPES.put(".html", text + "html");
		MIME_TYPES.put(".htm", text + "html");
		MIME_TYPES.put(".txt", text + "plain");
		MIME_TYPES.put(".css", text + "css");
	}

	private static WebServer server;

	public static String getAddress() {
		return "http://localhost:" + server.port;
	}

	// Work out the filename extension. If there isn't one, we keep
	// it as the empty string ("").
	public static String getExtension(final java.io.File file) {
		String extension = "";
		final String filename = file.getName();
		final int dotPos = filename.lastIndexOf(".");
		if (dotPos >= 0) {
			extension = filename.substring(dotPos);
		}
		return extension.toLowerCase();
	}

	public static void init() {
		if (server == null) {
			try {
				server = new WebServer(58881);
			} catch (final IOException e) {
				LOG.error("error starting webserver", e);
			}
		}
	}

	private int port;

	private ServerSocket _serverSocket;

	private final boolean _running = true;

	public WebServer(final int port) throws IOException {
		this.port = port;
		
		if(Application.getPort() != null)
			this.port = Application.getPort();
		
		int trycount = 0;
		String msg = "JVM_Bind";
		while ((msg.contains("JVM_Bind") || _serverSocket == null) && trycount < 10) {
			msg = "";
			trycount++;
			try {
				_serverSocket = new ServerSocket(this.port);
			} catch (final SocketException e) {
				LOG.error("cannot start portal server on port " + this.port + ": " + e.getMessage());
				msg = e.getMessage();
				if (trycount == 10 || Application.getPort() != null) {
					LOG.error("stopping portal server");
					System.exit(0);
					return;
				} else {
					this.port++;
				}
			}
		}
		LOG.info("server started on localhost:" + this.port);
		start();
	}

	public int getPort() {
		return port;
	}

	@Override
	public void run() {
		while (_running) {
			try {
				final Socket socket = _serverSocket.accept();
				final RequestThread requestThread = new RequestThread(socket, port);
				requestThread.start();
			} catch (final IOException e) {
				System.exit(1);
			}
		}
	}

}