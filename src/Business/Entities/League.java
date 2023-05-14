package Business.Entities;

import java.sql.Time;
import java.util.Date;

public class League {

    private String name;
    private Date date;
    private Time hour;
    private int day;
    private int number_teams;




    private int state;

    public League(String name, Date date, Time hour, int day, int number_teams, int state) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.day = day;
        this.number_teams = number_teams;
        this.state = state;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNumber_teams() {
        return number_teams;
    }

    public void setNumber_teams(int number_teams) {
        this.number_teams = number_teams;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
