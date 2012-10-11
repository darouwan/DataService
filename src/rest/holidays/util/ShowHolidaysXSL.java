package rest.holidays.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ShowHolidaysXSL {
    static public void main(String[] arg) {

	String inXML = "xml/holidays.xml";
	String inXSL = "xml/show.xsl";
	String outTXT = "xml/out.html";

	ShowHolidaysXSL st = new ShowHolidaysXSL();
	try {
	    st.transform(inXML, inXSL, outTXT,"VIC");
	} catch (TransformerConfigurationException e) {
	    System.err.println("Invalid factory configuration");
	    System.err.println(e);
	} catch (TransformerException e) {
	    System.err.println("Error during transformation");
	    System.err.println(e);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    


    public void transform(String inXML, String inXSL, String outTXT,String state)
	    throws TransformerConfigurationException, TransformerException, FileNotFoundException {

	TransformerFactory factory = TransformerFactory.newInstance();

	StreamSource xslStream = new StreamSource(new FileInputStream(inXSL));
	Transformer transformer = factory.newTransformer(xslStream);
	transformer.setErrorListener(new MyErrorListener());

	StreamSource in = new StreamSource(new FileInputStream( inXML));

	StreamResult out = new StreamResult(new FileOutputStream(outTXT));
	transformer.setParameter("short", state);
	transformer.transform(in, out);
	System.out.println("The generated HTML file is:" + outTXT);
    }
}

class MyErrorListener implements ErrorListener {
    public void warning(TransformerException e) throws TransformerException {
	show("Warning", e);
	throw (e);
    }

    public void error(TransformerException e) throws TransformerException {
	show("Error", e);
	throw (e);
    }

    public void fatalError(TransformerException e) throws TransformerException {
	show("Fatal Error", e);
	throw (e);
    }

    private void show(String type, TransformerException e) {
	System.out.println(type + ": " + e.getMessage());
	if (e.getLocationAsString() != null)
	    System.out.println(e.getLocationAsString());
    }
}