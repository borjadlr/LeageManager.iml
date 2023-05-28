package Business.Managers;

import Business.Entities.Team;
import Business.Entities.User;
import Exceptions.IncorrectTeamNameException;
import Exceptions.InvalidPlayerNumberException;
import Exceptions.TeamAlreadyExistsException;
import Persistance.TeamsDAOInt;
import Persistance.TeamsLeagueDAOInt;
import Persistance.UserTeamsDAOInt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamManager {

    private final TeamsDAOInt teamsDAO;

    private final TeamsLeagueDAOInt teamsLeagueDAOInt;

    private final UserTeamsDAOInt userTeamsDAOInt;
    private Team team;
    private List<Team> teamsList;
    private UserManager userManager;

    private List<User> userList;


    public TeamManager(TeamsDAOInt teamDAO, TeamsLeagueDAOInt teamsLeagueDAOInt, UserTeamsDAOInt userTeamsDAOInt, Team team) {
        this.teamsDAO = teamDAO;
        this.teamsLeagueDAOInt = teamsLeagueDAOInt;
        this.userTeamsDAOInt = userTeamsDAOInt;
        this.team = team;
        this.teamsList = new ArrayList<>();

    }

    public List<Team> returnTeams(List<String> teamsNames) throws SQLException {
        List<Team> teams = new ArrayList<>();
        int i = 0;
        while (teamsNames.size() > i){
            teams.add(teamsDAO.selectTeam(teamsNames.get(i)));
        }
        return teams;
    }


    public void createTeam(String teamName) throws SQLException {
        teamsDAO.jsonToDatabase(teamName);
    }

    public void deleteTeam(String teamName) throws SQLException {
        List<Team> teams = getAllTeams();
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                teamsDAO.deleteDataTeams(teamName);
                teamsList.remove(team);
                return;
            }
        }
    }

    public List<Team> getAllTeams() throws SQLException {
        if (teamsDAO.getAllTeams().size() == 0) {
            throw new NullPointerException();
        }
        return teamsDAO.getAllTeams();
    }

    public List<Team> getTeamsOfLeague(String leagueName) throws SQLException {
        List<String> teamNames = teamsLeagueDAOInt.obtenerEquiposPorLiga(leagueName);
        List<Team> teams = new ArrayList<>();
        int i = 0;

        while (teamNames.size() > i){
            teams.add(teamsDAO.selectTeam(teamNames.get(i)));
            i++;
        }
        Collections.sort(teams);

        return teams;
    }

    public List<User> getPlayersOfTeam(String teamName) throws SQLException {
        return userTeamsDAOInt.getTeamPlayers(teamName);
    }



}
