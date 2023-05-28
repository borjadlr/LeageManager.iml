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

public class MenuAdminController implements ActionListener {

    private final MenuAdminGUI view;
    private final MainFrameGUI mainFrameGUI;
    private final LeagueManager leagueManager;
    private final ListLeagueAdminGUI listLeagueAdminGUI;
    private final MatchManager matchManager;
    private final SimulationLeagueGUI simulationLeagueGUI;
    private int i;

    public MenuAdminController(MainFrameGUI mainFrame, LeagueManager leagueManager, MenuAdminGUI view, ListLeagueAdminGUI listLeagueAdminGUI, MatchManager matchManager, SimulationLeagueGUI simulationLeagueGUI){
        this.leagueManager = leagueManager;
        this.mainFrameGUI = mainFrame;
        this.view = view;
        this.listLeagueAdminGUI = listLeagueAdminGUI;
        this.matchManager = matchManager;
        this.simulationLeagueGUI = simulationLeagueGUI;
        this.i = 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "CREATE_NEW_ACCOUNT":
                    mainFrameGUI.showLogin();
                    break;
                case "CREATE_NEW_TEAM":
                    mainFrameGUI.showNewTeam();
                    break;
                case "NEW_LEAGUE":
                    mainFrameGUI.showMenuNewLeague();
                    break;
                case "VIEW_LEAGUES":
                    try {
                        List<League> leagues = leagueManager.listLeagues();
                        listLeagueAdminGUI.addLeagues(leagues);
                        mainFrameGUI.showLeague();
                    } catch (SQLException | NullPointerException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    break;
                case "STATISTICS_VIEW":
                    mainFrameGUI.showStatistics();
                    break;
                case "VIEW_MATCHES":
                    try {
                        matchManager.simularPartidos(leagueManager.getAllMatches());
                        simulationLeagueGUI.addMatch(matchManager.getAllMatchesByJornada(i));
                        mainFrameGUI.showSimulationLeagueView();
                        i++;
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
            }
        }
    }
}
