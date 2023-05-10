package Presentation.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Presentation.Views.MainFrameGUI;
import Presentation.Views.MainPanelGUI;
import Presentation.Views.TopPanelGUI;

public class TopPanelController {

    private final TopPanelGUI topPanelGUI;
    private final MainFrameGUI mainframeGUI;

    public TopPanelController(TopPanelGUI topPanelGUI, MainFrameGUI mainframeGUI) {
        this.topPanelGUI = topPanelGUI;
        this.mainframeGUI = mainframeGUI;

        // Add action listeners to the dropdown menu items
        JPopupMenu dropdownMenu = new JPopupMenu();
        JMenuItem logout = topPanelGUI.getLogoutMenuItem();
        JMenuItem deleteAccount = topPanelGUI.getDeleteAccountMenuItem();
        dropdownMenu.add(logout);
        dropdownMenu.add(deleteAccount);

        ActionListener dropdownButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(topPanelGUI.getDropdownButton(), 0, topPanelGUI.getDropdownButton().getHeight());
            }
        };
        topPanelGUI.dropdownButton(dropdownButtonListener);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframeGUI.showMainPanel();
            }
        });
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframeGUI.showMainPanel();
            }
        });
    }
}
