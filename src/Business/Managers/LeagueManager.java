
package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Exceptions.*;
import Persistance.LeagueDAOInt;
import Persistance.TeamsDAOInt;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LeagueManager {

    private final TeamManager teamManager;
    
    private final LeagueDAOInt leagueDAO;

    private TeamsDAOInt teamsDAO;

    public LeagueManager(TeamManager teamManager, LeagueDAOInt leagueDAO, TeamsDAOInt teamsDAO) {
        this.teamManager = teamManager;
        this.leagueDAO = leagueDAO;
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

    public boolean comprovaNumTeams(int numTeams) throws SQLException {
        List<Team> allTeams = teamManager.getAllTeams();
        if (allTeams.size() > numTeams){
            return false;
        } else return numTeams != 0;
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

    public void deleteLeague(String leagueName) throws IncorrectLeagueNameException, SQLException {
        List<League> leagues = leagueDAO.getAllLeagues();

        for (League league : leagues) {
            if (league.getName().equals(leagueName)) {
                leagueDAO.DeleteDataLeague(leagueName);
                return;
            } else {
                throw new IncorrectLeagueNameException();
            }
        }
    }

    public List<League> listLeagues() throws SQLException, NullPointerException {
        if (leagueDAO.getAllLeagues().size() == 0) {
            throw new NullPointerException();
        }
        return leagueDAO.getAllLeagues();
    }

/*
    public LinkedList<Match> generarCalendario(List<Team> equipos) {
        LinkedList<Match> calendario = new LinkedList<>();
        equipos = teamsDAO.getTeamsInLeague("LijeName");
        int numEquipos = equipos.size();
        int numJornadas = numEquipos - 1; // Cantidad de jornadas necesarias para que todos los equipos se enfrenten

        for (int i = 0; i < numJornadas * 2; i++) {
            for (int j = 0; j < numEquipos / 2; j++) {
                int equipoLocal = j;
                int equipoVisitante = (numEquipos - 1) - j;

                if (i % 2 == 0) {
                    Match partido = new Match(equipos.get(equipoVisitante).getName(), equipos.get(equipoLocal).getName(), 0, 0, i / 2 + 1, false);
                    calendario.add(partido);
                } else {
                    Match partido = new Match(equipos.get(equipoLocal).getName(), equipos.get(equipoVisitante).getName(), 0, 0, i / 2 + 1, false);
                    calendario.add(partido);
                }
            }

            // Rotación de los equipos excepto el primero
            Team ultimoEquipo = equipos.get(numEquipos - 1);
            for (int k = numEquipos - 1; k > 1; k--) {
                equipos.set(k, equipos.get(k - 1));
            }
            equipos.set(1, ultimoEquipo);
        }

        return calendario;
    }

 */
/*
    public List<Match> generateRRCalendar(League league) {
        List<Team> teams = league.getTeams();
        int numTeams = league.getNumber_teams();
        int numRounds = numTeams - 1;
        int halfSize = numTeams / 2;

        List<Team> teamsList = new ArrayList<>(teams);
        teamsList.remove(0);

        int teamsSize = teamsList.size();

        List<Match> matches = new ArrayList<>();

        for (int round = 0; round < numRounds; round++) {
            for (int idx = 0; idx < halfSize; idx++) {
                Team team1 = teamsList.get(idx);
                Team team2 = teamsList.get(teamsSize - idx - 1);

                matches.add(new Match(team1, team2, false));
                matches.add(new Match(team2, team1, false));
            }

            // Rotate teams in the list
            Collections.rotate(teamsList, 1);
        }

        return matches;
    }
*/
    public void deleteLeagueMatches (League leagueName) throws SQLException, MatchIsPlayingException {

        List<League> leagues = leagueDAO.getAllLeagues();
        // Borrar partidos jugados por el equipo en todas las ligas
        for (League league : leagues) {
            if (league.getName().equals(leagueName.getName())) {
                for (Match match : league.getMatches()) {
                    if (match.isStatus()) {
                        // Parar la ejecución si el partido está en marcha.
                        throw new MatchIsPlayingException();
                    }
                    league.getMatches().remove(match);
                    //metodo borja
                }
            }
        }
    }
/*
    public void deleteTeamMatches (Team team) throws SQLException, MatchIsPlayingException {

        List<League> leagues = leagueDAO.getAllLeagues();
        for (League league : leagues) {
            for (Match match : league.getMatches()) {
                if (match.isStatus()) {
                    // Parar la ejecución si el partido está en marcha.
                    throw new MatchIsPlayingException();
                } else {
                    if (match.getTeam1() == team || match.getTeam2() == team) {
                        league.getMatches().remove(match);
                        //metodo borja
                    }
                }

            }
        }
    }
*/
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

}
