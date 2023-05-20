package Presentation.Controllers;

import Business.Entities.User;
import Business.Managers.UserManager;
import Exceptions.*;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class RegistrationController implements FocusListener, ActionListener {

    private final RegistrationGUI view;
    private final UserManager userManager;
    private final MainFrameGUI mainFrameGUI;

    private final String defaultDniText = "Dni: ";
    private final String defaultTeamText = "Team:";
    private final String defaultDorsalText = "Dorsal: ";
    private final String defaultNameText = "Name: ";
    private final String defaultPhoneNumberText = "Phone Number: ";

    public RegistrationController(MainFrameGUI mainFrameGUI, RegistrationGUI view, UserManager userManager) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if ("OK_BUTTON".equals(e.getActionCommand())) {
                String dni = view.getDniText();
                int dorsal = view.getDorsalText();
                String phoneNumber = view.getPhoneNumberText();
                String email = view.getEmailText();
                User user = userManager.createUser(dni, UserManager.generatePassword(), email, dorsal, phoneNumber);

                try {
                    userManager.signUp(user, user.getPassword());
                } catch (InvalidPasswordException | ExistingDNIException | DNIDontExistException |
                         InvalidEmailException | EmailAlreadyExistsException | SamePasswordException |
                         DNIException | SQLException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
                mainFrameGUI.showMenuUser();
            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case "DNI":
                    if (textField.getText().equals(defaultDniText)) {
                        textField.setText("");
                    }
                    break;
                case "Team":
                    if (textField.getText().equals(defaultTeamText)) {
                        textField.setText("");
                    }
                    break;
                case "Dorsal":
                    if (textField.getText().equals(defaultDorsalText)) {
                        textField.setText("");
                    }
                    break;
                case "Name":
                    if (textField.getText().equals(defaultNameText)) {
                        textField.setText("");
                    }
                    break;
                case "Phone":
                    if (textField.getText().equals(defaultPhoneNumberText)) {
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
                case "DNI":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultDniText);
                    }
                    break;
                case "Team":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultTeamText);
                    }
                    break;
                case "Dorsal":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultDorsalText);
                    }
                    break;
                case "Name":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultNameText);
                    }
                    break;
                case "Phone":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultPhoneNumberText);
                    }
                    break;
            }
        }
    }
}
