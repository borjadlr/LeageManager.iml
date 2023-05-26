package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
public class MainFrameGUI extends JFrame{
    private static ArrayList<String> recorregutVistas = new ArrayList<>();
    private static List<List<Boolean>> booleanPacks = new ArrayList<>();
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
    public final String LEAGUE_LIST = "LEAGUE_LIST";
    public final String SIMULATION_VIEW = "SIMULATION_VIEW";
    public final String STATISTICS_VIEW = "STATISTICS_VIEW";
    public final String TEAM_LIST_VIEW = "TEAM_LIST_VIEW";
    private final String CREATE_NEW_TEAM = "CREATE_NEW_TEAM";
    private final String SHOW_TEAMS_VIEW = "SHOW_TEAMS_VIEW";
    private int auxiliar = 0;
    private  JPanel topPanel;
    private  JPanel centerPanel;
    private  JPanel bottomPanel;
    private final CardLayout cardLayout;




    public MainFrameGUI(LoginGUI userLoginGUI, MenuUserGUI menuUserGUI, MenuAdminGUI menuAdminGUI, ChangePasswordGUI changePasswordGUI, NewLeagueGUI newLeaguesGUI, RegistrationGUI registrationGUI, MainPanelGUI mainPanelGUI, DeleteGUI deleteGUI, ShowLeague showLeague, StatisticsGUI statisticsGUI, SimulationGameGUI simulationGameGUI, TeamListGUI teamsListGUI, TopPanelGUI topPanelGUI, BottomPanelGUI bottomPanelGUI, NewTeamGUI newTeamGUI){

        super("Main Frame");

        setLayout(new BorderLayout());

        centerPanel = new JPanel();
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        add(centerPanel, BorderLayout.CENTER);

        topPanel = topPanelGUI;
        add(topPanel, BorderLayout.NORTH);

        bottomPanel = bottomPanelGUI;
        add(bottomPanel, BorderLayout.SOUTH);

        centerPanel.add(mainPanelGUI, FIRST_UI);
        centerPanel.add(userLoginGUI, LOGIN_VIEW);
        centerPanel.add(menuUserGUI, MENU_USER_VIEW);
        centerPanel.add(menuAdminGUI, MENU_ADMIN_VIEW);
        centerPanel.add(changePasswordGUI,CHANGE_PASSWORD_VIEW);
        centerPanel.add(newLeaguesGUI, MENU_NEW_LEAGUE);
        centerPanel.add(registrationGUI, REGISTRATION_VIEW);
        centerPanel.add(deleteGUI, DELETE_VIEW);
        centerPanel.add(showLeague, LEAGUE_LIST);
        centerPanel.add(simulationGameGUI, SIMULATION_VIEW);
        centerPanel.add(statisticsGUI, STATISTICS_VIEW);
        centerPanel.add(teamsListGUI, TEAM_LIST_VIEW);
        centerPanel.add(newTeamGUI, CREATE_NEW_TEAM);
        
        //FIRST UI
        auxiliar--;
        showMainPanel();

        setSize(1000,1200);
        setVisible(true);

    }

    public void showMainPanel() {
        bottomPanel.setVisible(false);
        topPanel.setVisible(false);
        cardLayout.show(centerPanel, FIRST_UI);
        auxiliar++;
        updateRecorregut(FIRST_UI, false, false);
    }

    public void showGoBack() {
        recorregutVistas.remove(auxiliar);
        auxiliar--;
        cardLayout.show(centerPanel, recorregutVistas.get(auxiliar));
        topPanel.setVisible(booleanPacks.get(auxiliar).get(0));
        bottomPanel.setVisible(booleanPacks.get(auxiliar).get(1));
    }

    public void showLogin() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, LOGIN_VIEW);
        auxiliar++;
        updateRecorregut(LOGIN_VIEW, true, false);
        //
    }

    public void showRegister() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, REGISTRATION_VIEW);
        auxiliar++;
        updateRecorregut(REGISTRATION_VIEW, true, false);
    }

    public void showNewLeague() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, MENU_NEW_LEAGUE);
        auxiliar++;
        updateRecorregut(MENU_NEW_LEAGUE, true, true);
    }

    public void showMenuUser() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, MENU_USER_VIEW);
        auxiliar++;
        updateRecorregut(MENU_USER_VIEW, true, true);
    }

    public void showChangePassword() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, CHANGE_PASSWORD_VIEW);
        auxiliar++;
        updateRecorregut(CHANGE_PASSWORD_VIEW, true, false);
    }

    public void showTeamsView() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, SHOW_TEAMS_VIEW);
        auxiliar++;
        updateRecorregut(SHOW_TEAMS_VIEW, true, true);
    }

    public void showNewTeam() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, CREATE_NEW_TEAM);
        auxiliar++;
        updateRecorregut(CREATE_NEW_TEAM, true, true);
    }

    public void updateRecorregut(String vista, boolean top, boolean bottom) {
        recorregutVistas.add(auxiliar, vista);
        List<Boolean> pack = new ArrayList<>();
        pack.add(top);
        pack.add(bottom);
        booleanPacks.add(auxiliar, pack);
    }
    public void showLeague(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, LEAGUE_LIST);
        auxiliar++;
        updateRecorregut(LEAGUE_LIST, true, true);
    }
    public void showMenuAdmin(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, MENU_ADMIN_VIEW);
        auxiliar++;
        updateRecorregut(MENU_ADMIN_VIEW, true, false);
    }

    public void showTeamList(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, TEAM_LIST_VIEW);
        auxiliar++;
        updateRecorregut(TEAM_LIST_VIEW, true, true);
    }
    public void deleteAccount(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, DELETE_VIEW);
        auxiliar++;
        updateRecorregut(DELETE_VIEW, true, true);
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