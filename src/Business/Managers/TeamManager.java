package Business.Managers;

import Business.Entities.Team;
import Business.Entities.User;
import Exceptions.*;
import Persistance.TeamsDAOInt;
import Business.Entities.League;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Optional;

public class TeamManager {

    private final TeamsDAOInt teamsDAO;
    private Team teams;

    private UserManager userManager;
    private List<Team> teamsList;
    private List<User> userList;


    public TeamManager(TeamsDAOInt teamDAO, Team teams, UserManager userManager) {
        this.teamsDAO = teamDAO;
        this.teams = teams;
        this.userManager = userManager;
        this.teamsList = new ArrayList<>();
        this.userList = new ArrayList<>();

    }

    public void newTeam(TeamsDAOInt teamsDAO) {


        // Read JSON file content as a string
        String jsonContenido;
        try {
            jsonContenido = teamsDAO.InsertDataTeams();
        } catch (IOException e) {
            System.err.println("Error reading JSON file");
            return;
        }

        // Parse JSON string into a list of players
        List<Team> newPlayersList = parsePlayersFromJson(jsonContent);
        if (newPlayersList == null || newPlayersList.isEmpty()) {
            System.err.println("Error parsing JSON content");
            return;
        }

        String newTeam = newPlayersList.get(0).getName();
        for (Team team : teamsList) {
            if (team.getName().equals(newTeam)) {
                System.err.println("Team name already exists");
                return;
            }
        }

        for (User user : userList) {
            if (user.getNumber() <= 0) {
                System.err.println("Invalid squad number for player " + user.getDni());
                return;
            }
        }

        userManager.comprovaCaractersMail();
        userManager.comprovaDNI();


        int nPlayers = newPlayersList.size();
        Team Team = new Team(name, nPlayers, 0, 0, 0, 0);
        teamsList.add(Team);

        teamsDAO.InsertDataTeams(name, nPlayers, 0, 0, 0, 0);





    /*public void deleteTeam (Teams teams) {

        for (League league : teams.getLeagues()) {
            league.removeTeam(teams);
        }

        // Delete all matches played by the team in all leagues
        for (Match match : teams.getMatches()) {
            if (match.getStatus() == MatchStatus.IN_PROGRESS) {
                // Stop execution if the match is in progress
                throw new RuntimeException("Cannot delete team while match is in progress");
            }
            match.removeMatch(match);

        }

        // Delete the team itself
        TeamRepository.delete(teams);
    }

    public void removeTeam(Teams teams) {
        if (!teams.contains(teams)) {
            throw new IllegalArgumentException("Team is not in league");
        }
        teams.remove(teams);
    }

    public void removeMatch(Match match) {
        if (!matches.contains(match)) {
            throw new IllegalArgumentException("Match is not in team's record");
        }

        matches.remove(match);
    }
*/

        }

        public boolean deleteTeam(String teamName) {

            Optional<Team> optionalTeam = teams.getName(teamName);
            if (!optionalTeam.isPresent()) {

                return false;
            }
            Team team = optionalTeam.get();

            // Remove the team from all leagues.
            List<League> leagues = getLeaguesForTeam(team);
            for (League league : leagues) {
                league.removeTeam(team);
            }


           /* List<Match> matches = getMatchesForTeam(team);
            for (Match match : matches) {
                if (match.getStatus() == MatchStatus.PLAYING) {
                    match.stopMatch();
                }
                league.removeMatch(match);
            }
            teamsDAO.DeleteDataTeams(teamName);

            return true;
        }*/

        /*private List<League> getLeaguesForTeam(Teams team) {
            List<League> leagues = new ArrayList<>();
            return leagues;
        }

        private List<Match> getMatchesForTeam(Teams team) {
            List<Match> matches = new ArrayList<>();
            return matches;
        }
    }*/
}
