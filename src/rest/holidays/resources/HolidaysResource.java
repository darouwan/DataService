package rest.holidays.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.holidays.bean.Holiday;
import rest.holidays.dao.HolidayDao;

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
    public List<Holiday> getStateHolidaysInHTML(@PathParam("state") String state) {
	List<Holiday> holidays;
	HolidayDao holidayDao = new HolidayDao();
	holidays = holidayDao.getHolidayByState(state);
	
	return holidays;

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
	if(result==null){
	    result = "This is not any holiday";
	}
	System.out.println("Result = " + result);
	return result;
    }

}
