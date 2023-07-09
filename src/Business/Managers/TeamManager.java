package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Business.Entities.User;
import Exceptions.MatchIsPlayingException;
import Persistance.*;
import Persistance.dao.LeagueDAO;
import Persistance.dao.MatchDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase que gestiona las operaciones relacionadas con los equipos.
 */
public class TeamManager {

    private final TeamsDAOInt teamsDAO;
    private final TeamsLeagueDAOInt teamsLeagueDAOInt;
    private final UserTeamsDAOInt userTeamsDAOInt;
    private final List<Team> teamsList;

    /**
     * Constructor de la clase TeamManager.
     *
     * @param teamDAO          objeto TeamsDAOInt para acceder a los datos de los equipos
     * @param teamsLeagueDAOInt objeto TeamsLeagueDAOInt para acceder a los datos de las ligas de equipos
     * @param userTeamsDAOInt  objeto UserTeamsDAOInt para acceder a los datos de los equipos de usuarios
     */
    public TeamManager(TeamsDAOInt teamDAO, TeamsLeagueDAOInt teamsLeagueDAOInt, UserTeamsDAOInt userTeamsDAOInt) {

        this.teamsDAO = teamDAO;
        this.teamsLeagueDAOInt = teamsLeagueDAOInt;
        this.userTeamsDAOInt = userTeamsDAOInt;
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
    public void deleteTeam(String teamName) throws SQLException, MatchIsPlayingException {
        List<Team> teams = getAllTeams();
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                teamsDAO.deleteDataTeams(teamName);
                teamsList.remove(team);
                deleteTeamMatches(team);
                return;
            }
        }
    }

    /**
     * Elimina los partidos asociados a un equipo en todas las ligas.
     *
     * @param team el equipo a eliminar los partidos
     * @throws SQLException            si ocurre un error al acceder a la base de datos
     * @throws MatchIsPlayingException si se intenta eliminar partidos en curso
     */
    public void deleteTeamMatches(Team team) throws SQLException, MatchIsPlayingException {
        LeagueDAOInt leagueDAO = new LeagueDAO();
        MatchDAOInt matchDAO = new MatchDAO();
        List<League> leagues = leagueDAO.getAllLeagues();
        for (League league : leagues) {
            for (Match match : league.getMatches()) {
                if (match.isStatus()) {
                    throw new MatchIsPlayingException();
                } else {
                    if (Objects.equals(match.getLocal(), team.getName()) || Objects.equals(match.getVisitante(), team.getName())) {
                        league.getMatches().remove(match);
                        matchDAO.deleteMatchesByTeamName(team.getName());
                    }
                }
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
