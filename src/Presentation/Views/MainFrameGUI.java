package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainFrameGUI class represents the main frame of the graphical user interface.
 * It contains different panels and uses a CardLayout to switch between them based on user actions.
 */

public class MainFrameGUI extends JFrame{
    private static final ArrayList<String> recorregutVistas = new ArrayList<>();
    private static final List<List<Boolean>> booleanPacks = new ArrayList<>();
    private final String LOGIN_VIEW = "LOGIN_VIEW";
    private final String  MENU_USER_VIEW = "MENU_USER_VIEW";
    private final String MENU_ADMIN_VIEW = "MENU_ADMIN_VIEW";
    private final String FIRST_UI = "FIRST_UI";
    private final String MENU_NEW_LEAGUE = "MENU_NEW_LEAGUE";
    private final String CHANGE_PASSWORD_VIEW = "CHANGE_PASSWORD_VIEW";
    private final String REGISTRATION_VIEW = "REGISTRATION_VIEW";
    private final String DELETE_VIEW = "DELETE_VIEW";
    private final String LEAGUE_LIST = "LEAGUE_LIST";
    private final String STATISTICS_VIEW = "STATISTICS_VIEW";
    private final String CREATE_NEW_TEAM = "CREATE_NEW_TEAM";
    private final String SHOW_USERS_TEAM = "SHOW_USERS_TEAM";
    private final String SIMULATION_LEAGUE_VIEW = "SIMULATION_LEAGUE_VIEW";
    private final String TEAM_LIST_CREATE_LEAGUE = "TEAM_LIST_CREATE_LEAGUE";
    private final String LIST_TEAM_ADMIN_VIEW = "LIST_TEAM_ADMIN_VIEW";
    private final String LEAGUE_USER_LIST = "LEAGUE_USER_LIST";
    private int auxiliar = 0;
    private final JPanel topPanel;
    private final JPanel centerPanel;
    private final JPanel bottomPanel;
    private final CardLayout cardLayout;


    /**
     * Constructs a MainFrameGUI object with the specified panels.
     *
     * @param userLoginGUI          the login panel
     * @param menuUserGUI           the user menu panel
     * @param menuAdminGUI          the admin menu panel
     * @param changePasswordGUI     the change password panel
     * @param newLeaguesGUI         the new leagues panel
     * @param registrationGUI       the registration panel
     * @param mainPanelGUI          the main panel
     * @param deleteGUI             the delete panel
     * @param listLeagueUserGUI     the list league (user) panel
     * @param statisticsGUI         the statistics panel
     * @param simulationGameGUI     the simulation game panel
     * @param topPanelGUI           the top panel
     * @param bottomPanelGUI        the bottom panel
     * @param newTeamGUI            the new team panel
     * @param listLeagueAdminGUI    the list league (admin) panel
     * @param teamListCreateLeague  the team list (create league) panel
     * @param simulationLeagueGUI   the simulation league panel
     * @param listTeamAdminGUI      the list team (admin) panel
     */
    public MainFrameGUI(LoginGUI userLoginGUI, MenuUserGUI menuUserGUI, MenuAdminGUI menuAdminGUI, ChangePasswordGUI changePasswordGUI, NewLeagueGUI newLeaguesGUI, RegistrationGUI registrationGUI, MainPanelGUI mainPanelGUI, DeleteGUI deleteGUI, ListPlayerGUI listLeagueUserGUI, StatisticsGUI statisticsGUI, SimulationGameGUI simulationGameGUI, TopPanelGUI topPanelGUI, BottomPanelGUI bottomPanelGUI, NewTeamGUI newTeamGUI, ListLeagueAdminGUI listLeagueAdminGUI, TeamListCreateLeague teamListCreateLeague, SimulationLeagueGUI simulationLeagueGUI, ListTeamAdminGUI listTeamAdminGUI){

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
        centerPanel.add(listLeagueAdminGUI, LEAGUE_LIST);
        centerPanel.add(listLeagueUserGUI, LEAGUE_USER_LIST);
        String SIMULATION_GAME_VIEW = "SIMULATION_GAME_VIEW";
        centerPanel.add(simulationGameGUI, SIMULATION_GAME_VIEW);
        centerPanel.add(simulationLeagueGUI, SIMULATION_LEAGUE_VIEW);
        centerPanel.add(statisticsGUI, STATISTICS_VIEW);
        String TEAM_LIST_VIEW = "TEAM_LIST_VIEW";
        centerPanel.add(listTeamAdminGUI, TEAM_LIST_VIEW);
        centerPanel.add(newTeamGUI, CREATE_NEW_TEAM);
        centerPanel.add(teamListCreateLeague, TEAM_LIST_CREATE_LEAGUE);
        centerPanel.add(listTeamAdminGUI, LIST_TEAM_ADMIN_VIEW);
        centerPanel.add(listLeagueUserGUI, SHOW_USERS_TEAM);

        auxiliar--;
        showMainPanel();

        setSize(1000,1200);
        setVisible(true);

    }

    /**
     * Displays the main panel.
     */

