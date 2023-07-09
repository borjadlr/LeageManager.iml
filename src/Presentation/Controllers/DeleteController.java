package Presentation.Controllers;

import Business.Managers.UserManager;
import Exceptions.*;
import Presentation.Views.DeleteGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 * The controller class for the DeleteGUI.
 * Handles the focus events and action events performed in the delete user view.
 */
public class DeleteController implements ActionListener, FocusListener {

    private static final String EMAIL_OR_DNI = "Email or DNI";
    private static final String PASSWORD = "Password";
    private static final String BLANK = "";
    private static final String defaultEmailText = "dni/email: ";
    private static final String defaultPasswordText = "Password: ";
    private static final String DELETE_BUTTON = "DELETE_BUTTON";

    private final MainFrameGUI mainFrameGUI;
    private final DeleteGUI view;
    private final UserManager userManager;
    /**
     * Constructs a DeleteController object.
     *
     * @param mainView     The MainFrameGUI instance.
     * @param view         The DeleteGUI instance.
     * @param userManager The UserManager instance.
     */
    public DeleteController(MainFrameGUI mainView, DeleteGUI view, UserManager userManager) {
        this.mainFrameGUI = mainView;
        this.view = view;
        this.userManager = userManager;
    }
    /**
     * Handles the action events performed on the delete button in the delete user view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (DELETE_BUTTON.equals(e.getActionCommand())) {
            try {
                String username = view.getUsernameInfo();
                String password = view.getPasswordInfo();
                userManager.deleteUser(username, password);
                mainFrameGUI.showMainPanel();

            } catch (IncorrectPassword4UserException | DNIOrMailDontExistException ex) {
                view.exceptionMessage(ex.getMessage());
            }
        }
    }
    /**
     * Handles the focus gained events on the text fields.
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
     * Handles the focus lost events on the text fields.
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


