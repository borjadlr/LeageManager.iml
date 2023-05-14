import Persistance.dao.UserDAO;
import Presentation.Controllers.MainPanelController;
import Presentation.Views.*;

public class Main {

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MainPanelGUI mainPanelGUI = new MainPanelGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        CurrentLeaguesGUI currentLeagueGUI = new CurrentLeaguesGUI();
        NewLeagueGUI newLeaguesGUI = new NewLeagueGUI();
        RegistrationGUI newRegistration = new RegistrationGUI();

        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, currentLeagueGUI, newLeaguesGUI, newRegistration, mainPanelGUI);
        MainPanelController mainPanelController = new MainPanelController(mainFrame);

        mainPanelGUI.registerListener(mainPanelController);

        UserDAO userDAO = new UserDAO();


        userDAO.InsertDataUser("267598111c","borjhs@gmail.com",2,"holaqtal",5,"554322233");
    }
}
