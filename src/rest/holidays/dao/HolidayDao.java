package rest.holidays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rest.holidays.bean.Holiday;

public class HolidayDao {

    public List<Holiday> viewXML(String state) throws Exception {

	XQDataSource xqds = new SaxonXQDataSource();
	List<Holiday> holidays = new ArrayList<Holiday>();

	XQConnection con = xqds.getConnection();
	String queryString = "for $s in doc(\"D:/workspace/DataService/xml/holidays.xml\")/HOLIDAYLIST/STATE[@value=\""
		+ state + "\"]/HOLIDAY return $s";
	XQPreparedExpression exp = con.prepareExpression(queryString);
	XQResultSequence result = exp.executeQuery();
	// System.out.println(result.count());

	while (result.next()) {
	    Node node = result.getNode();
	    NodeList nl = node.getChildNodes();
	    for (int i = 0; i < nl.getLength(); i++) {
		Holiday holiday = new Holiday();
		// System.out.println(nl.item(i).getNodeName());
		Node childNode = nl.item(i);
		if (childNode.getNodeName().equals("TITLE")) {
		    holiday.setTitle(childNode.getTextContent());
		    System.out.println(childNode.getTextContent());
		} else if (childNode.getNodeName().equals("YEAR")) {

		    System.out.println(childNode.getAttributes()
			    .getNamedItem("value").getTextContent());
		    holiday.setYear(childNode.getAttributes()
			    .getNamedItem("value").getTextContent());
		    NodeList nl2 = childNode.getChildNodes();
		    System.out.println(nl2.item(0).getTextContent());

		    holiday.setMonth(nl2.item(0).getTextContent());

		    System.out.println(nl2.item(1).getTextContent());

		    holiday.setDay(nl2.item(1).getTextContent());

		    System.out.println(nl2.item(2).getTextContent());

		    holiday.setWeekday(nl2.item(2).getTextContent());

		    // holiday.getDate().add(date);
		}
		holidays.add(holiday);
	    }

	}
	return holidays;
    }

    public List<Holiday> getHolidayByState(String state) {
	// TODO Auto-generated method stub
	try {
	    return this.viewXML(state);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public static void main(String[] args) {
	HolidayDao hd = new HolidayDao();
	try {
	    hd.viewXML("NSW");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
