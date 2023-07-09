package Presentation.Controllers;
import Business.Entities.League;
import Business.Managers.LeagueManager;
import Presentation.Views.ListLeagueUserGUI;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.MenuUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * The controller class for the MenuUserGUI.
 * Handles the actions in the user menu view.
 */
public class MenuUserController implements ActionListener {

    private final MainFrameGUI mainFrameGUI;
    private final LeagueManager leagueManager;
    private final ListLeagueUserGUI listLeagueUserGUI;
    private final MenuUserGUI view;

    /**
     * Constructs a MenuUserController object.
     *
     * @param mainFrameGUI The MainFrameGUI instance.
     */
    public MenuUserController(MainFrameGUI mainFrameGUI, LeagueManager leagueManager, ListLeagueUserGUI listLeagueUserGUI, MenuUserGUI menuUserGUI) {
        this.mainFrameGUI = mainFrameGUI;
        this.leagueManager = leagueManager;
        this.listLeagueUserGUI = listLeagueUserGUI;
        this.view = menuUserGUI;
    }

    /**
     * Handles the action events in the user menu view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof JButton) {
                switch (e.getActionCommand()) {
                    case "WATCH_MATCHES":
                        mainFrameGUI.showSimulationLeagueView();
                        break;
                    case "VIEW_AVAIABLE_LEAGUES":
                        mainFrameGUI.showLeague();
                        break;
                    case "VIEW_STATISTICS":
                        mainFrameGUI.showStatistics();
                        break;
                    case "VIEW_LEAGUES":
                        try {
                            List<League> leagues = leagueManager.listLeagues();
                            listLeagueUserGUI.addLeagues(leagues);
                            mainFrameGUI.showLeague();
                        } catch (SQLException | NullPointerException ex) {
                            view.exceptionMessage(ex.getMessage());
                        }
                        break;
                }
            }
        }
    }

