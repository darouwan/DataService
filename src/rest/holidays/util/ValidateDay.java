package rest.holidays.util;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ValidateDay {
    static public void main(String[] arg) {

	String inXML = "xml/holidays.xml";
	String inXSL = "xml/validate.xsl";
	String outTXT = "xml/out1.xml";

	ShowHolidays st = new ShowHolidays();
	try {
	    st.transform(inXML, inXSL, outTXT);
	} catch (TransformerConfigurationException e) {
	    System.err.println("Invalid factory configuration");
	    System.err.println(e);
	} catch (TransformerException e) {
	    System.err.println("Error during transformation");
	    System.err.println(e);
	}
    }

    public void transform(String inXML, String inXSL, String outTXT)
	    throws TransformerConfigurationException, TransformerException {

	TransformerFactory factory = TransformerFactory.newInstance();

	StreamSource xslStream = new StreamSource(inXSL);
	Transformer transformer = factory.newTransformer(xslStream);
	transformer.setErrorListener(new MyErrorListener());

	StreamSource in = new StreamSource(inXML);

	StreamResult out = new StreamResult(outTXT);
	
	transformer.setParameter("state", "NSW");
	transformer.setParameter("month", "January");
	transformer.setParameter("day", "1");
	transformer.setParameter("year", "2012");

	transformer.transform(in, out);
	System.out.println("The generated HTML file is:" + outTXT);
    }
}
