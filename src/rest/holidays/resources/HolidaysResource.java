package rest.holidays.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import rest.holidays.bean.Holiday;
import rest.holidays.dao.HolidayDao;
import rest.holidays.util.ShowHolidaysXSL;

@Path("/")
public class HolidaysResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Path("{state}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Holiday> getStateHolidays(@PathParam("state") String state) {
	List<Holiday> holidays;
	HolidayDao holidayDao = new HolidayDao();
	holidays = holidayDao.getHolidayByState(state);

	return holidays;

    }

    @GET
    @Path("{state}/show")
    @Produces({ MediaType.TEXT_HTML })
    public String getStateHolidaysInHTML(@PathParam("state") String state) {
	ShowHolidaysXSL showHolidays = new ShowHolidaysXSL();
	String basePath = "D:/workspace/DataService/";
	String html = "";
	String inXML = basePath + "xml/holidays.xml";
	String inXSL = basePath + "xml/show.xsl";
	String outTXT = basePath + "xml/out.html";
	try {
	    showHolidays.transform(inXML, inXSL, outTXT, state);
	    File file = new File(basePath + "xml/out.html");
	    BufferedReader reader = null;

	    reader = new BufferedReader(new FileReader(file));
	    String tempString = null;

	    while ((tempString = reader.readLine()) != null) {

		html = html + tempString;

	    }
	    reader.close();
	} catch (TransformerConfigurationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return html;

    }

    @GET
    @Path("{state}/{date}")
    public String getHolidayBydate(@PathParam("state") String state,
	    @PathParam("date") String date) {
	String result = null;
	HolidayDao holidayDao = new HolidayDao();
	String[] dateItems = date.split("-");
	String year = dateItems[2];
	String month = dateItems[1];
	String day = Integer.parseInt(dateItems[0]) + "";

	if (month.equals("01")) {
	    month = "January";
	} else if (month.equals("02")) {
	    month = "February";
	} else if (month.equals("03")) {
	    month = "March";
	} else if (month.equals("04")) {
	    month = "April";
	} else if (month.equals("05")) {
	    month = "May";
	} else if (month.equals("06")) {
	    month = "June";
	} else if (month.equals("07")) {
	    month = "July";
	} else if (month.equals("08")) {
	    month = "August";
	} else if (month.equals("09")) {
	    month = "September";
	} else if (month.equals("10")) {
	    month = "October";
	} else if (month.equals("11")) {
	    month = "November";
	} else if (month.equals("12")) {
	    month = "December";
	}

	System.out.println(state + " " + day + " " + month + " " + year);
	result = holidayDao.validateDay(state, day, month, year);
	if (result == null) {
	    result = "This is not any holiday";
	}
	System.out.println("Result = " + result);
	return result;
    }

}
