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

}
