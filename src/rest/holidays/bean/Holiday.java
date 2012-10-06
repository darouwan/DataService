package rest.holidays.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Holiday {
    private String title;
    private String day;
    private String month;
    private String year;
    private Weekday weekday;

    enum Weekday {
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDay() {
	return day;
    }

    public void setDay(String day) {
	this.day = day;
    }

    public String getMonth() {
	return month;
    }

    public void setMonth(String month) {
	this.month = month;
    }

    public String getYear() {
	return year;
    }

    public void setYear(String year) {
	this.year = year;
    }

    public Weekday getWeekday() {
	return weekday;
    }

    public void setWeekday(Weekday weekday) {
	this.weekday = weekday;
    };
}
