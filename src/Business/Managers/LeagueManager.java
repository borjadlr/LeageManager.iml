package Business.Managers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.Team;
import Exceptions.DateExpiredException;
import Exceptions.IncorrectLeagueNameException;
import Exceptions.LeagueAlreadyExistsException;
import Exceptions.RepeatedTeamException;
import Persistance.LeagueDAOInt;
import Persistance.TeamsDAOInt;

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

    public void introduceLeague(League league) throws LeagueAlreadyExistsException, DateExpiredException, RepeatedTeamException {
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

    public boolean comprovaRepeatedTeams(League league) {
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

    public void deleteLeague(String leagueName) throws IncorrectLeagueNameException {
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

    public List<League> listLeagues() {
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

                matches.add(new Match(team1, team2));
                matches.add(new Match(team2, team1));
            }

            // Rotate teams in the list
            Collections.rotate(teamsList, 1);
        }

        return matches;
    }

}
