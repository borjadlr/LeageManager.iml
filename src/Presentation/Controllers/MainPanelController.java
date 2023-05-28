package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.MainPanelGUI;
import Presentation.Views.TopPanelGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The controller class for the MainPanelGUI.
 * Handles the actions in the main panel view.
 */
public class MainPanelController implements ActionListener {
    private final MainFrameGUI mainFrame;
    private final TopPanelGUI topPanelGUI;

    /**
     * Constructs a MainPanelController object.
     *
     * @param mainFrame   The MainFrameGUI instance.
     * @param topPanelGUI The TopPanelGUI instance.
     */
    public MainPanelController(MainFrameGUI mainFrame, TopPanelGUI topPanelGUI) {
        this.mainFrame = mainFrame;
        this.topPanelGUI = topPanelGUI;
    }
    /**
     * Handles the action events in the main panel view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "LOGIN_BUTTON" -> {
                    mainFrame.showLogin();
                    topPanelGUI.hideShowDropDownButton(false);
                }
                case "REGISTER_BUTTON" -> {
                    mainFrame.showRegister();
                    topPanelGUI.hideShowDropDownButton(false);
                }
            }
        }
    }
}
