package Business.Entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class League {

    private String name;

    private Date date;

    private Time time;
    private int day;

    private int number_teams;

    private List<Team> teams;
    
    private List<Match> matches;

    private boolean state;

    public League(String name, Date date, Time time,  int day, int number_teams, List<Team> teams, List<Match> matches, boolean state) {
        this.name = name;
        this.date = date;
        this.day = day;
        this.time = time;
        this.number_teams = number_teams;
        this.teams = teams;
        this.matches = matches;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getDay() {
        return day;
    }

    public int getNumber_teams() {
        return number_teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getMatches() {
        return matches;
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

    public void setDay(int day) {
        this.day = day;
    }

    public void setNumber_teams(int number_teams) {
        this.number_teams = number_teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
