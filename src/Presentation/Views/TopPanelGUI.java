package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import Presentation.Controllers.TopPanelController;

public class TopPanelGUI extends JPanel {

    private JButton dropdownButton;
    private JMenuItem logout;
    private JMenuItem deleteAccount;
    private TopPanelController controller;

    public TopPanelGUI(CardLayout cardLayout, JPanel centerPanel) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 50));
        setBackground(Color.WHITE);

        // Add a section to the top panel
        JPanel upSection = new JPanel(new BorderLayout());
        upSection.setPreferredSize(new Dimension(0, 50));
        add(upSection, BorderLayout.NORTH);
        upSection.setBackground(Color.WHITE);

        // Add a back button to the top-left corner of the top panel
        JButton atras = new JButton("◀");
        atras.setFont(new Font("Inter", Font.PLAIN, 20));
        atras.setBackground(Color.WHITE);
        atras.setSize(new Dimension(30, 5));
        atras.setBorder(BorderFactory.createEmptyBorder());
        Border emptyBorder = BorderFactory.createEmptyBorder();
        upSection.setBorder(emptyBorder);
        upSection.add(atras, BorderLayout.WEST);

        // Add a dropdown button to the top-right corner of the top panel
        dropdownButton = new JButton("  ▼  ");
        JPanel drop = new JPanel();
        drop.add(dropdownButton);

        dropdownButton.setPreferredSize(new Dimension(40, 2));
        dropdownButton.setBackground(Color.decode("#D9D9D9"));
        dropdownButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        upSection.add(dropdownButton, BorderLayout.EAST);
        dropdownButton.setVisible(true);

        // Add a dropdown menu to the dropdown button
        JPopupMenu dropdownMenu = new JPopupMenu();
        dropdownMenu.setBackground(Color.black);
        logout = new JMenuItem("Log out");
        logout.setBackground(Color.decode("#D9D9D9"));
        logout.setPreferredSize(new Dimension(90, 30));
        logout.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteAccount = new JMenuItem("Delete Account");
        deleteAccount.setBackground(Color.decode("#D9D9D9"));
        deleteAccount.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteAccount.setPreferredSize(new Dimension(90, 30));
        dropdownMenu.add(logout);
        dropdownMenu.add(deleteAccount);

        // Create a controller and add action listener to dropdown button);
    }

    public void dropdownButton(ActionListener actionListener){
        dropdownButton.addActionListener(actionListener);
    }

    public JMenuItem getLogoutMenuItem() {
        return logout;
    }
    
    public JMenuItem getDeleteAccountMenuItem(){
        return deleteAccount;
    }

    public JButton getDropdownButton() {
        return dropdownButton;
    }

}
