package Business.Entities;

import java.util.Date;

public class Match {

    private Team team1;
    private Team team2;

    private String golesTeam1;

    private String golesTeam2;

    private Date matchDay;
    private boolean status;

    public Match(Team team1, Team team2, boolean status) {
        this.team1 = team1;
        this.team2 = team2;
        this.status = status;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setGolesTeam1(String golesTeam1) {
        this.golesTeam1 = golesTeam1;
    }

    public void setGolesTeam2(String golesTeam2) {
        this.golesTeam2 = golesTeam2;
    }

    public void setMatchDay(Date matchDay) {
        this.matchDay = matchDay;
    }

    public Date getMatchDay() {
        return matchDay;
    }

    public String getGolesTeam1() {
        return golesTeam1;
    }

    public String getGolesTeam2() {
        return golesTeam2;
    }

    public boolean isStatus() {
        return status;
    }
}
