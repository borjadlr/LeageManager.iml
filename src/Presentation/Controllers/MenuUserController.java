package Presentation.Controllers;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller class for the MenuUserGUI.
 * Handles the actions in the user menu view.
 */
public class MenuUserController implements ActionListener {

    private final MainFrameGUI mainFrameGUI;

    /**
     * Constructs a MenuUserController object.
     *
     * @param mainFrameGUI The MainFrameGUI instance.
     */
    public MenuUserController(MainFrameGUI mainFrameGUI) {
        this.mainFrameGUI = mainFrameGUI;
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
                    case "WATCH_MATCHES" ->
                        //simulationLeagueGUI.addMatch(userManager.getSelectedMatches());
                            mainFrameGUI.showSimulationLeagueView();
                    case "VIEW_AVAIABLE_LEAGUES" -> mainFrameGUI.showLeague();
                    case "VIEW_STATISTICS" -> mainFrameGUI.showStatistics();
                }
            }
        }
    }

