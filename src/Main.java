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

        //Dao
        UserDAO userDAO = new UserDAO();
        TeamsDAO teamsDAO = new TeamsDAO();



        //Managers
        User user = new User();
        //Vistas
        LoginGUI loginGUI = new LoginGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MainPanelGUI mainPanelGUI = new MainPanelGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        CurrentLeaguesGUI currentLeagueGUI = new CurrentLeaguesGUI();
        NewLeagueGUI newLeaguesGUI = new NewLeagueGUI();
        RegistrationGUI newRegistration = new RegistrationGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, currentLeagueGUI, newLeaguesGUI, newRegistration, mainPanelGUI);

        //Controllers
        MainPanelController mainPanelController = new MainPanelController(mainFrame);
        LoginController loginController = new LoginController(mainFrame, loginGUI, userDAO);
        loginGUI.actionListener(loginController);
        loginGUI.focusListener(loginController);

        //Buttons
        mainPanelGUI.registerListener(mainPanelController);

        //UserDAO userDAO = new UserDAO();


        //userDAO.InsertDataUser("267598111c","borjhs@gmail.com",2,"holaqtal",5,"554322233");
    }
}
