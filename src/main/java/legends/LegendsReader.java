package legends;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import legends.xml.WorldContentHandler;
import legends.xml.handlers.XMLContentHandler;

public class LegendsReader {
	public static void read(Path path) throws SAXException, IOException {
		System.out.println("load legends: "+path);
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		XMLContentHandler contentHandler = new XMLContentHandler("", xmlReader);
		WorldContentHandler worldContentHandler = new WorldContentHandler("df_world", xmlReader);
		contentHandler.registerContentHandler(worldContentHandler);
		xmlReader.setContentHandler(contentHandler);

		InputSource inputSource = new InputSource(Files.newBufferedReader(path, Charset.forName("ISO-8859-1")));
		xmlReader.parse(inputSource);
	}
}
