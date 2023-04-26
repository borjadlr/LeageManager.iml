package Business.Managers;

import Business.Entities.League;
import Business.Entities.Team;
import Exceptions.DateExpiredException;
import Exceptions.LeagueAlreadyExistsException;
import Persistance.LeagueDAOInt;

import java.util.Date;
import java.util.List;

public class LeagueManager {

    private final TeamManager teamManager;
    
    private LeagueDAOInt leagueDAO;

    public LeagueManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void introduceLeague(League league) throws LeagueAlreadyExistsException, DateExpiredException {
        List<League> leagues = leagueDAO.InsertDataLeague(); //metodo borja nuevo
        int i = 0;
        
        while (i < leagues.size()){
            if (!leagues.get(i).getName().equals(league.getName())){
                throw new LeagueAlreadyExistsException();
            } else if (!comprovaData(league.getDate())) {
                throw new DateExpiredException();
            }
            i++;
        }
    }

    public boolean comprovaData(Date date){
        Date today = new Date(System.currentTimeMillis());

        return date.after(today);
    }

    public void deleteLeague(String leagueName) {
        List<League> leagues = leagueDAO.;

        for (League league : leagues) {
            if (league.getName().equals(leagueName)) {
                leagueDAO.DeleteDataLeague(leagueName);
                return;
            } else {
                throw new IncorrectLeagueNameException();
            }
        }
    }

}
