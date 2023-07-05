package Presentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The TopPanelGUI class represents a panel for the top section of the user interface.
 * It includes a dropdown button, a back button, and a dropdown menu.
 */
public class TopPanelGUI extends JPanel {
    private final JButton dropdownButton;
    private final JButton atras;
    private final JPopupMenu dropdownMenu;
    private final JMenuItem logout;
    private final JMenuItem deleteAccount;
    private static final String DROPDOWN_BUTTON = "DROPDOWN_BUTTON";
    private static final String LOGOUT_BUTTON = "LOGOUT_BUTTON";
    private static final String DELETE_ACCOUNT_BUTTON = "DELETE_ACCOUNT_BUTTON";
    private static final String BACK_BUTTON = "BACK_BUTTON";

    /**
     * Constructs a TopPanelGUI object.
     */
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

    /**
     * Hides or shows the dropdown button based on the specified flag.
     * @param t true to show the dropdown button, false to hide it
     */
    public void hideShowDropDownButton(boolean t) {
        dropdownButton.setVisible(t);
    }

    /**
     * Hides or shows the back button based on the specified flag.
     * @param t true to show the back button, false to hide it
     */
    public void hideBackButton(boolean t) {
        atras.setVisible(t);
    }

    /**
     * Hides or shows the "Delete Account" option in the dropdown menu based on the specified flag.
     * @param t true to show the "Delete Account" option, false to hide it
     */
    public void hideShowDeleteAccount(boolean t) {
        deleteAccount.setVisible(t);
    }

    /**
     * Displays the dropdown menu.
     */
    public void showDropdownMenu() {
        JPopupMenu popupMenu = (JPopupMenu) dropdownButton.getComponentPopupMenu();
        popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
    }

    /**
     * Displays a confirmation message dialog asking the user if they want to log out.
     * @return the user's confirmation choice (JOptionPane.YES_OPTION or JOptionPane.NO_OPTION)
     */
    public int messageAreYouSure() {
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Registers an ActionListener to the buttons in the top panel.
     * @param actionListener the ActionListener to register
     */
    public void actionListener(ActionListener actionListener) {
        dropdownButton.addActionListener(actionListener);
        logout.addActionListener(actionListener);
        deleteAccount.addActionListener(actionListener);
        atras.addActionListener(actionListener);
    }
}