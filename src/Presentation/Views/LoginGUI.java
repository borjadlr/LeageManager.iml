package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import static java.awt.Font.PLAIN;

/**
 * The graphical user interface for the login functionality.
 */
public class LoginGUI extends JPanel {

    private final JTextField email;
    private final JTextField password;
    private final JButton login;
    private static final String LOGIN_BUTTON = "LOGIN_BUTTON";

    /**
     * Creates a new instance of the LoginGUI class.
     */
    public LoginGUI() {
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
        JLabel title = new JLabel("LeagueManager Login");
        title.setFont(new Font("Apple Casual", PLAIN, 60));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        // Dni/Email
        String defaultDniText = "dni/email: ";
        email = new JTextField(defaultDniText);
        email.setFont(new Font("Inter", PLAIN, 20));
        email.setBackground(Color.decode("#D9D9D9"));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(email);

        panel.add(Box.createVerticalStrut(15));

        JPanel passwordPanel = new JPanel(new BorderLayout());

        panel.add(passwordPanel);

        String defaultPasswordText = "Password: ";
        password = new JPasswordField(defaultPasswordText);
        password.setFont(new Font("Inter", PLAIN, 20));
        password.setBackground(Color.decode("#D9D9D9"));
        password.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        passwordPanel.add(password, BorderLayout.CENTER);

        panel.add(Box.createVerticalStrut(15));

        login = new JButton("Login");
        login.setActionCommand(LOGIN_BUTTON);
        login.setPreferredSize(new Dimension(150, 40));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(login);

        // register focus listener for login controller

        // add panel to this
        this.add(panel);
    }

    /**
     * Registers a focus listener for the text fields.
     * @param focusListener The focus listener to register.
     */
    public void focusListener(FocusListener focusListener) {
        email.addFocusListener(focusListener);
        email.setName("Email or DNI");
        password.addFocusListener(focusListener);
        password.setName("Password");
    }

    /**
     * Registers an action listener for the login button.
     * @param actionListener The action listener to register.
     */
    public void actionListener(ActionListener actionListener) {
        login.addActionListener(actionListener);
    }

    /**
     * Retrieves the entered username or email information.
     * @return The username or email information.
     */
    public String getUsernameInfo() {
        return email.getText();
    }

    /**
     * Retrieves the entered password information.
     * @return The password information.
     */
    public String getPasswordInfo() {
        return password.getText();
    }

    /**
     * Displays an exception message dialog with the specified message.
     * @param message The exception message to display.
     */
    public void exceptionMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Displays a message dialog indicating that there are no users, so login is not possible.
     */
    public void noUsersMessage() {
        JOptionPane.showMessageDialog(null, "There are no users, so you cannot log in.");
    }

}