package Presentation.Controllers;

import Business.Entities.League;
import Business.Managers.LeagueManager;
import Business.Managers.MatchManager;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * The controller class for the MenuAdminGUI.
 * Handles the actions in the admin menu view.
 */
public class MenuAdminController implements ActionListener {

    private final MenuAdminGUI view;
    private final MainFrameGUI mainFrameGUI;
    private final LeagueManager leagueManager;
    private final ListLeagueAdminGUI listLeagueAdminGUI;
    private final MatchManager matchManager;
    private final SimulationLeagueGUI simulationLeagueGUI;
    private static final String CREATE_NEW_TEAM = "CREATE_NEW_TEAM";
    private static final String CREATE_NEW_ACCOUNT = "CREATE_NEW_ACCOUNT";
    private static final String NEW_LEAGUE = "NEW_LEAGUE";
    private static final String VIEW_LEAGUES = "VIEW_LEAGUES";
    private static final String VIEW_STATISTICS = "STATISTICS_VIEW";
    private static final String VIEW_MATCHES = "VIEW_MATCHES";
    private int i;

    /**
     * Constructs a MenuAdminController object.
     *
     * @param mainFrame        The MainFrameGUI instance.
     * @param leagueManager    The LeagueManager instance.
     * @param view             The MenuAdminGUI instance.
     * @param listLeagueAdminGUI The ListLeagueAdminGUI instance.
     * @param matchManager     The MatchManager instance.
     * @param simulationLeagueGUI The SimulationLeagueGUI instance.
     */
    public MenuAdminController(MainFrameGUI mainFrame, LeagueManager leagueManager, MenuAdminGUI view, ListLeagueAdminGUI listLeagueAdminGUI, MatchManager matchManager, SimulationLeagueGUI simulationLeagueGUI){
        this.leagueManager = leagueManager;
        this.mainFrameGUI = mainFrame;
        this.view = view;
        this.listLeagueAdminGUI = listLeagueAdminGUI;
        this.matchManager = matchManager;
        this.simulationLeagueGUI = simulationLeagueGUI;
        this.i = 1;
    }

    /**
     * Handles the action events in the admin menu view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case CREATE_NEW_ACCOUNT:
                    mainFrameGUI.showLogin();
                    break;
                case CREATE_NEW_TEAM:
                    mainFrameGUI.showNewTeam();
                    break;
                case NEW_LEAGUE:
                    mainFrameGUI.showMenuNewLeague();
                    break;
                case VIEW_LEAGUES:
                    try {
                        List<League> leagues = leagueManager.listLeagues();
                        listLeagueAdminGUI.addLeagues(leagues);
                        mainFrameGUI.showLeague();
                    } catch (SQLException | NullPointerException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    break;
                case VIEW_STATISTICS:
                    mainFrameGUI.showStatistics();
                    break;
                case VIEW_MATCHES:
                    try {
                        matchManager.simularPartidos(leagueManager.getAllMatches());
                        simulationLeagueGUI.addMatch(matchManager.getAllMatchesByJornada(i));
                        mainFrameGUI.showSimulationLeagueView();
                        i++;
                    } catch (SQLException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    break;
            }
        }
    }
}
