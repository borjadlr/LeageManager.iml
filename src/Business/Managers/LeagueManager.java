package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Exceptions.*;
import Persistance.LeagueDAOInt;
import Persistance.MatchDAOInt;
import Persistance.TeamsLeagueDAOInt;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LeagueManager {

    private final TeamManager teamManager;
    private final LeagueDAOInt leagueDAO;
    private final MatchDAOInt matchDAO;
    private final TeamsLeagueDAOInt teamsLeagueDAOInt;

    public LeagueManager(TeamManager teamManager, LeagueDAOInt leagueDAO, MatchDAOInt matchDAO, TeamsLeagueDAOInt teamsLeagueDAO) {
        this.teamManager = teamManager;
        this.leagueDAO = leagueDAO;
        this.teamsLeagueDAOInt = teamsLeagueDAO;
        this.matchDAO = matchDAO;
    }

    /**
     * Introduce una liga en el sistema.
     *
     * @param league la liga a introducir
     * @throws LeagueAlreadyExistsException si la liga ya existe
     * @throws DateExpiredException         si la fecha de la liga ha expirado
     * @throws WrongTeamNumberException     si el número de equipos es incorrecto
     * @throws RepeatedTeamException        si hay equipos repetidos en la liga
     * @throws SQLException                 si ocurre un error de SQL
     */
    public void introduceLeague(League league) throws LeagueAlreadyExistsException, DateExpiredException, WrongTeamNumberException, RepeatedTeamException, SQLException {
        try {
            List<League> leagues = leagueDAO.getAllLeagues();
            int i = 0;
            while (i < leagues.size()) {
                if (leagues.get(i).getName().equals(league.getName())) {
                    throw new LeagueAlreadyExistsException();
                } else if (comprovaData(league.getDate())) {
                    throw new DateExpiredException();
                } else if (comprovaRepeatedTeams(league)) {
                    throw new RepeatedTeamException();
                } else if (comprovaNumTeams(league.getNumber_teams())) {
                    throw new WrongTeamNumberException();
                } else {
                    i++;
                }
            }
        } catch (NullPointerException npe) {
            if (comprovaData(league.getDate())) {
                throw new DateExpiredException();
            } else if (comprovaRepeatedTeams(league)) {
                throw new RepeatedTeamException();
            } else if (comprovaNumTeams(league.getNumber_teams())) {
                throw new WrongTeamNumberException();
            }
        }

        leagueDAO.insertDataLeague(league.getName(),
                turnToSql(league.getDate()),
                league.getTime(),
                league.getDay(),
                league.getNumber_teams(),
                league.isState());
    }

    /**
     * Introduce los equipos de una liga en el sistema.
     *
     * @param teams      la lista de equipos a introducir
     * @param leagueName el nombre de la liga
     * @throws SQLException                      si ocurre un error de SQL
     * @throws NumberOfTeamsDoNotRelateException si el número de equipos no coincide con el número esperado
     */
    public void introduceTeamsLeague(List<Team> teams, String leagueName) throws SQLException, NumberOfTeamsDoNotRelateException {
        int i = 0;
        League league = getLeagueByName(leagueName);

        if (league.getNumber_teams() != teams.size()) {
            throw new NumberOfTeamsDoNotRelateException();
        }
        while (teams.size() > i) {
            teamsLeagueDAOInt.insertarEquipoLiga(teams.get(i).getName(), leagueName);
            i++;
        }
        generateCalendar(teams, leagueName);
        league.setTeams(teams);
    }

    /**
     * Genera el calendario de partidos para una liga.
     *
     * @param teams      los equipos de la liga
     * @param leagueName el nombre de la liga
     * @throws SQLException si ocurre un error de SQL
     */
    public void generateCalendar(List<Team> teams, String leagueName) throws SQLException {
        List<Match> matches = matchDAO.crearCalendarioIdaVuelta(teams, leagueName);
        League league = getLeagueByName(leagueName);
        league.setMatches(matches);
    }

    /**
     * Comprueba si el número de equipos es válido.
     *
     * @param numTeams el número de equipos
     * @return true si el número de equipos es válido, false de lo contrario
     * @throws SQLException si ocurre un error de SQL
     */
    public boolean comprovaNumTeams(int numTeams) throws SQLException {
        List<Team> allTeams = teamManager.getAllTeams();
        if (allTeams.size() < numTeams) {
            return true;
        } else return numTeams == 0;
    }

    /**
     * Obtiene todos los partidos.
     *
     * @return una lista con todos los partidos
     * @throws SQLException si ocurre un error de SQL
     */
    public List<Match> getAllMatches() throws SQLException {
        return matchDAO.getAllMatches();
    }

    /**
     * Corrige el formato de la fecha.
     *
     * @param data la fecha a corregir
     * @return la fecha corregida
     * @throws DateExpiredException si la fecha no tiene el formato correcto
     */
    public String correctData(String data) throws DateExpiredException {
        char caracter = data.charAt(4);
        if (data.length() != 10) {
            throw new DateExpiredException();
        } else if (caracter != '-' || data.charAt(7) != '-') {
            data = data.replace(caracter, '-');
        }

        return data;
    }

    /**
     * Corrige el formato de la hora.
     *
     * @param time la hora a corregir
     * @return la hora corregida
     * @throws WrongTimeException si la hora no tiene el formato correcto
     */
    public String correctTime(String time) throws WrongTimeException {
        char caracter = time.charAt(2);
        if (time.length() != 8) {
            throw new WrongTimeException();
        } else if (caracter != ':' || time.charAt(6) != ':') {
            time = time.replace(caracter, ':');
        }

        return time;
    }

    /**
     * Crea una instancia de la clase `League` con los datos proporcionados.
     *
     * @param name       el nombre de la liga
     * @param date       la fecha de la liga
     * @param hour       la hora de la liga
     * @param day        el día de la liga
     * @param teamNumber el número de equipos en la liga
     * @param state      el estado de la liga
     * @param teams      los equipos de la liga
     * @return una instancia de la clase `League`
     */
    public League setLeague(String name, Date date, Time hour, int day, int teamNumber, boolean state, List<Team> teams) {
        League league = new League();
        league.setName(name);
        league.setDate(turnToSql(date));
        league.setTime(hour);
        league.setDay(day);
        league.setNumber_teams(teamNumber);
        league.setState(state);
        league.setTeams(teams);
        List<Match> matches = new ArrayList<>();
        league.setMatches(matches);
        return league;
    }

    /**
     * Convierte una fecha de tipo `Date` a tipo `java.sql.Date`.
     *
     * @param date la fecha a convertir
     * @return la fecha convertida a tipo `java.sql.Date`
     */
    public java.sql.Date turnToSql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Convierte una cadena de texto en formato de hora a un objeto de tipo `Time`.
     *
     * @param timeString la cadena de texto que representa la hora
     * @return el objeto `Time` que representa la hora
     */
    public Time stringToTime(String timeString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = format.parse(timeString);
            return new Time(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte una cadena de texto en formato de fecha a un objeto de tipo `Date`.
     *
     * @param dataString la cadena de texto que representa la fecha
     * @return el objeto `Date` que representa la fecha
     * @throws ParseException si la cadena de texto no tiene el formato correcto
     */
    public Date stringToDate(String dataString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
    }

    /**
     * Comprueba si una fecha está en el futuro.
     *
     * @param date la fecha a comprobar
     * @return true si la fecha está en el futuro, false de lo contrario
     */
    public boolean comprovaData(Date date) {
        Date today = new Date(System.currentTimeMillis());
        return !date.after(today);
    }

    /**
     * Comprueba si hay equipos repetidos en una liga.
     *
     * @param league la liga a comprobar
     * @return `true` si no hay equipos repetidos, `false` en caso contrario
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public boolean comprovaRepeatedTeams(League league) throws SQLException {
        // Obtener la lista de equipos de la liga
        List<Team> leagueTeams = league.getTeams();
        // Obtener todos los equipos disponibles
        List<Team> allTeams = teamManager.getAllTeams();
        int leaguesWithSameName = 0, i = 0, j = 0;

        // Comprobar si hay equipos con el mismo nombre en la liga y en todos los equipos
        while (allTeams.size() > j) {
            i = 0;
            while (leagueTeams.size() > i) {
                if (leagueTeams.get(i).getName().equals(allTeams.get(j).getName())) {
                    leaguesWithSameName++;
                    if (leaguesWithSameName == 2) {
                        return true;
                    }
                }
                i++;
            }
            j++;
        }

        return false;
    }

    /**
     * Elimina una liga y todos sus partidos asociados.
     *
     * @param leagueName el nombre de la liga a eliminar
     * @throws SQLException            si ocurre un error al acceder a la base de datos
     * @throws MatchIsPlayingException si se intenta eliminar una liga con partidos en curso
     */
    public void deleteLeague(String leagueName) throws SQLException, MatchIsPlayingException {
        List<League> leagues = leagueDAO.getAllLeagues();
        List<Team> teams = teamManager.getAllTeams();
        int i = 0;

        for (League league : leagues) {
            if (league.getName().equals(leagueName)) {
                leagueDAO.DeleteDataLeague(leagueName);
                deleteLeagueMatches(leagueName);
                return;
            }
        }
    }

    /**
     * Obtiene la lista de ligas disponibles.
     *
     * @return la lista de ligas
     * @throws SQLException si ocurre un error al acceder a la base de datos
     * @throws NullPointerException si no hay ligas disponibles
     */
    public List<League> listLeagues() throws SQLException, NullPointerException {
        return leagueDAO.getAllLeagues();
    }

    /**
     * Elimina los partidos asociados a una liga.
     *
     * @param leagueName el nombre de la liga
     * @throws SQLException            si ocurre un error al acceder a la base de datos
     * @throws MatchIsPlayingException si se intenta eliminar partidos en curso
     */
    public void deleteLeagueMatches(String leagueName) throws SQLException, MatchIsPlayingException {
        List<League> leagues = leagueDAO.getAllLeagues();

        // Borrar partidos jugados por el equipo en todas las ligas
        for (League league : leagues) {
            if (league.getName().equals(leagueName)) {
                for (Match match : league.getMatches()) {
                    if (match.isStatus()) {
                        // Parar la ejecución si el partido está en marcha.
                        throw new MatchIsPlayingException();
                    }
                    league.getMatches().remove(match);
                    //matchDAO.deleteMatch(match.getNombreLiga());
                }
            }
        }
    }

    /**
     * Obtiene los partidos en curso de una liga.
     *
     * @param leagueName el nombre de la liga
     * @return la lista de partidos en curso
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Match> getSimulatingMatches(String leagueName) throws SQLException {
        List<Match> matches = new ArrayList<>();
        List<League> leagues = leagueDAO.getAllLeagues();
        for (League league : leagues) {
            if (league.getName().equals(leagueName)) {
                for (Match match : league.getMatches()) {
                    if (match.isStatus()) {
                        matches.add(match);
                    }
                }
            }
        }
        return matches;
    }

    /**
     * Verifica si una liga está activa.
     *
     * @param league la liga a verificar
     * @return `true` si la liga está activa, `false` en caso contrario
     */
    public boolean isLeagueActive(League league) {
        return league.isState();
    }

    /**
     * Verifica si una lista de ligas existe en otra lista de ligas.
     *
     * @param leagues     las ligas a verificar
     * @param leaguesUser las ligas de usuario
     * @return `true` si todas las ligas existen, `false` en caso contrario
     * @throws IncorrectLeagueNameException si se encuentra un nombre de liga incorrecto
     */
    public boolean isLeagueExisting(List<League> leagues, List<League> leaguesUser) throws IncorrectLeagueNameException {
        int i = 0, j = 0;
        boolean nameFound = false;

        while (leagues.size() > i) {
            while (leaguesUser.size() > j) {
                if (leagues.get(i).getName().equals(leaguesUser.get(j).getName())) {
                    nameFound = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!nameFound) {
                return false;
            }
            j = 0;
            i++;
        }
        return true;
    }

    /**
     * Obtiene una liga por su nombre.
     *
     * @param leagueName el nombre de la liga
     * @return la liga encontrada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public League getLeagueByName(String leagueName) throws SQLException {
        League league = new League();
        List<League> leagues = leagueDAO.getAllLeagues();
        int i = 0;

        while (leagues.size() > i) {
            if (leagues.get(i).getName().equals(leagueName)) {
                return leagues.get(i);
            }
            i++;
        }

        return league;
    }
}