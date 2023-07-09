package Business.Entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The `League` class represents a league in a sports context.
 */
public class League {

    private String name;
    private Date date;
    private Time time;
    private int day;
    private int number_teams;
    private List<Team> teams;
    private List<Match> matches;
    private boolean state;

    /**
     * Constructs a new `League` object with the specified attributes.
     * @param name         The name of the league.
     * @param date         The date of the league.
     * @param time         The time of the league.
     * @param day          The day of the league.
     * @param number_teams The number of teams participating in the league.
     * @param teams        The list of teams participating in the league.
     * @param matches      The list of matches scheduled for the league.
     * @param state        The state of the league.
     */
    public League(String name, Date date, Time time, int day, int number_teams, List<Team> teams, List<Match> matches, boolean state) {
        this.name = name;
        this.date = date;
        this.day = day;
        this.time = time;
        this.number_teams = number_teams;
        this.teams = teams;
        this.matches = matches;
        this.state = state;
    }

    /**
     * Constructs a new empty `League` object.
     */
    public League() {
    }

    /**
     * Sets the date of the league.
     * @param date The date of the league.
     */
    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    /**
     * Returns the name of the league.
     * @return The name of the league.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of the league.
     * @return The date of the league.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the time of the league.
     * @return The time of the league.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the time of the league.
     * @param time of the league.
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * Returns the day of the league.
     * @return The day of the league.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the number of teams participating in the league.
     * @return The number of teams participating in the league.
     */
    public int getNumber_teams() {
        return number_teams;
    }

    /**
     * Returns the list of teams participating in the league.
     * @return The list of teams participating in the league.
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Returns the list of matches scheduled for the league.
     * @return The list of matches scheduled for the league.
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * Returns the state of the league.
     * @return The state of the league.
     */
    public boolean isState() {
        return state;
    }

    /**
     * Sets the name of the league.
     * @param name The name of the league.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the day of the league.
     * @param day The day of the league.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Sets the number of teams participating in the league.
     * @param number_teams The number of teams participating in the league.
     */
    public void setNumber_teams(int number_teams) {
        this.number_teams = number_teams;
    }

    /**
     * Sets the list of teams participating in the league.
     * @param teams The list of teams participating in the league.
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Sets the state of the league.
     * @param state The state of the league.
     */
    public void setState(boolean state) {
        this.state = state;
    }
}