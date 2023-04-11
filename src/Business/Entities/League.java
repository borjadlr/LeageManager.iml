package Business.Entities;

public class League {

    private String name;
    private String date;
    private String hour;
    private int day;
    private int number_teams;
    private boolean state;

    public League(String name, String date, String hour, int day, int number_teams, boolean state) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.day = day;
        this.number_teams = number_teams;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public int getDay() {
        return day;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setNumber_teams(int number_teams) {
        this.number_teams = number_teams;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
