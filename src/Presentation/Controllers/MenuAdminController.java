package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdminController implements ActionListener{

    private final MainFrameGUI mainFrameGUI;

    public MenuAdminController(MainFrameGUI mainFrame) {
        this.mainFrameGUI = mainFrame;
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
                case "DELETE_LEAGUE":
                    mainFrameGUI.showLeague();
                    break;
                case "VIEW_LEAGUES":
                    mainFrameGUI.showLeague();
                    break;
            }
        }
    }
}
