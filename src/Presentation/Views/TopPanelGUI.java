package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class TopPanelGUI extends JPanel {
    private JButton dropdownButton;
    private JButton atras;
    private JPopupMenu dropdownMenu;
    private final JMenuItem logout;
    private final JMenuItem deleteAccount;
    private static final String DROPDOWN_BUTTON = "DROPDOWN_BUTTON";
    private static final String LOGOUT_BUTTON = "LOGOUT_BUTTON";
    private static final String DELETE_ACCOUNT_BUTTON = "DELETE_ACCOUNT_BUTTON";
    private static final String BACK_BUTTON = "BACK_BUTTON";

    public TopPanelGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 50));
        setBackground(Color.WHITE);

        // Add a section to the top panel
        JPanel upSection = new JPanel(new BorderLayout());
        upSection.setPreferredSize(new Dimension(0, 50));
        add(upSection, BorderLayout.NORTH);
        upSection.setBackground(Color.WHITE);

        // Add a back button to the top-left corner of the top panel
        atras = new JButton("◀");
        atras.setFont(new Font("Inter", Font.PLAIN, 20));
        atras.setBackground(Color.WHITE);
        atras.setSize(new Dimension(30, 5));
        atras.setBorder(BorderFactory.createEmptyBorder());
        atras.setActionCommand(BACK_BUTTON);
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
        dropdownButton.setActionCommand(DROPDOWN_BUTTON);
        dropdownButton.setVisible(true);

        // Add a dropdown menu to the dropdown button
        dropdownMenu = new JPopupMenu();
        dropdownMenu.setBackground(Color.black);
        logout = new JMenuItem("Log out");
        logout.setBackground(Color.decode("#D9D9D9"));
        logout.setPreferredSize(new Dimension(100, 30));
        logout.setBorder(BorderFactory.createLineBorder(Color.black));
        logout.setActionCommand(LOGOUT_BUTTON);
        deleteAccount = new JMenuItem("Delete Account");
        deleteAccount.setBackground(Color.decode("#D9D9D9"));
        deleteAccount.setBorder(BorderFactory.createLineBorder(Color.black));
        deleteAccount.setPreferredSize(new Dimension(100, 30));
        deleteAccount.setActionCommand(DELETE_ACCOUNT_BUTTON);
        dropdownMenu.add(logout);
        dropdownMenu.add(deleteAccount);

        dropdownButton.setComponentPopupMenu(dropdownMenu);

    }

    public void hideShowDropDownButton(boolean t){
        dropdownButton.setVisible(t);
    }

    public void hideBackButton(boolean t){
        atras.setVisible(t);
    }

    public void hideShowDeleteAccount(boolean t){
        deleteAccount.setVisible(t);
    }

    public void showDropdownMenu() {
        JPopupMenu popupMenu = (JPopupMenu) dropdownButton.getComponentPopupMenu();
        popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
    }

    public int messageAreYouSure(){
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
        return confirm;
    }

    public void actionListener(ActionListener actionListener){
        dropdownButton.addActionListener(actionListener);
        logout.addActionListener(actionListener);
        deleteAccount.addActionListener(actionListener);
        atras.addActionListener(actionListener);
    }
}
