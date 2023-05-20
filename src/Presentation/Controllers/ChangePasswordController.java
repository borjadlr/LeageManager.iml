package Presentation.Controllers;

import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ChangePasswordController implements FocusListener, ActionListener {

    private MainFrameGUI mainFrameGUI;

    private String defaultCurrentPassword = "Actual Password: ";
    private String defaultNewPassword = "New Password: ";
    private String defaultRepeatNewPassword = "Repeat New Password: ";

    public ChangePasswordController(MainFrameGUI mainFrameGUI) {
        this.mainFrameGUI = mainFrameGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "OK":
                    mainFrameGUI.showMenuUser();
                    break;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JPasswordField) {
            JPasswordField textField = (JPasswordField) e.getSource();
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

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JPasswordField) {
            JPasswordField textField = (JPasswordField) e.getSource();
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
