package Business.Managers;

import Business.Entities.League;
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

    public void introduceLeague(League league) throws LeagueAlreadyExistsException {
        List<League> leagues = leagueDAO.InsertDataLeague(); //metode borja nou
        int i = 0;
        
        while (i < leagues.size()){
            if (!leagues.get(i).getName().equals(league.getName())){
                throw new LeagueAlreadyExistsException();
            } else if (/*comprovaData(league.getDate(), league.getHour())*/) {

            }
            i++;
        }
    }

    /*public boolean comprovaData(Date date, Date hour){
        Date today = new Date();

        if (date.getTime() == today.){

        }
    }*/

}
