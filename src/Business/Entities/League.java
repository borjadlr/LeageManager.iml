package Business.Entities;

import java.util.Date;

public class League {

    private String name;
    private Date date;
    private Date hour;

    private Date matchDay;
    private int number_teams;
    private boolean state;

    public League(String name, Date date, Date hour, Date matchDay, int number_teams, boolean state) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.matchDay = matchDay;
        this.number_teams = number_teams;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Date getHour() {
        return hour;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public int getNumber_teams() {
        return number_teams;
    }

    public boolean isState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public void setDay(Date day) {
        this.matchDay = matchDay;
    }

    public void setNumber_teams(int number_teams) {
        this.number_teams = number_teams;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
