package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.*;
import Exceptions.DNIException;
import Presentation.Views.LoginGUI;
import Presentation.Views.MainFrameGUI;
import Persistance.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginController implements ActionListener, FocusListener {

    private final String defaultEmailText = "dni/email: ";
    private final String defaultPasswordText = "Password: ";

    private final MainFrameGUI mainFrameGUI;
    private final LoginGUI view;

    private final UserManager userManager;

    public LoginController(MainFrameGUI mainView, LoginGUI view, UserManager userManager) {
        this.mainFrameGUI = mainView;
        this.view = view;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN_BUTTON":
                try {
                    String username = view.getUsernameInfo();
                    String password = view.getPasswordInfo();
                    userManager.signIn(username, password);
                    mainFrameGUI.showChangePassword();

                } catch (IncorrectPassword4UserException | DNIOrMailDontExistException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case "Email or DNI":
                    if (textField.getText().equals(defaultEmailText)) {
                        textField.setText("");
                    }
                    break;
                case "Password":
                    if (textField.getText().equals(defaultPasswordText)) {
                        textField.setText("");
                    }
                    break;
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case "Email or DNI":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultEmailText);
                    }
                    break;
                case "Password":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultPasswordText);
                    }
                    break;
            }
        }
    }
}


