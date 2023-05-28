package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.InvalidPasswordException;
import Exceptions.SamePasswordException;
import Presentation.Views.ChangePasswordGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 * The controller class for the ChangePasswordGUI.
 * Handles the focus events and action events performed in the change password view.
 */
public class ChangePasswordController implements FocusListener, ActionListener {

    private final MainFrameGUI mainFrameGUI;
    private final String defaultCurrentPassword = "Actual Password: ";
    private final String defaultNewPassword = "New Password: ";
    private final String defaultRepeatNewPassword = "Repeat New Password: ";
    private final ChangePasswordGUI view;
    private final UserManager userManager;

    /**
     * Constructs a ChangePasswordController object.
     *
     * @param mainFrameGUI The MainFrameGUI instance.
     * @param view         The ChangePasswordGUI instance.
     * @param userManager The UserManager instance.
     */
    public ChangePasswordController(MainFrameGUI mainFrameGUI, ChangePasswordGUI view, UserManager userManager) {
        this.mainFrameGUI = mainFrameGUI;
        this.view = view;
        this.userManager = userManager;
    }
    /**
     * Handles the action events performed on the buttons in the change password view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if ("OK".equals(e.getActionCommand())) {
                try {

                    String actualPassword = view.getActualPassword();
                    String newPassword = view.getNewPassword();
                    String repeatedPassword = view.getRepeatNewPassword();

                    userManager.canviContrasenya(actualPassword, newPassword, repeatedPassword);
                    view.passwordSuccess();
                    mainFrameGUI.showMenuUser();
                } catch (InvalidPasswordException | SamePasswordException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
            }
        }
    }
    /**
     * Handles the focus gained events on the password fields.
     *
     * @param e The FocusEvent that occurred.
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JPasswordField textField) {
            switch (textField.getName()) {
                case "ActualPassword":
                    if (textField.getText().equals(defaultCurrentPassword)) {
                        textField.setText("");
                    }
                    textField.setEchoChar('*');
                    break;
                case "NewPassword":
                    if (textField.getText().equals(defaultNewPassword)) {
                        textField.setText("");
                    }
                    textField.setEchoChar('*');
                    break;
                case "RepeatNewPassword":
                    if (textField.getText().equals(defaultRepeatNewPassword)) {
                        textField.setText("");
                    }
                    textField.setEchoChar('*');
                    break;
            }
        }
    }

    /**
     * Handles the focus lost events on the password fields.
     *
     * @param e The FocusEvent that occurred.
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JPasswordField textField) {
            switch (textField.getName()) {
                case "ActualPassword":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultCurrentPassword);
                        textField.setEchoChar((char) 0);
                    }

                    break;
                case "NewPassword":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultNewPassword);
                        textField.setEchoChar((char) 0);
                    }

                    break;
                case "RepeatNewPassword":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultRepeatNewPassword);
                        textField.setEchoChar((char) 0);
                    }
                    break;
            }
        }
    }
}
