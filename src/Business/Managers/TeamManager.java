package Business.Managers;

import java.nio.file.Files;
import java.nio.file.Paths;
import Business.Entities.Team;
import Business.Entities.User;
import Persistance.TeamsDAOInt;
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

    public void newTeam() {

        String jsonContenido;
        try {
            jsonContenido = Files.readString(Paths.get(jsonFile.getPath()));
        } catch (IOException e) {
            System.err.println("Error reading JSON file");
            return;
        }

        String teamName = teamNameFromJson(jsonContenido);
        int NPlayers = numberPlayersFromJson(jsonContenido);
        createTeam(teamName, NPlayers);
    }


    private void createTeam(String teamName, int NPlayers) {

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

        teamsDAO.InsertDataTeams(teamName, NPlayers, 0, 0, 0, 0);

    }

    public void deleteTeam(String teamName) {

        for (Team team : teamsList) {
            if (team.getName().equals(teamName)) {
                teamsDAO.DeleteDataTeams(teamName);
                teamsList.remove(team);
                return;
            } else {
                throw new IncorrectTeamNameException();
            }
        }
    }

}
