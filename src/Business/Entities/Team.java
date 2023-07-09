package Business.Entities;

/**
 * The `Team` class represents a team in a sports context.
 */
public class Team implements Comparable<Team> {
    private String name;
    private int NPlayers;
    private int wins;
    private int ties;
    private int losses;
    private int points;

    /**
     * Constructs a new `Team` object with the specified attributes.
     *
     * @param name      The name of the team.
     * @param NPlayers  The number of players in the team.
     * @param wins      The number of wins by the team.
     * @param ties      The number of ties by the team.
     * @param losses    The number of losses by the team.
     * @param points    The total points earned by the team.
     */
    public Team(String name, int NPlayers, int wins, int ties, int losses, int points) {
        this.name = name;
        this.NPlayers = NPlayers;
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
        this.points = points;
    }

    /**
     * Constructs a new empty `Team` object.
     */
    public Team() {
    }

    /**
     * Returns the name of the team.
     *
     * @return The name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team.
     *
     * @param name The name of the team.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of players in the team.
     *
     * @return The number of players in the team.
     */
    public int getNPlayers() {
        return NPlayers;
    }


    /**
     * Returns the number of wins by the team.
     *
     * @return The number of wins by the team.
     */
    public int getWins() {
        return wins;
    }


    /**
     * Returns the number of ties by the team.
     *
     * @return The number of ties by the team.
     */
    public int getTies() {
        return ties;
    }


    /**
     * Returns the number of losses by the team.
     *
     * @return The number of losses by the team.
     */
    public int getLosses() {
        return losses;
    }


    /**
     * Returns the total points earned by the team.
     *
     * @return The total points earned by the team.
     */
    public int getPoints() {
        return points;
    }


    /**
     * Compares this team with the specified team for ordering based on points.
     * The team with more points is considered greater.
     *
     * @param team The team to be compared.
     * @return A negative integer if this team has fewer points than the specified team,
     *         zero if both teams have the same points, or a positive integer if this team
     *         has more points than the specified team.
     */
    @Override
    public int compareTo(Team team) {
        int comparePoints = team.getPoints();
        return comparePoints - this.points;
    }
}