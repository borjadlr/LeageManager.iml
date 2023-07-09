package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class implements the bottonm panel of the Main Frame where there the action to change the password
 */
public class BottomPanelGUI extends JPanel {

    private final JButton changePassword;
    private static final String LetterType = "INTER";
    private static final String CHANGE_PASSWORD = "Change Password";
    private static final String CHANGE_CURRENT_PASSWORD = "CHANGE_CURRENT_PASSWORD";

    public BottomPanelGUI() {
        setLayout(new BorderLayout());

        // Add a white section to the bottom panel
        JPanel downSection = new JPanel(new BorderLayout());
        downSection.setPreferredSize(new Dimension(0, 30));
        add(downSection, BorderLayout.SOUTH);
        downSection.setBackground(Color.WHITE);
        downSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 5));

        // Add a button to the white section to change the current password
        changePassword = new JButton(CHANGE_PASSWORD);
        changePassword.setActionCommand(CHANGE_CURRENT_PASSWORD);
        changePassword.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        changePassword.setFont(Font.getFont(LetterType));
        changePassword.setPreferredSize(new Dimension(180, 30));
        changePassword.setBackground(Color.WHITE);
        downSection.add(changePassword, BorderLayout.EAST);

    }

    /**
     * This method is about the listener of the change password button
     * @param actionListener action listener
     */
    public void changePasswordListener(ActionListener actionListener){
        changePassword.addActionListener(actionListener);
    }
}


