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

/**
 * The RegistrationController class handles user registration logic and interacts with the RegistrationGUI view
 * and the UserManager.
 */
public class RegistrationController implements FocusListener, ActionListener {

    private final UserManager userManager;
    private final MainFrameGUI mainFrameGUI;
    private final TopPanelGUI topPanelGUI;
    private final RegistrationGUI view;
    private final String defaultDniText = "Dni: ";
    private final String defaultDorsalText = "Dorsal: ";
    private final String defaultEmailText = "Email: ";
    private final String defaultPhoneNumberText = "Phone number: ";

    /**
     * Constructs a RegistrationController with the specified dependencies.
     *
     * @param mainFrameGUI The MainFrameGUI instance for navigating between views.
     * @param view         The RegistrationGUI instance for user input and display.
     * @param userManager  The UserManager instance for user management.
     */
    public RegistrationController(MainFrameGUI mainFrameGUI, RegistrationGUI view, UserManager userManager, TopPanelGUI topPanelGUI) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
        this.userManager = userManager;
        this.topPanelGUI = topPanelGUI;
    }

    /**
     * Handles the actionPerformed event for buttons.
     *
     * @param e The ActionEvent representing the button action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if ("OK_BUTTON".equals(e.getActionCommand())) {
                try {
                    String dni = view.getDniText();
                    int dorsal = Integer.parseInt(view.getDorsalText());
                    String phoneNumber = String.valueOf(view.getPhoneNumberText());
                    String email = view.getEmailText();

                    User user = userManager.createUser(dni, UserManager.generatePassword(), email, dorsal, phoneNumber);
                    userManager.signUp(user, user.getPassword());
                    mainFrameGUI.showMenuUser();
                    topPanelGUI.hideShowDropDownButton(true);
                    view.clear();
                } catch (InvalidPasswordException | ExistingDNIException | DNIOrMailDontExistException |
                         InvalidEmailException | EmailAlreadyExistsException | SamePasswordException |
                         DNIException | InvalidPlayerNumberException | SQLException ex) {
                    view.exceptionMessage(ex.getMessage());
                } catch (NumberFormatException nfe) {
                    view.numberFormatMessage();
                }
            }
        }
    }

    /**
     * Handles the focusGained event for text fields.
     *
     * @param e The FocusEvent representing the focus gained event.
     */
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

    /**
     * Handles the focusLost event for text fields.
     *
     * @param e The FocusEvent representing the focus lost event.
     */
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
