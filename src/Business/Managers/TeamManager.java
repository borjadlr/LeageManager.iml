package Business.Managers;

import java.nio.file.Files;
import java.nio.file.Paths;
import Business.Entities.Team;
import Business.Entities.User;
import Exceptions.IncorrectTeamNameException;
import Exceptions.InvalidPlayerNumberException;
import Exceptions.TeamAlreadyExistsException;
import Persistance.TeamsDAOInt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamManager {

    private final TeamsDAOInt teamsDAO;
    private Team team;
    private List<Team> teamsList;
    private List<User> userList;


    public TeamManager(TeamsDAOInt teamDAO, Team team, List<Team> teamsList, List<User> userList) {
        this.teamsDAO = teamDAO;
        this.team = team;
        this.teamsList = new ArrayList<>();
        this.userList = new ArrayList<>();

    }




    private void createTeam(String teamName, int NPlayers) throws InvalidPlayerNumberException, TeamAlreadyExistsException, SQLException {

        for (Team team : teamsList) {
            if (team.getName().equals(teamName)) {
                throw new TeamAlreadyExistsException();
            }
        }

        for (User user : userList) {
            if (user.getNumber() <= 0) {
                throw new InvalidPlayerNumberException();
            }
        }

        Team team = new Team(teamName, NPlayers, 0, 0, 0, 0);
        teamsList.add(team);

        teamsDAO.insertDataTeams(teamName, NPlayers, 0, 0, 0, 0);

    }

    public void deleteTeam(String teamName) throws IncorrectTeamNameException, SQLException {
        List<Team> teams = getAllTeams();
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                teamsDAO.deleteDataTeams(teamName);
                teamsList.remove(team);
                return;
            } else {
                throw new IncorrectTeamNameException();
            }
        }
    }

    public List<Team> getAllTeams() throws SQLException {
        return teamsDAO.getAllTeams();
    }

}
