package Presentation.Controllers;

import Business.Entities.League;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.MenuUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MenuUserController implements ActionListener {

    private final MainFrameGUI mainFrameGUI;

    private final MenuUserGUI view;

    public MenuUserController(MainFrameGUI mainFrameGUI, MenuUserGUI view) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                switch (e.getActionCommand()) {
                    case "WATCH_MATCHES":
                        mainFrameGUI.showLogin();
                        break;
                    case "VIEW_AVAIABLE_LEAGUES":
                        mainFrameGUI.showLeague();
                        break;

                }
            }
        }
    }

