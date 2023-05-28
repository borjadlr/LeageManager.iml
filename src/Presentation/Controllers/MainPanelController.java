package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.MainPanelGUI;
import Presentation.Views.TopPanelGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelController implements ActionListener {
    private final MainFrameGUI mainFrame;
    private final TopPanelGUI topPanelGUI;
    public MainPanelController(MainFrameGUI mainFrame, TopPanelGUI topPanelGUI) {
        this.mainFrame = mainFrame;
        this.topPanelGUI = topPanelGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "LOGIN_BUTTON":
                    mainFrame.showLogin();
                    topPanelGUI.hideShowDropDownButton(false);
                    break;
                case "REGISTER_BUTTON":
                    mainFrame.showRegister();
                    topPanelGUI.hideShowDropDownButton(false);
                    break;

            }
        }
    }
}
