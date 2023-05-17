package Business.Entities;

public class Match {

    private Team team1;
    private Team team2;

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

    public boolean isStatus() {
        return status;
    }
}
