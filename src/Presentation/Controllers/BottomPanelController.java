package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller class for the bottom panel in the MainFrameGUI.
 * Handles the actions performed on the buttons in the bottom panel.
 */
public class BottomPanelController implements ActionListener {

    private static final String CHANGE_CURRENT_PASSWORD = "CHANGE_CURRENT_PASSWORD";
    private final MainFrameGUI mainFrame;

    /**
     * Handles the action events performed on the buttons in the bottom panel.
     *
     * @param mainFrame The ActionEvent that occurred.
     */
    public BottomPanelController(MainFrameGUI mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (CHANGE_CURRENT_PASSWORD.equals(e.getActionCommand())) {
                mainFrame.showChangePassword();
            }
        }
    }
}
