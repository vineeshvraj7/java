/*
 * Code for reference
 */
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

/**
 * @author vineeshvraj7@gmail.com
 *
 */
public class XMLFormatter {
	/**
	 * @param argv
	 * @throws Exception
	 */
	public static void main(String[] argv) throws Exception {
		String unformattedXml = ""+
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?><breakfast_menu><food>    <name>Belgian Waffles</name> "+
		"		<price>$5.95</price>    <description>   Two of our famous Belgian Waffles with plenty of real maple syrup   "+
		"		</description>    <calories>650</calories></food><food>    <name>Strawberry Belgian Waffles</name>    "+
		"		<price>$7.95</price>    <description>    Light Belgian waffles covered with strawberries and whipped cream  "+  
		"		</description>    <calories>900</calories></food><food>    <name>Berry-Berry Belgian Waffles</name>    <price>$8.95</price>"+
		"		    <description>    Belgian waffles covered with assorted fresh berries and whipped cream"+
		"		    </description>    <calories>900</calories></food></breakfast_menu>";
				    
		XMLFormatter.prettyFormat(unformattedXml);
	}

	public static void prettyFormat(String xmlString) {
		xmlString = xmlString != null ? xmlString.trim() : "";
		try {
			InputSource source = new InputSource(new StringReader(xmlString));
			Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source).getDocumentElement();
			Boolean keepDeclaration = Boolean.valueOf(xmlString.startsWith("<?xml"));
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("LS");
			LSSerializer serializer = implementation.createLSSerializer();
			serializer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			serializer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
			System.out.println(serializer.writeToString(document));
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}