package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BottomPanelGUI extends JPanel {

    private JButton changePassword;

    private static final String INTER = "INTER";
    private static final String CHANGE_CURRENT_PASSWORD = "Change Current Password";

    public BottomPanelGUI() {
        setLayout(new BorderLayout());

        // Add a white section to the bottom panel
        JPanel downSection = new JPanel(new BorderLayout());
        downSection.setPreferredSize(new Dimension(0, 30));
        add(downSection, BorderLayout.SOUTH);
        downSection.setBackground(Color.WHITE);
        downSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 5));

        // Add a button to the white section to change the current password
        changePassword = new JButton(CHANGE_CURRENT_PASSWORD);
        changePassword.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        changePassword.setFont(Font.getFont(INTER));
        changePassword.setPreferredSize(new Dimension(180, 30));
        changePassword.setBackground(Color.WHITE);
        downSection.add(changePassword, BorderLayout.EAST);
    }

    public void changePasswordListener(ActionListener actionListener){
        changePassword.addActionListener(actionListener);
    }
}


