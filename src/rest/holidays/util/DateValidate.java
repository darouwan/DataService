package rest.holidays.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import net.sf.saxon.Configuration;
import net.sf.saxon.dom.DocumentWrapper;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DateValidate {

    public static void select(String state) throws FileNotFoundException {
	//
	//String state = "SA";

	String fileString = "xml/holidays.xml";
	//
	String query = "<HOLIDAYLIST>{for $s in /HOLIDAYLIST/STATE[@value=\""
		+ state + "\"] return $s} </HOLIDAYLIST>";
	//
	Document document = getDocument(fileString);
	Configuration configuration = new Configuration();
	StaticQueryContext context = new StaticQueryContext(configuration,
		false);
	//
	XQueryExpression expression = null;
	try {
	    expression = context.compileQuery(query);
	    DynamicQueryContext context2 = new DynamicQueryContext(
		    configuration);
	    context2.setContextItem(new DocumentWrapper(document, null,
		    configuration));

	    final Properties props = new Properties();
	    props.setProperty(OutputKeys.METHOD, "xml");
	    props.setProperty(OutputKeys.INDENT, "yes");
	    //
	    System.setOut(new PrintStream(new BufferedOutputStream(
		    new FileOutputStream("xml/out.xml"))));

	    expression.run(context2, new StreamResult(System.out), props);

	} catch (XPathException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    public static Document getDocument(String xml) {
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder builder;
	Document document = null;
	try {
	    builder = builderFactory.newDocumentBuilder();
	    document = builder.parse(xml);
	} catch (ParserConfigurationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	document.normalize();
	return document;
    }

    public static void main(String[] args) {
	try {
	    select("NSW");
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
