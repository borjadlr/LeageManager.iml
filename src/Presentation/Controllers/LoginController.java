package Presentation.Controllers;

import Business.Managers.LeagueManager;
import Business.Managers.UserManager;
import Exceptions.*;
import Presentation.Views.LoginGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * The controller class for the LoginGUI.
 * Handles the actions and focus events in the login view.
 */
public class LoginController implements ActionListener, FocusListener {

    private final String defaultEmailText = "dni/email: ";
    private final String defaultPasswordText = "Password: ";
    private static final String EMAIL_OR_DNI = "Email or DNI";
    private static final String PASSWORD = "Password: ";
    private static final String LOGIN_BUTTON = "LOGIN_BUTTON";
    private static final String BLANK = "";

    private final MainFrameGUI mainFrameGUI;
    private final LoginGUI view;
    private final UserManager userManager;

    /**
     * Constructs a LoginController object.
     *
     * @param mainView     The MainFrameGUI instance.
     * @param view         The LoginGUI instance.
     * @param userManager The UserManager instance.
     */
    public LoginController(MainFrameGUI mainView, LoginGUI view, UserManager userManager) {
        this.mainFrameGUI = mainView;
        this.view = view;
        this.userManager = userManager;
    }

    /**
     * Handles the action events in the login view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (LOGIN_BUTTON.equals(e.getActionCommand())) {
            try {
                String username = view.getUsernameInfo();
                String password = view.getPasswordInfo();
                if (userManager.signIn(username, password)) {
                    mainFrameGUI.showMenuAdmin();
                } else {
                    mainFrameGUI.showMenuUser();
                }
                view.clear();
            } catch (IncorrectPassword4UserException | DNIOrMailDontExistException ex) {
                view.exceptionMessage(ex.getMessage());
            } catch (NullPointerException npe) {
                view.noUsersMessage();
            }
        }
    }

    /**
     * Handles the focus gained events in the login view.
     *
     * @param e The FocusEvent that occurred.
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case EMAIL_OR_DNI:
                    if (textField.getText().equals(defaultEmailText)) {
                        textField.setText(BLANK);
                    }
                    break;
                case PASSWORD:
                    if (textField.getText().equals(defaultPasswordText)) {
                        textField.setText(BLANK);
                    }
                    break;
            }
        }
    }

    /**
     * Handles the focus lost events in the login view.
     *
     * @param e The FocusEvent that occurred.
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case EMAIL_OR_DNI:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultEmailText);
                    }
                    break;
                case PASSWORD:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultPasswordText);
                    }
                    break;
            }
        }
    }
}


