package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.DNIException;
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
    private MainFrameGUI mainFrameGUI;

    private String defaultDniText = "Dni: ";
    private String defaultTeamText = "Team:";
    private String defaultDorsalText = "Dorsal: ";
    private String defaultNameText = "Name: ";
    private String defaultPhoneNumberText = "Phone Number: ";

    public RegistrationController(MainFrameGUI mainFrameGUI, RegistrationGUI view, UserManager userManager) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "OK_BUTTON":
                    String dni = view.getDniText();
                    String dorsal = view.getDorsalText();
                    int phoneNumber = view.getPhoneNumberText();
                    String teamName = view.getNameTeamText();
                    String email = view.getEmailText();
                    String name = view.getNameText();
                    try {
                        userManager.createUser(dni, userManager.generatePassword(), email); //Els hi falta parametres .
                        mainFrameGUI.showMenuUser();
                    } catch (DNIException ex) {
                        view.dniAlreadyExist();
                    }
                    break;
            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
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
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
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
