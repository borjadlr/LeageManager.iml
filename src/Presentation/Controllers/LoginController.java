package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.*;
import Presentation.Views.LoginGUI;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.TopPanelGUI;

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
    private final TopPanelGUI topPanelGUI;

    public LoginController(MainFrameGUI mainView, LoginGUI view, UserManager userManager, TopPanelGUI topPanelGUI) {
        this.mainFrameGUI = mainView;
        this.view = view;
        this.userManager = userManager;
        this.topPanelGUI = topPanelGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN_BUTTON":
                try {
                    String username;
                    String password;
                    do {
                        username = view.getUsernameInfo();
                        password = view.getPasswordInfo();
                        if(userManager.signIn(username, password)){
                            mainFrameGUI.showMenuAdmin();
                            topPanelGUI.hideShowDeleteAccount(false);
                            topPanelGUI.hideShowDropDownButton(true);
                        }else {
                            mainFrameGUI.showMenuUser();
                            topPanelGUI.hideShowDropDownButton(true);
                        }
                    } while (!areFieldsCompleted(username, password));

                } catch (IncorrectPassword4UserException | DNIOrMailDontExistException ex) {
                    view.exceptionMessage(ex.getMessage());
                } catch (NullPointerException npe) {
                    view.noUsersMessage();
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

    public boolean areFieldsCompleted(String username, String password){
        return username != "" && password != "";
    }
}


