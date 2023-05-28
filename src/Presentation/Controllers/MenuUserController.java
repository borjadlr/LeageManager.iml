package Presentation.Controllers;

import Business.Entities.League;
import Business.Entities.Match;
import Business.Entities.User;
import Business.Managers.LeagueManager;
import Business.Managers.MatchManager;
import Business.Managers.UserManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.MenuUserGUI;
import Presentation.Views.SimulationGameGUI;
import Presentation.Views.SimulationLeagueGUI;
import Presentation.Views.StatisticsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class MenuUserController implements ActionListener {

    private final MainFrameGUI mainFrameGUI;
    private final MenuUserGUI view;
    private final StatisticsGUI statisticsGUI;
    private final UserManager userManager;
    private final SimulationLeagueGUI simulationLeagueGUI;

    public MenuUserController(MainFrameGUI mainFrameGUI, MenuUserGUI view, StatisticsGUI statisticsGUI, UserManager userManager, SimulationLeagueGUI simulationLeagueGUI) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
        this.statisticsGUI = statisticsGUI;
        this.userManager = userManager;
        this.simulationLeagueGUI = simulationLeagueGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof JButton) {
                switch (e.getActionCommand()) {
                    case "WATCH_MATCHES":
                            //simulationLeagueGUI.addMatch(userManager.getSelectedMatches());
                            mainFrameGUI.showSimulationLeagueView();

                        break;
                    case "VIEW_AVAIABLE_LEAGUES":
                        mainFrameGUI.showLeague();
                        break;

                    case "VIEW_STATISTICS" :
                        mainFrameGUI.showStatistics();
                        break;
                }
            }
        }
    }

