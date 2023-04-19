import Presentation.Views.*;

public class Main {

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        MenuUserGUI menuUserGUI = new MenuUserGUI();
        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
        ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI();
        CurrentLeaguesGUI currentLeagueGUI = new CurrentLeaguesGUI();
        MainFrameGUI mainFrame = new MainFrameGUI(loginGUI, menuUserGUI, menuAdminGUI, changePasswordGUI, currentLeagueGUI);
    }
}
