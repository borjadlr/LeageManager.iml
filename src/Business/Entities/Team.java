package Business.Entities;

public class Team {
    private String name;
    private int NPlayers;
    private int wins;
    private int ties;
    private int losses;
    private int points;

    public Team(String name, int NPlayers, int wins, int ties, int losses, int points) {
        this.name = name;
        this.NPlayers = NPlayers;
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
        this.points = points;
    }

    public Team(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNPlayers() {
        return NPlayers;
    }

    public void setNPlayers(int NPlayers) {
        this.NPlayers = NPlayers;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
