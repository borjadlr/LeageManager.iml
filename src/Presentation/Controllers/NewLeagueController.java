package Presentation.Controllers;

import Business.Managers.LeagueManager;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class NewLeagueController implements ActionListener, FocusListener {

    private final MainFrameGUI mainFrame;
    private final String defaultDateText = "Date: ";
    private final String defaultLegueText = "League name: ";

    private final LeagueManager leagueManager;

    private final String defaultNumeroEquips = "Número equips: ";
    private final String defaultHora = "Hour: ";
    public NewLeagueController(MainFrameGUI mainFrame, LeagueManager leagueManager) {
        this.mainFrame = mainFrame;
        this.leagueManager = leagueManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "OK_BUTTON":
                    leagueManager.introduceLeague();
                    mainFrame.showTeamList();
                    break;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case "Date":
                    if (textField.getText().equals(defaultDateText)) {
                        textField.setText("");
                    }
                    break;
                case "New League":
                    if (textField.getText().equals(defaultLegueText)) {
                        textField.setText("");
                    }
                    break;
                case "Número equips":
                    if (textField.getText().equals(defaultNumeroEquips)) {
                        textField.setText("");
                    }
                    break;
                case "Hora":
                    if (textField.getText().equals(defaultHora)) {
                        textField.setText("");
                    }
                    break;
            }
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case "Date":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultDateText);
                    }
                    break;
                case "New League":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultLegueText);
                    }
                    break;
                case "Número equips":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultNumeroEquips);
                    }
                    break;
                case "Hora":
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultHora);
                    }
                    break;
            }
        }
    }
}
