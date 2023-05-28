package Presentation.Views;

import Presentation.Controllers.ChangePasswordController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordGUI extends JPanel {

    private final JPasswordField actualPassword;
    private final JPasswordField newPassword;
    private final JPasswordField repeatNewPassword;

    private final JButton ok;


    public ChangePasswordGUI() {

        Color backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        //General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        //Title
        JLabel title = new JLabel("Change Password");
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setFont(new Font("Apple Casual", Font.PLAIN, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(60));

        String defaultActualPasswordText = "Actual Password: ";
        actualPassword = new JPasswordField(defaultActualPasswordText);
        actualPassword.setEchoChar((char)  0);
        actualPassword.setFont(new Font("Inter", Font.PLAIN, 20));
        actualPassword.setBackground(Color.decode("#D9D9D9"));
        actualPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(actualPassword);

        panel.add(Box.createVerticalStrut(15));

        String defaultNewPasswordText = "New Password: ";
        newPassword = new JPasswordField(defaultNewPasswordText);
        newPassword.setEchoChar((char)  0);
        newPassword.setFont(new Font("Inter", Font.PLAIN, 20));
        newPassword.setBackground(Color.decode("#D9D9D9"));
        newPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(newPassword);

        panel.add(Box.createVerticalStrut(15));

        String defaultRepeatNewPasswordText = "Repeat New Password: ";
        repeatNewPassword = new JPasswordField(defaultRepeatNewPasswordText);
        repeatNewPassword.setEchoChar((char)  0);
        repeatNewPassword.setFont(new Font("Inter", Font.PLAIN, 20));
        repeatNewPassword.setBackground(Color.decode("#D9D9D9"));
        repeatNewPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(repeatNewPassword);

        panel.add(Box.createVerticalStrut(20));

        ok = new JButton("    OK    ");
        ok.setActionCommand("OK");
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(ok);


        this.add(panel);
    }

    public void registerChangePassword(ChangePasswordController controller) {
        actualPassword.addFocusListener(controller);
        actualPassword.setName("ActualPassword");
        newPassword.addFocusListener(controller);
        newPassword.setName("NewPassword");
        repeatNewPassword.addFocusListener(controller);
        repeatNewPassword.setName("RepeatNewPassword");
    }

    public String getActualPassword() {
        return actualPassword.getText();
    }

    public String getNewPassword() {
        return newPassword.getText();
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword.getText();
    }

    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void passwordSuccess() {
        JOptionPane.showMessageDialog(null, "The password have been successfully changed!");
    }

    public void actionListenerPassword(ActionListener actionListener){
        ok.addActionListener(actionListener);
    }
}
