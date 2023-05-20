package Presentation.Controllers;

import Business.Managers.UserManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.TopPanelGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanelController implements ActionListener {

    private final TopPanelGUI view;

    private final MainFrameGUI mainFrameGUI;
    private final UserManager userManager;

    public TopPanelController(TopPanelGUI view, MainFrameGUI mainFrameGUI, UserManager userManager) {
        this.view = view;
        this.mainFrameGUI = mainFrameGUI;
        this.userManager = userManager;

        // Add action listeners to the menu items
        view.getLogoutMenuItem().addActionListener(this);
        view.getDeleteAccountMenuItem().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLogoutMenuItem()) {
            userManager.logOut();
            mainFrameGUI.showMainPanel();
        } else if (e.getSource() == view.getDeleteAccountMenuItem()) {

        }
    }
}
