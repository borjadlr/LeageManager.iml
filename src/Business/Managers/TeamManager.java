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

/**
 * Clase que gestiona las operaciones relacionadas con los equipos.
 */
public class TeamManager {

    private final TeamsDAOInt teamsDAO;
    private final TeamsLeagueDAOInt teamsLeagueDAOInt;
    private final UserTeamsDAOInt userTeamsDAOInt;
    private Team team;
    private final List<Team> teamsList;

    private UserManager userManager;

    private List<User> userList;

    /**
     * Constructor de la clase TeamManager.
     *
     * @param teamDAO          objeto TeamsDAOInt para acceder a los datos de los equipos
     * @param teamsLeagueDAOInt objeto TeamsLeagueDAOInt para acceder a los datos de las ligas de equipos
     * @param userTeamsDAOInt  objeto UserTeamsDAOInt para acceder a los datos de los equipos de usuarios
     * @param team             objeto Team que representa al equipo actual
     */
    public TeamManager(TeamsDAOInt teamDAO, TeamsLeagueDAOInt teamsLeagueDAOInt, UserTeamsDAOInt userTeamsDAOInt, Team team) {

        this.teamsDAO = teamDAO;
        this.teamsLeagueDAOInt = teamsLeagueDAOInt;
        this.userTeamsDAOInt = userTeamsDAOInt;
        this.team = team;
        this.teamsList = new ArrayList<>();
    }

    /**
     * Devuelve una lista de equipos a partir de los nombres proporcionados.
     *
     * @param teamsNames lista de nombres de equipos
     * @return lista de objetos Team correspondientes a los nombres de equipos proporcionados
     * @throws SQLException si ocurre un error al acceder a los datos de los equipos
     */
    public List<Team> returnTeams(List<String> teamsNames) throws SQLException {
        List<Team> teams = new ArrayList<>();
        int i = 0;
        while (teamsNames.size() > i){
            teams.add(teamsDAO.selectTeam(teamsNames.get(i)));
        }
        return teams;
    }

    /**
     * Borra los nombres de los equipos de la memoria RAM.
     *
     * @param teams lista de equipos
     */
    public void eraseRAM(List<Team> teams) {
        int i = 0;
        for (Team team : teams) {
            teams.get(i).setName(null);
        }
    }

    /**
     * Crea un nuevo equipo con el nombre proporcionado.
     *
     * @param teamName nombre del equipo a crear
     * @throws SQLException si ocurre un error al acceder a los datos de los equipos
     */
    public void createTeam(String teamName) throws SQLException {
        teamsDAO.jsonToDatabase(teamName);
    }

    /**
     * Elimina un equipo con el nombre proporcionado.
     *
     * @param teamName nombre del equipo a eliminar
     * @throws SQLException si ocurre un error al acceder a los datos de los equipos
     */
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

    /**
     * Obtiene una lista de todos los equipos.
     *
     * @return lista de todos los equipos
     * @throws SQLException si ocurre un error al acceder a los datos de los equipos
     */
    public List<Team> getAllTeams() throws SQLException {
        if (teamsDAO.getAllTeams().size() == 0) {
            throw new NullPointerException();
        }
        return teamsDAO.getAllTeams();
    }

    /**
     * Obtiene una lista de equipos pertenecientes a una liga específica.
     *
     * @param leagueName nombre de la liga
     * @return lista de equipos pertenecientes a la liga especificada
     * @throws SQLException si ocurre un error al acceder a los datos de los equipos o las ligas
     */
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

    /**
     * Obtiene una lista de jugadores pertenecientes a un equipo específico.
     *
     * @param teamName nombre del equipo
     * @return lista de jugadores pertenecientes al equipo especificado
     * @throws SQLException si ocurre un error al acceder a los datos de los jugadores o los equipos
     */
    public List<User> getPlayersOfTeam(String teamName) throws SQLException {
        return userTeamsDAOInt.getTeamPlayers(teamName);
    }
}
