package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.MainPanelGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelController implements ActionListener {
    private MainFrameGUI mainFrame;
    public MainPanelController(MainFrameGUI mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "LOGIN_BUTTON":
                    mainFrame.showLogin();
                    break;
                case "REGISTER_BUTTON":
                    mainFrame.showRegister();
                    break;

            }
        }
    }
}
