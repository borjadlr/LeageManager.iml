package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelController implements ActionListener {

    private static final String MAIN_PANEL_LOGIN = "Login";

    private static final String MAIN_PANEL_REGISTER = "Register";

    private final MainFrameGUI mainFrameGUI;

    public MainPanelController(MainFrameGUI mainFrameGUI) {
        this.mainFrameGUI = mainFrameGUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MAIN_PANEL_LOGIN:
                mainFrameGUI.showLogin();
                break;
            case MAIN_PANEL_REGISTER:
                mainFrameGUI.showRegister();
                break;

        }
    }
}
