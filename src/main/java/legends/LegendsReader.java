package legends;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Path;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import legends.model.LegendsXml;
import legends.xml.handlers.AnnotationContentHandler;

public class LegendsReader {
	public static void read(Path path, Charset cs) throws SAXException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("load legends: "+path);
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		AnnotationContentHandler contentHandler = new AnnotationContentHandler(LegendsXml.class);
		contentHandler.setXmlReader(xmlReader);
		xmlReader.setContentHandler(contentHandler);
		
		CharsetDecoder decoder = cs.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        
		InputSource inputSource = new InputSource(new BufferedReader( new InputStreamReader(Files.newInputStream(path), decoder)));
		xmlReader.parse(inputSource);
	}
}
