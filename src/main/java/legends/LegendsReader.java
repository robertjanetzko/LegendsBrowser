package legends;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import legends.model.LegendsXml;
import legends.xml.handlers.AnnotationContentHandler;

public class LegendsReader {
	private static final Log LOG = LogFactory.getLog(LegendsReader.class);

	public static void read(Path path, Charset cs) throws SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LOG.info("load legends: " + path);
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		AnnotationContentHandler contentHandler = new AnnotationContentHandler(LegendsXml.class);
		contentHandler.setXmlReader(xmlReader);
		xmlReader.setContentHandler(contentHandler);

		CharsetDecoder decoder = cs.newDecoder();
		decoder.onMalformedInput(CodingErrorAction.IGNORE);

		BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path), decoder));
		PipedWriter writer = new PipedWriter();
		PipedReader fixedReader = new PipedReader(writer);

		new Thread() {
			public void run() {
				try {
					String line = null;
					while ((line = reader.readLine()) != null) {
						if (line.contains("conspirator_hfid")) {
							line = line.replaceAll("<conspirator_hfid>(\\d+)<\\/conspirator>",
									"<conspirator_hfid>$1</conspirator_hfid>");
						}
						if (line.contains("interrogator_hfid")) {
							line = line.replaceAll("<interrogator_hfid>(\\d+)<\\/convicted_hfid>",
									"<interrogator_hfid>$1</interrogator_hfid>");
						}
						writer.write(line);
						writer.write("\r\n");
					}
					writer.close();
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();

		InputSource inputSource = new InputSource(fixedReader);
		xmlReader.parse(inputSource);

		contentHandler.printMappedValues();
	}
}
