package Presentation.Views;

import Presentation.Controllers.LoginController;
import Presentation.Controllers.TopPanelController;

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
    private static final String defaultEmailText = "dni/email: ";
    private static final String BLANK = "";
    private static final String EMAIL_OR_DNI = "Email or DNI";
    private static final String USERS_ERROR = "There are no users registered, so you cannot log in.";
    private static final String PASSWORD = "Password: ";
    private static final String TITLE = "LeagueManager Login";
    private static final String TitleLetterType = "Apple Casual";
    private static final String LOGIN = "Login";
    private static final String LetterType = "Inter";
    private static final String BackGroundColor = "#D9D9D9";


    /**
     * Creates a new instance of the LoginGUI class.
     */
    public LoginGUI() {
        Color backgroundColor = Color.white;
        this.setLayout(new GridBagLayout());
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setBackground(backgroundColor);
        panel.setOpaque(true);

        JLabel title = new JLabel(TITLE);
        title.setFont(new Font(TitleLetterType, PLAIN, 60));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(90));

        email = new JTextField(defaultEmailText);
        email.setFont(new Font(LetterType, PLAIN, 20));
        email.setBackground(Color.decode(BackGroundColor));
        email.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(email);

        panel.add(Box.createVerticalStrut(15));

        JPanel passwordPanel = new JPanel(new BorderLayout());

        panel.add(passwordPanel);

        password = new JPasswordField(PASSWORD);
        password.setFont(new Font(LetterType, PLAIN, 20));
        password.setBackground(Color.decode(BackGroundColor));
        password.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        passwordPanel.add(password, BorderLayout.CENTER);

        panel.add(Box.createVerticalStrut(15));

        login = new JButton(LOGIN);
        login.setActionCommand(LOGIN_BUTTON);
        login.setPreferredSize(new Dimension(150, 40));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(login);

        this.add(panel);
    }

    /**
     * Registers a focus listener for the text fields.
     * @param focusListener The focus listener to register.
     */
    public void focusListener(FocusListener focusListener) {
        email.addFocusListener(focusListener);
        email.setName(EMAIL_OR_DNI);
        password.addFocusListener(focusListener);
        password.setName(PASSWORD);
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
        JOptionPane.showMessageDialog(null, USERS_ERROR);
    }

    /**
     * Clean the variables in JTextFields.
     */
    public void clear(){
        email.setText(BLANK);
        password.setText(BLANK);
    }

}