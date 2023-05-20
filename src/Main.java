import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.*;
import Persistance.dao.TeamsDAO;
import Persistance.dao.UserDAO;
import Presentation.Controllers.*;
import Presentation.Views.*;

public class Main {

    public static void main(String[] args) {

        //Entities
        Team team = new Team();

        //Dao
        UserDAO userDAO = new UserDAO();
        TeamsDAO teamsDAO = new TeamsDAO();



        //Managers
        User user = new User();
        AdminManager adminManaguer = new AdminManager(userDAO);
        TeamManager teamManager = new TeamManager(teamsDAO, team);
        LeagueManager leagueManager = new LeagueManager(teamManager);
        UserManager userManager = new UserManager(userDAO, leagueManager, teamManager, user, adminManaguer);

        //Vistas
        LoginGUI loginGUI = new LoginGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MainPanelGUI mainPanelGUI = new MainPanelGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        CurrentLeaguesGUI currentLeagueGUI = new CurrentLeaguesGUI();
        NewLeagueGUI newLeaguesGUI = new NewLeagueGUI();
        RegistrationGUI registrationGUI = new RegistrationGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, currentLeagueGUI, newLeaguesGUI, registrationGUI, mainPanelGUI);

        //Controllers
        MainPanelController mainPanelController = new MainPanelController(mainFrame);
        RegistrationController registrationController =  new RegistrationController(mainFrame, registrationGUI, userManager);
        LoginController loginController = new LoginController(mainFrame, loginGUI, userManager);
        ChangePasswordController changePasswordController = new ChangePasswordController(mainFrame);
        loginGUI.actionListener(loginController);
        loginGUI.focusListener(loginController);

        //Buttons
        mainPanelGUI.registerListener(mainPanelController);
        registrationGUI.foscusListener(registrationController);
        registrationGUI.registerRegistration(registrationController);
        changePasswordGUI.registerChangePassword(changePasswordController);
        changePasswordGUI.actionListenerPassword(changePasswordController);

        //UserDAO userDAO = new UserDAO();


        //userDAO.InsertDataUser("267598111c","borjhs@gmail.com",2,"holaqtal",5,"554322233");
    }
}
