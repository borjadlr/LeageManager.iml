package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Exceptions.*;
import Persistance.LeagueDAOInt;
import Persistance.TeamsDAOInt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LeagueManager {

    private final TeamManager teamManager;
    
    private LeagueDAOInt leagueDAO;

    public LeagueManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void introduceLeague(League league) throws LeagueAlreadyExistsException, DateExpiredException, RepeatedTeamException, SQLException {
        List<League> leagues = leagueDAO.getAllLeagues(); //metodo borja nuevo
        int i = 0;
        
        while (i < leagues.size()){
            if (!leagues.get(i).getName().equals(league.getName())){
                throw new LeagueAlreadyExistsException();
            } else if (!comprovaData(league.getDate())) {
                throw new DateExpiredException();
            } else if (!comprovaRepeatedTeams(league)){
                throw new RepeatedTeamException();
            } else {
                //metodo borja
            }
            i++;
        }
    }

    public boolean comprovaData(Date date){
        Date today = new Date(System.currentTimeMillis());
        return date.after(today);
    }

    public boolean comprovaRepeatedTeams(League league) throws SQLException {
        List<Team> leagueTeams = league.getTeams();
        List<Team> allTeams = teamManager.getAllTeams();
        int flag = 0, i = 0, j = 0;

        while (allTeams.size() > j)  {
            if (leagueTeams.get(i).getName().equals(allTeams.get(j).getName())) {
                flag++;
                if (flag == 2){
                    return false;
                }
            } else if (leagueTeams.size() == i){
                i = 0;
                j++;
            } else {
                i++;
            }
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

    public List<League> listLeagues() throws SQLException {
        return leagueDAO.getAllLeagues();
    }

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
                    if (match.getTeam1() == team || match.getTeam2() == team) {
                        league.getMatches().remove(match);
                    }
                }

            }
        }
    }

}
