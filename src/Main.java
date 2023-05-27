import Business.Entities.League;
import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.*;
import Persistance.dao.TeamsDAO;
import Persistance.dao.TeamsLeagueDAO;
import Persistance.dao.*;
import Persistance.dao.UserTeamsDAO;
import Presentation.Controllers.*;
import Presentation.Views.*;

public class Main {

    public static void main(String[] args) {

        //Entities
        Team team = new Team();
        League league = new League();


        //Dao
        UserDAO userDAO = new UserDAO();
        TeamsDAO teamsDAO = new TeamsDAO();
        UserTeamsDAO userTeamsDAO = new UserTeamsDAO();
        TeamsLeagueDAO teamsLeagueDAO = new TeamsLeagueDAO();
        LeagueDAO leagueDAO = new LeagueDAO();

        //Managers
        User user = new User();
        TeamManager teamManager = new TeamManager(teamsDAO, teamsLeagueDAO, userTeamsDAO, team);
        LeagueManager leagueManager = new LeagueManager(teamManager, leagueDAO, teamsDAO);
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
        BottomPanelGUI bottomPanelGUI = new BottomPanelGUI();
        DeleteGUI deleteGUI = new DeleteGUI();
        ListLeagueUserGUI listLeagueUserGUI = new ListLeagueUserGUI();
        ListTeamUserGUI teamsListGUI = new ListTeamUserGUI();
        NewTeamGUI newTeamGUI = new NewTeamGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, newLeaguesGUI, registrationGUI, mainPanelGUI, deleteGUI, listLeagueUserGUI, statisticsGUI,simulationGameGUI, teamsListGUI, topPanelGUI, bottomPanelGUI, newTeamGUI);

        //Controllers
        MainPanelController mainPanelController = new MainPanelController(mainFrame, topPanelGUI);
        RegistrationController registrationController =  new RegistrationController(mainFrame, registrationGUI, userManager);
        LoginController loginController = new LoginController(mainFrame, loginGUI, userManager, topPanelGUI);
        ChangePasswordController changePasswordController = new ChangePasswordController(mainFrame, changePasswordGUI, userManager);
        MenuAdminController menuAdminController = new MenuAdminController(mainFrame, leagueManager, listLeagueUserGUI, menuAdminGUI);
        TopPanelController topPanelController =  new TopPanelController(mainFrame, topPanelGUI);
        DeleteController deleteController = new DeleteController(mainFrame, deleteGUI, userManager);
        NewLeagueController newLeagueController = new NewLeagueController(mainFrame, newLeaguesGUI);
        ListLeagueUserController listLeagueUserController = new ListLeagueUserController(listLeagueUserGUI, mainFrame,teamsListGUI, leagueManager, league);
        BottomPanelController bottomPanelController = new BottomPanelController(mainFrame);
        NewTeamController newTeamController = new NewTeamController(mainFrame, newTeamGUI, teamManager);
        MenuUserController menuUserController = new MenuUserController(mainFrame, menuUserGUI);

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
        listLeagueUserGUI.setController(listLeagueUserController);
        bottomPanelGUI.changePasswordListener(bottomPanelController);
        topPanelGUI.actionListener(topPanelController);
        newTeamGUI.actionListener(newTeamController);
        menuUserGUI.registerListener(menuUserController);

        //UserDAO userDAO = new UserDAO();


        //userDAO.InsertDataUser("267598111c","borjhs@gmail.com",2,"holaqtal",5,"554322233");
    }
}
