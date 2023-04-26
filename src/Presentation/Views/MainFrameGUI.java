package Presentation.Views;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class MainFrameGUI extends JFrame{
    public final String LOGIN_VIEW = "LOGIN_VIEW";
    public final String REGISTER_VIEW = "REGISTER_VIEW";
    public final String MENU_USER_VIEW = "MENU_USER_VIEW";
    public final String MENU_ADMIN_VIEW = "MENU_ADMIN_VIEW";
    public final String MENU_NEW_LEAGUE = "MENU_NEW_LEAGUE";
    public final String CHANGE_PASSWORD_VIEW = "CHANGE_PASSWORD_VIEW";
    public final String CURRENT_LEAGUE_VIEW = "CURRENT_LEAGUE_VIEW";
    public final String REGISTRATION_VIEW = "REGISTRATION_VIEW";
    private CardLayout cardLayout;

    public MainFrameGUI(LoginGUI userLoginGUI, MenuUserGUI menuUserGUI, MenuAdminGUI menuAdminGUI, ChangePasswordGUI changePasswordGUI, CurrentLeaguesGUI currentLeagueGUI, NewLeagueGUI newLeaguesGUI, RegistrationGUI registrationGUI){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        add(userLoginGUI, LOGIN_VIEW); //comentar estas lineas para poner a prueba otras views
        //add(menuUserGUI, MENU_USER_VIEW); //comentar estas lineas para poner a prueba otras views
        //add(menuAdminGUI, MENU_ADMIN_VIEW); //comentar estas lineas para poner a prueba otras views
        //add (changePasswordGUI, CHANGE_PASSWORD_VIEW);
        //add (currentLeagueGUI, CURRENT_LEAGUE_VIEW);
        add(newLeaguesGUI, MENU_NEW_LEAGUE);
        add(registrationGUI, REGISTRATION_VIEW);
        setSize(600,650);
        cardLayout.show(getContentPane(),"Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("LeagueManager");
    }

    public void showLogin() {
        cardLayout.show(getContentPane(),LOGIN_VIEW);
    }

    public void showRegister(){
        cardLayout.show(getContentPane(),REGISTER_VIEW);
    }

    public void showMessageToUser(String message) {
        showMessageDialog(null, message);
    }

    public boolean confirmDeleteAccount() {
        int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete your account?", "Select an Option...", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            return true;
        }
        return false;
    }

}