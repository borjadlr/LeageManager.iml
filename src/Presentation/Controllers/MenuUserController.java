package Presentation.Controllers;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUserController implements ActionListener {

    private final MainFrameGUI mainFrameGUI;

    public MenuUserController(MainFrameGUI mainFrameGUI) {
        this.mainFrameGUI = mainFrameGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() instanceof JButton) {
                switch (e.getActionCommand()) {
                    case "WATCH_MATCHES" ->
                        //simulationLeagueGUI.addMatch(userManager.getSelectedMatches());
                            mainFrameGUI.showSimulationLeagueView();
                    case "VIEW_AVAIABLE_LEAGUES" -> mainFrameGUI.showLeague();
                    case "VIEW_STATISTICS" -> mainFrameGUI.showStatistics();
                }
            }
        }
    }

