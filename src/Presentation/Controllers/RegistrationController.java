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

    private final UserManager userManager;
    private final MainFrameGUI mainFrameGUI;

    private final RegistrationGUI view;

    private final String defaultDniText = "Dni: ";
    private final String defaultDorsalText = "Dorsal: ";
    private final String defaultEmailText = "Email: ";
    private final String defaultPhoneNumberText = "Phone number: ";

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
                    try {
                        String dni = view.getDniText();
                        int dorsal = Integer.parseInt(view.getDorsalText());
                        String phoneNumber = String.valueOf(view.getPhoneNumberText());
                        String email = view.getEmailText();

                        User user = userManager.createUser(dni, UserManager.generatePassword(), email, dorsal, phoneNumber);

                        userManager.signUp(user, user.getPassword());
                        mainFrameGUI.showMenuUser();
                    } catch (InvalidPasswordException | ExistingDNIException | DNIOrMailDontExistException |
                             InvalidEmailException | EmailAlreadyExistsException | SamePasswordException |
                             DNIException | InvalidPlayerNumberException | SQLException ex ) {
                        view.exceptionMessage(ex.getMessage());
                    } catch (NumberFormatException nfe) {
                        view.numberFormatMessage();
                    }
                    break;
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
                case "Dorsal":
                    if (textField.getText().equals(defaultDorsalText)) {
                        textField.setText("");
                    }
                    break;
                case "Email":
                    if (textField.getText().equals(defaultEmailText)) {
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
                case "Dorsal":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultDorsalText);
                    }
                    break;
                case "Email":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultEmailText);
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
