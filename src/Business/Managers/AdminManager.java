package Business.Managers;

import Business.Entities.Admin;
import Business.Entities.Config;
import Persistance.UserDAOInt;
import Persistance.dao.ConfigJsonDAO;

import java.io.IOException;

public class AdminManager {

    private final UserDAOInt userDAO;

    private final LeagueManager leagueManager;

    private final TeamManager teamManager;

    public AdminManager(UserDAOInt userDAO, LeagueManager leagueManager, TeamManager teamManager) {
        this.userDAO = userDAO;
        this.leagueManager = leagueManager;
        this.teamManager = teamManager;
    }

    public boolean isAdmin (String name) throws IOException {


        if (name.equalsIgnoreCase("admin")){
            Admin admin = new Admin();
            admin.setName(name.toLowerCase());
            return true;
        }

        return false;
    }





}
