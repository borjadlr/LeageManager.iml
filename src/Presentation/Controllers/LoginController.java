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

    private String defaultEmailText = "dni/email: ";
    private String defaultPasswordText = "Password: ";

    private final MainFrameGUI mainView;
    private final LoginGUI view;
    //private final UserManager userManager;
    private UserDAOInt userDAOInt;


    private static final String BACK_LOGIN = "Back Login";

    public LoginController(MainFrameGUI mainView, LoginGUI view, UserDAOInt userDAOInt) {
        this.mainView = mainView;
        this.view = view;
        //this.userManager = userManager;
        this.userDAOInt = userDAOInt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN_BUTTON":
                String username = view.getUsernameInfo();
                String password = view.getPasswordInfo();
                userDAOInt.InsertDataUser("233443434G", username, password, 22, "123456789");
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
            switch (textField.getName()) {
                case "Email":
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
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
            switch (textField.getName()) {
                case "Email":
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