    public void showMainPanel() {
        bottomPanel.setVisible(false);
        topPanel.setVisible(false);
        cardLayout.show(centerPanel, FIRST_UI);
        auxiliar++;
        updateRecorregut(FIRST_UI, false, false);
    }

    public void showUsersLeague(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, LEAGUE_USER_LIST);
        auxiliar++;
        updateRecorregut(LEAGUE_USER_LIST, true, false);
    }


    /**
     * Displays the team user list.
     */

    public void showUserList() {
        bottomPanel.setVisible(false);
        topPanel.setVisible(true);
        cardLayout.show(centerPanel, SHOW_USERS_TEAM);
        auxiliar++;
        updateRecorregut(SHOW_USERS_TEAM, true, false);
    }

    /**
     * Displays simulation league view.
     */

    public void showSimulationLeagueView(){
        bottomPanel.setVisible(true);
        topPanel.setVisible(true);
        cardLayout.show(centerPanel, SIMULATION_LEAGUE_VIEW);
        auxiliar++;
        updateRecorregut(SIMULATION_LEAGUE_VIEW, true, true);
    }

    /**
     * Displays the team list admin.
     */
    public void showTeamListAdminView(){
        bottomPanel.setVisible(false);
        topPanel.setVisible(true);
        cardLayout.show(centerPanel, LIST_TEAM_ADMIN_VIEW);
        auxiliar++;
        updateRecorregut(LIST_TEAM_ADMIN_VIEW, true, false);
    }
    /**
     * Displays the previous panel.
     */
    public void showGoBack() {
        recorregutVistas.remove(auxiliar);
        auxiliar--;
        cardLayout.show(centerPanel, recorregutVistas.get(auxiliar));
        topPanel.setVisible(booleanPacks.get(auxiliar).get(0));
        bottomPanel.setVisible(booleanPacks.get(auxiliar).get(1));
    }
    /**
     * Displays the login.
     */
    public void showLogin() {
        topPanel.setVisible(true);

        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, LOGIN_VIEW);
        auxiliar++;
        updateRecorregut(LOGIN_VIEW, true, false);
    }
    /**
     * Displays the register view.
     */
    public void showRegister() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, REGISTRATION_VIEW);
        auxiliar++;
        updateRecorregut(REGISTRATION_VIEW, true, false);
    }
    /**
     * Displays the teams new league.
     */
    public void showTeamsNewLeague(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, TEAM_LIST_CREATE_LEAGUE);
        auxiliar++;
        updateRecorregut(TEAM_LIST_CREATE_LEAGUE, true, false);
    }
    /**
     * Displays the user menu.
     */
    public void showMenuUser() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, MENU_USER_VIEW);
        auxiliar++;
        updateRecorregut(MENU_USER_VIEW, true, true);
    }
    /**
     * Displays the change password view.
     */
    public void showChangePassword() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, CHANGE_PASSWORD_VIEW);
        auxiliar++;
        updateRecorregut(CHANGE_PASSWORD_VIEW, true, false);
    }
    /**
     * Displays teams view.
     */
    public void showTeamsView() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        String SHOW_TEAMS_VIEW = "SHOW_TEAMS_VIEW";
        cardLayout.show(centerPanel, SHOW_TEAMS_VIEW);
        auxiliar++;
        updateRecorregut(SHOW_TEAMS_VIEW, true, true);
    }
    /**
     * Displays the main panel.
     */
    public void showNewTeam() {
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, CREATE_NEW_TEAM);
        auxiliar++;
        updateRecorregut(CREATE_NEW_TEAM, true, false);
    }
    /**
     * Makes an update.
     */
    public void updateRecorregut(String vista, boolean top, boolean bottom) {
        recorregutVistas.add(auxiliar, vista);
        List<Boolean> pack = new ArrayList<>();
        pack.add(top);
        pack.add(bottom);
        booleanPacks.add(auxiliar, pack);
    }

    /**
     * Displays the league.
     */
    public void showLeague(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, LEAGUE_LIST);
        auxiliar++;
        updateRecorregut(LEAGUE_LIST, true, false);
    }
    /**
     * Displays admin menu.
     */
    public void showMenuAdmin(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(false);
        cardLayout.show(centerPanel, MENU_ADMIN_VIEW);
        auxiliar++;
        updateRecorregut(MENU_ADMIN_VIEW, true, false);
    }
    /**
     * Displays the main panel.
     */
    public void deleteAccount(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, DELETE_VIEW);
        auxiliar++;
        updateRecorregut(DELETE_VIEW, true, true);
    }
    /**
     * Displays the menu new league.
     */
    public void showMenuNewLeague(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, MENU_NEW_LEAGUE);
        auxiliar++;
        updateRecorregut(MENU_NEW_LEAGUE, true, false);
    }
    /**
     * Displays the statistics view.
     */
    public void showStatistics(){
        topPanel.setVisible(true);
        bottomPanel.setVisible(true);
        cardLayout.show(centerPanel, STATISTICS_VIEW);
        auxiliar++;
        updateRecorregut(STATISTICS_VIEW, true, true);
    }
}