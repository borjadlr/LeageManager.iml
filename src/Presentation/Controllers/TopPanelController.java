package Presentation.Controllers;

import Presentation.Views.TopPanelGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanelController implements ActionListener {

    private TopPanelGUI view;

    public TopPanelController(TopPanelGUI view) {
        this.view = view;

        // Add action listeners to the menu items
        view.getLogoutMenuItem().addActionListener(this);
        view.getDeleteAccountMenuItem().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLogoutMenuItem()) {
            System.out.println("hola");
        } else if (e.getSource() == view.getDeleteAccountMenuItem()) {
            System.out.println("adeu");
        }
    }
}
