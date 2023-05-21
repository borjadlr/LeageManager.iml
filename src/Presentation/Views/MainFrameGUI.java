package Presentation.Views;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class MainFrameGUI extends JFrame{
    public final String LOGIN_VIEW = "LOGIN_VIEW";
    public final String  MENU_USER_VIEW = "MENU_USER_VIEW";
    public final String MENU_ADMIN_VIEW = "MENU_ADMIN_VIEW";
    public final String MAIN_PANEL = "MAIN_PANEL";
    public final String FIRST_UI = "FIRST_UI";
    public final String MENU_NEW_LEAGUE = "MENU_NEW_LEAGUE";
    public final String CHANGE_PASSWORD_VIEW = "CHANGE_PASSWORD_VIEW";
    public final String CURRENT_LEAGUE_VIEW = "CURRENT_LEAGUE_VIEW";
    public final String REGISTRATION_VIEW = "REGISTRATION_VIEW";
    public final String DELETE_VIEW = "DELETE_VIEW";
    public final String SHOW_LEAGUE = "SHOW_LEAGUE";
    public final String SIMULATION_VIEW = "SIMULATION_VIEW";
    public final String STATISTICS_VIEW = "STATISTICS_VIEW";
    public final String TEAM_LIST_VIEW = "TEAM_LIST_VIEW";


    private final JPanel centerPanel;
    private final JPanel bottomPanel;
    private final CardLayout cardLayout;



    public MainFrameGUI(LoginGUI userLoginGUI, MenuUserGUI menuUserGUI, MenuAdminGUI menuAdminGUI, ChangePasswordGUI changePasswordGUI, NewLeagueGUI newLeaguesGUI, RegistrationGUI registrationGUI, MainPanelGUI mainPanelGUI, DeleteGUI deleteGUI, ShowLeague showLeague, StatisticsGUI statisticsGUI, SimulationGameGUI simulationGameGUI, TeamListGUI teamsListGUI){

        super("Main Frame");

        setLayout(new BorderLayout());

        centerPanel = new JPanel();
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topPanel = new TopPanelGUI();
        add(topPanel, BorderLayout.NORTH);

        centerPanel.add(mainPanelGUI, FIRST_UI);
        centerPanel.add(userLoginGUI, LOGIN_VIEW);
        centerPanel.add(menuUserGUI, MENU_USER_VIEW);
        centerPanel.add(menuAdminGUI, MENU_ADMIN_VIEW);
        centerPanel.add(changePasswordGUI,CHANGE_PASSWORD_VIEW);
        centerPanel.add(newLeaguesGUI, MENU_NEW_LEAGUE);
        centerPanel.add(registrationGUI, REGISTRATION_VIEW);
        centerPanel.add(deleteGUI, DELETE_VIEW);
        centerPanel.add(showLeague, SHOW_LEAGUE);
        centerPanel.add(simulationGameGUI, SIMULATION_VIEW);
        centerPanel.add(statisticsGUI, STATISTICS_VIEW);
        centerPanel.add(teamsListGUI, TEAM_LIST_VIEW);

        cardLayout.show(centerPanel, FIRST_UI);

        bottomPanel = new BottomPanelGUI();
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(1000,1200);
        setVisible(true);
    }

    public void showLogin() {
        //topPanel.setVisible(false);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel,LOGIN_VIEW);
    }

    public void showRegister() {
        cardLayout.show(centerPanel, REGISTRATION_VIEW);
        bottomPanel.setVisible(false);
    }

    public  void showMainPanel(){
        cardLayout.show(centerPanel, MAIN_PANEL);
    }

    public void showChangePassword(){
        cardLayout.show(centerPanel, CHANGE_PASSWORD_VIEW);
    }

    public void showMenuUser() {
        cardLayout.show(centerPanel, MENU_USER_VIEW);
    }
    public void showLeague(){ cardLayout.show(centerPanel, SHOW_LEAGUE);}
    public void showMenuAdmin(){
        cardLayout.show(centerPanel, MENU_ADMIN_VIEW);
    }

    public void showTeamList(){
        cardLayout.show(centerPanel, TEAM_LIST_VIEW);
    }

    public void showCurrentLeagues(){
        cardLayout.show(centerPanel, CURRENT_LEAGUE_VIEW);
    }


    public void showMenuNewLeague(){
        cardLayout.show(centerPanel, MENU_NEW_LEAGUE);
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