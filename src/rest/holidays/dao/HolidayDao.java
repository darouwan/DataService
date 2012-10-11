package rest.holidays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rest.holidays.bean.Date;
import rest.holidays.bean.Holiday;

public class HolidayDao {
    String basePath="D:/workspace/DataService/";
    public List<Holiday> getHolidayByState(String state) {

	XQDataSource xqds = new SaxonXQDataSource();
	List<Holiday> holidays = new ArrayList<Holiday>();
	
	XQConnection con;
	try {
	    con = xqds.getConnection();
	    String queryString = "for $s in doc(\""+basePath+"xml/holidays.xml\")/HOLIDAYLIST/STATE[@value=\""
		    + state + "\"]/HOLIDAY return $s";
	    XQPreparedExpression exp = con.prepareExpression(queryString);
	    XQResultSequence result = exp.executeQuery();
	    // System.out.println(result.count());

	    while (result.next()) {
		Node node = result.getNode();
		NodeList nl = node.getChildNodes();
		Holiday holiday = new Holiday();
		for (int i = 0; i < nl.getLength(); i++) {

		    // System.out.println(nl.item(i).getNodeName());
		    Node childNode = nl.item(i);
		    if (childNode.getNodeName().equals("TITLE")) {
			holiday.setTitle(childNode.getTextContent());
			System.out.println(childNode.getTextContent());
		    } else if (childNode.getNodeName().equals("YEAR")) {
			Date date = new Date();
			System.out.println(childNode.getAttributes()
				.getNamedItem("value").getTextContent());
			date.setYear(childNode.getAttributes()
				.getNamedItem("value").getTextContent());
			NodeList nl2 = childNode.getChildNodes();
			System.out.println(nl2.item(0).getTextContent());

			date.setMonth(nl2.item(0).getTextContent());

			System.out.println(nl2.item(1).getTextContent());

			date.setDay(nl2.item(1).getTextContent());

			System.out.println(nl2.item(2).getTextContent());

			date.setWeekday(nl2.item(2).getTextContent());

			holiday.getDate().add(date);
		    }

		}
		holidays.add(holiday);
	    }
	} catch (XQException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return holidays;
    }

    public String validateDay(String state, String day, String month,
	    String year) {
	XQDataSource xqds = new SaxonXQDataSource();
	String text = null;
	XQConnection con;
	String queryString = "for $s in doc('"+basePath+"xml/holidays.xml')/HOLIDAYLIST/STATE[@value='"
		+ state
		+ "']/HOLIDAY where  $s/YEAR/MONTH='"
		+ month
		+ "' and $s/YEAR/DAY='"
		+ day
		+ "' and $s/YEAR/@value='"
		+ year
		+ "' return $s/TITLE";
	try {
	    con = xqds.getConnection();
	    XQPreparedExpression exp = con.prepareExpression(queryString);
	    XQResultSequence result = exp.executeQuery();

	    while (result.next()) {
		Node node = result.getNode();
		text = node.getTextContent();

	    }
	} catch (XQException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println(text);
	return text;
    }

    public static void main(String[] args) {
	HolidayDao hd = new HolidayDao();
	try {
	    // hd.getByState("NSW");
	    hd.validateDay("NSW", "1", "January", "2012");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
