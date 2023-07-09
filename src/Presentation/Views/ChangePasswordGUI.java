package Presentation.Views;

import Presentation.Controllers.ChangePasswordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangePasswordGUI extends JPanel {

    private static final String OK = "OK";
    private static final String LetterType = "Inter";
    private static final String BackGroundColor = "#D9D9D9";
    private static final String BLANK = "    ";
    private static final String SUCCESS_MESSAGE = "The password has been successfully changed!";
    private static final String ACTUAL_PASSWORD = "ActualPassword";
    private static final String REPEATED_PASSWORD = "RepeatNewPassword";
    private static final String NEW_PASSWORD = "NewPassword";
    private static final String CHANGE_PASSWORD = "Change Password";
    private final JPasswordField actualPassword;
    private final JPasswordField newPassword;
    private final JPasswordField repeatNewPassword;

    private final JButton ok;

    /**
     * Constructs a ChangePasswordGUI object.
     */
    public ChangePasswordGUI() {

        Color backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // General panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        // Title
        JLabel title = new JLabel(CHANGE_PASSWORD);
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        String defaultCurrentPassword = "Actual Password: ";
        title.setFont(new Font(defaultCurrentPassword, Font.PLAIN, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(60));

        // Actual Password
        actualPassword = new JPasswordField(defaultCurrentPassword);
        actualPassword.setEchoChar((char)  0);
        actualPassword.setFont(new Font(LetterType, Font.PLAIN, 20));
        actualPassword.setBackground(Color.decode(BackGroundColor));
        actualPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(actualPassword);

        panel.add(Box.createVerticalStrut(15));

        // New Password
        String defaultNewPassword = "New Password: ";
        newPassword = new JPasswordField(defaultNewPassword);
        newPassword.setEchoChar((char)  0);
        newPassword.setFont(new Font(LetterType, Font.PLAIN, 20));
        newPassword.setBackground(Color.decode(BackGroundColor));
        newPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(newPassword);

        panel.add(Box.createVerticalStrut(15));

        // Repeat New Password
        String defaultRepeatNewPassword = "Repeat New Password: ";
        repeatNewPassword = new JPasswordField(defaultRepeatNewPassword);
        repeatNewPassword.setEchoChar((char)  0);
        repeatNewPassword.setFont(new Font(LetterType, Font.PLAIN, 20));
        repeatNewPassword.setBackground(Color.decode(BackGroundColor));
        repeatNewPassword.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(repeatNewPassword);

        panel.add(Box.createVerticalStrut(20));

        // OK Button
        ok = new JButton(BLANK + OK + BLANK);
        ok.setActionCommand(OK);
        ok.setPreferredSize(new Dimension(150, 40));
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(ok);

        this.add(panel);
    }

    /**
     * Registers the change password controller for the input fields.
     * @param controller the change password controller to register
     */
    public void registerChangePassword(ChangePasswordController controller) {
        actualPassword.addFocusListener(controller);
        actualPassword.setName(ACTUAL_PASSWORD);
        newPassword.addFocusListener(controller);
        newPassword.setName(NEW_PASSWORD);
        repeatNewPassword.addFocusListener(controller);
        repeatNewPassword.setName(REPEATED_PASSWORD);
    }

    /**
     * Returns the value of the actual password field.
     * @return the actual password
     */
    public String getActualPassword() {
        return actualPassword.getText();
    }

    /**
     * Returns the value of the new password field.
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword.getText();
    }

    /**
     * Returns the value of the repeat new password field.
     * @return the repeat new password
     */
    public String getRepeatNewPassword() {
        return repeatNewPassword.getText();
    }

    /**
     * Displays an exception message in a dialog box.
     * @param message the exception message to display
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Displays a success message when the password has been changed successfully.
     */
    public void passwordSuccess() {
        JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
    }

    /**
     * Registers an action listener for the OK button.
     * @param actionListener the action listener to register
     */
    public void actionListenerPassword(ActionListener actionListener){
        ok.addActionListener(actionListener);
    }
}