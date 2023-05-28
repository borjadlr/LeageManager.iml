
package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Exceptions.*;
import Persistance.LeagueDAOInt;
import Persistance.MatchDAOInt;
import Persistance.TeamsDAOInt;
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

    private TeamsDAOInt teamsDAO;

    public LeagueManager(TeamManager teamManager, LeagueDAOInt leagueDAO, TeamsDAOInt teamsDAO, MatchDAOInt matchDAO, TeamsLeagueDAOInt teamsLeagueDAO) {
        this.teamManager = teamManager;
        this.leagueDAO = leagueDAO;
        this.teamsLeagueDAOInt = teamsLeagueDAO;
        this.matchDAO = matchDAO;
        this.teamsDAO = teamsDAO;
    }



    public void introduceLeague(League league) throws LeagueAlreadyExistsException, DateExpiredException, WrongTeamNumberException, RepeatedTeamException, SQLException {
        try {
            List<League> leagues = leagueDAO.getAllLeagues();
            int i = 0;
            while (i < leagues.size()){
                if (leagues.get(i).getName().equals(league.getName())) {
                    throw new LeagueAlreadyExistsException();
                } else if (!comprovaData(league.getDate())) {
                    throw new DateExpiredException();
                } else if (!comprovaRepeatedTeams(league)){
                    throw new RepeatedTeamException();
                } else if (!comprovaNumTeams(league.getNumber_teams())) {
                    throw new WrongTeamNumberException();
                } else {
                    i++;
                }
            }
        } catch (NullPointerException npe) {
            if (!comprovaData(league.getDate())) {
                throw new DateExpiredException();
            } else if (!comprovaRepeatedTeams(league)){
                throw new RepeatedTeamException();
            } else if (!comprovaNumTeams(league.getNumber_teams())) {
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

    public void introduceTeamsLeague(List<Team> teams, String leagueName) throws SQLException, NumberOfTeamsDoNotRelateException {
        int i = 0;
        League league = getLeagueByName(leagueName);

        if (league.getNumber_teams() != teams.size()){
            throw new NumberOfTeamsDoNotRelateException();
        }

        while (teams.size() > i) {
            teamsLeagueDAOInt.insertarEquipoLiga(teams.get(i).getName(), leagueName);
            i++;
        }
        generateCalendar(teams, leagueName);
        league.setTeams(teams);
    }

    public void generateCalendar(List<Team> teams, String leagueName) throws SQLException {
        List<Match> matches = matchDAO.crearCalendarioIdaVuelta(teams,leagueName);
        League league = getLeagueByName(leagueName);
        league.setMatches(matches);
    }

    public boolean comprovaNumTeams(int numTeams) throws SQLException {
        List<Team> allTeams = teamManager.getAllTeams();
        if (allTeams.size() < numTeams){
            return false;
        } else return numTeams != 0;
    }

    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<>();
        List<League> leagues = listLeagues();
        int i = 0;

        while (leagues.size() > i) {
            matches.addAll(leagues.get(i).getMatches());
            i++;
        }

        return matches;
    }

    public String correctData(String data) throws DateExpiredException {
        char caracter = data.charAt(4);
        if (data.length() != 10){
            throw new DateExpiredException();
        } else if (caracter != '-' || data.charAt(7) != '-') {
            data = data.replace(caracter, '-');
        }

        return data;
    }

    public String correctTime(String time) throws WrongTimeException {
        char caracter = time.charAt(2);
        if (time.length() != 8){
            throw new WrongTimeException();
        } else if (caracter != ':' || time.charAt(6) != ':') {
            time = time.replace(caracter, ':');
        }

        return time;
    }

    public League setLeague (String name, Date date, Time hour, int day, int teamNumber, boolean state, List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        return new League(name, date, hour, day, teamNumber, teams, matches, state);
    }

    public java.sql.Date turnToSql(Date date){
        return new java.sql.Date(date.getTime());
    }

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

    public Date stringToDate(String dataString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
    }

    public boolean comprovaData(Date date){
        Date today = new Date(System.currentTimeMillis());
        return date.after(today);
    }

    public boolean comprovaRepeatedTeams(League league) throws SQLException {
        List<Team> leagueTeams = league.getTeams();
        List<Team> allTeams = teamManager.getAllTeams();
        int leaguesWithSameName = 0, i = 0, j = 0;

        while (allTeams.size() > j)  {
            i = 0;
            while (leagueTeams.size() > i) {
                if (leagueTeams.get(i).getName().equals(allTeams.get(j).getName())) {
                    leaguesWithSameName++;
                    if (leaguesWithSameName == 2){
                        return false;
                    }
                }
                i++;
            }
            j++;
        }

        return true;
    }

    public void deleteLeague(String leagueName) throws  SQLException, MatchIsPlayingException {
        List<League> leagues = leagueDAO.getAllLeagues();

        for (League league : leagues) {
            //System.out.println(league.getName());
            if (league.getName().equals(leagueName)) {
                leagueDAO.DeleteDataLeague(leagueName);
                deleteLeagueMatches(leagueName);
                return;
            }
        }
    }

    public List<League> listLeagues() throws SQLException, NullPointerException {
        if (leagueDAO.getAllLeagues().size() == 0) {
            throw new NullPointerException();
        }
        return leagueDAO.getAllLeagues();
    }

    public void deleteLeagueMatches (String leagueName) throws SQLException, MatchIsPlayingException {

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

    public void deleteTeamMatches (Team team) throws SQLException, MatchIsPlayingException {

        List<League> leagues = leagueDAO.getAllLeagues();
        for (League league : leagues) {
            for (Match match : league.getMatches()) {
                if (match.isStatus()) {
                    // Parar la ejecución si el partido está en marcha.
                    throw new MatchIsPlayingException();
                } else {
                    if ((match.getLocal()) == team.getName() || (match.getVisitante()) == team.getName()) {
                        league.getMatches().remove(match);
                        matchDAO.deleteMatchesByTeamName(team.getName());
                    }
                }

            }
        }
    }

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
    public boolean isLeagueActive(League league) {
        return league.isState();
    }

    public boolean isLeagueExisting(List<League> leagues, List<League> leaguesUser) throws IncorrectLeagueNameException {
        int i = 0, j = 0;
        boolean nameFound = false;

        while (leagues.size() > i){
            while (leaguesUser.size() > j) {
                if (leagues.get(i).getName().equals(leaguesUser.get(j).getName())){
                    nameFound = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!nameFound){
                return false;
            }
            j = 0;
            i++;
        }
        return true;
    }

    public League getLeagueByName(String leagueName) throws SQLException {
        League league = new League();
        List<League> leagues = leagueDAO.getAllLeagues();
        int i = 0;

        while (leagues.size() > i){
            if (leagues.get(i).getName().equals(leagueName)){
                return leagues.get(i);
            }
            i++;
        }

        return league;

    }

}
