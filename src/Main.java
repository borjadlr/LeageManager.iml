import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.*;
import Persistance.TeamsLeagueDAOInt;
import Persistance.UserTeamsDAOInt;
import Persistance.dao.TeamsDAO;
import Persistance.dao.TeamsLeagueDAO;
import Persistance.dao.UserDAO;
import Persistance.dao.UserTeamsDAO;
import Presentation.Controllers.*;
import Presentation.Views.*;

import java.awt.event.FocusListener;

public class Main {

    public static void main(String[] args) {

        //Entities
        Team team = new Team();

        //Dao
        UserDAO userDAO = new UserDAO();
        TeamsDAO teamsDAO = new TeamsDAO();
        UserTeamsDAO userTeamsDAO = new UserTeamsDAO();
        TeamsLeagueDAO teamsLeagueDAO = new TeamsLeagueDAO();
        //Managers
        User user = new User();
        TeamManager teamManager = new TeamManager(teamsDAO, team);
        LeagueManager leagueManager = new LeagueManager(teamManager);
        AdminManager adminManaguer = new AdminManager(userDAO, leagueManager, teamManager);
        UserManager userManager = new UserManager(userDAO, leagueManager, teamManager, userTeamsDAO, teamsLeagueDAO, user, adminManaguer);

        //Vistas
        LoginGUI loginGUI = new LoginGUI();
        StatisticsGUI statisticsGUI = new StatisticsGUI();
        SimulationGameGUI simulationGameGUI = new SimulationGameGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MainPanelGUI mainPanelGUI = new MainPanelGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        NewLeagueGUI newLeaguesGUI = new NewLeagueGUI();
        RegistrationGUI registrationGUI = new RegistrationGUI();
        TopPanelGUI topPanelGUI = new TopPanelGUI();
        DeleteGUI deleteGUI = new DeleteGUI();
        ShowLeague showLeague = new ShowLeague();
        TeamsListGUI teamsListGUI = new TeamsListGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, newLeaguesGUI, registrationGUI, mainPanelGUI, deleteGUI, showLeague, statisticsGUI,simulationGameGUI, teamsListGUI);

        //Controllers
        MainPanelController mainPanelController = new MainPanelController(mainFrame);
        RegistrationController registrationController =  new RegistrationController(mainFrame, registrationGUI, userManager);
        LoginController loginController = new LoginController(mainFrame, loginGUI, userManager);
        ChangePasswordController changePasswordController = new ChangePasswordController(mainFrame, changePasswordGUI, userManager);
        MenuAdminController menuAdminController = new MenuAdminController(mainFrame);
        //TopPanelController topPanelController =  new TopPanelController(topPanelGUI, mainFrame, userManager);
        DeleteController deleteController = new DeleteController(mainFrame, deleteGUI, userManager);
        NewLeagueController newLeagueController = new NewLeagueController(mainFrame);

        //Buttons
        loginGUI.actionListener(loginController);
        loginGUI.focusListener(loginController);
        mainPanelGUI.registerListener(mainPanelController);
        registrationGUI.foscusListener(registrationController);
        registrationGUI.registerRegistration(registrationController);
        changePasswordGUI.registerChangePassword(changePasswordController);
        changePasswordGUI.actionListenerPassword(changePasswordController);
        deleteGUI.actionListener(deleteController);
        deleteGUI.focusListener(deleteController);
        menuAdminGUI.menuAdminListener(menuAdminController);
        newLeaguesGUI.newLeagueFocusListener(newLeagueController);
        newLeaguesGUI.registerListener(newLeagueController);



        //UserDAO userDAO = new UserDAO();


        //userDAO.InsertDataUser("267598111c","borjhs@gmail.com",2,"holaqtal",5,"554322233");
    }
}
