package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanelController implements ActionListener {

    private final MainFrameGUI mainFrame;

    public BottomPanelController(MainFrameGUI mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "CHANGE_CURRENT_PASSWORD":
                    mainFrame.showChangePassword();
                    break;
            }
        }
    }
}
