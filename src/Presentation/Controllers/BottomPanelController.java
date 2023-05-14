package Presentation.Controllers;

import Presentation.Views.BottomPanelGUI;
import Presentation.Views.ChangePasswordGUI;
import Presentation.Views.MainFrameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanelController implements ActionListener {


    private static final String CHANGE_PASSWORD = "Change Password";
    private final BottomPanelGUI bottomPanel;
    private final ChangePasswordGUI changePasswordGUI;
    private final MainFrameGUI mainView;

    public BottomPanelController(MainFrameGUI mainView, BottomPanelGUI bottomPanel, ChangePasswordGUI changePasswordGUI){
        this.bottomPanel = bottomPanel;
        this.changePasswordGUI = changePasswordGUI;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch (e.getActionCommand()){
           case CHANGE_PASSWORD:
               mainView.showChangePassword();
               break;
       }
    }
}
