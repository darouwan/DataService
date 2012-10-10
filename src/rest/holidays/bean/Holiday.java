package rest.holidays.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Holiday {
    private String title = "";
    private ArrayList<Date> date = new ArrayList<Date>();



    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public ArrayList<Date> getDate() {
        return date;
    }

    public void setDate(ArrayList<Date> date) {
        this.date = date;
    }



}
