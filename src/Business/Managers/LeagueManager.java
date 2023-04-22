package Business.Managers;

import Business.Entities.League;
import Exceptions.LeagueAlreadyExistsException;
import Persistance.LeagueDAOInt;

import java.util.List;

public class LeagueManager {

    private final TeamManager teamManager;
    
    private LeagueDAOInt leagueDAO;

    public LeagueManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void introduceLeague(League league) throws LeagueAlreadyExistsException {
        List<League> leagues = leagueDAO.InsertDataLeague(); //metode borja nou
        int i = 0;
        
        while (i < leagues.size()){
            if (!leagues.get(i).getName().equals(league.getName())){
                throw new LeagueAlreadyExistsException();
            } else if () {
                
            }
            i++;
        }
    }

    public boolean comprovaData(String date, String hour){

    }

}
