package Presentation.Controllers;

import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Exceptions.DateExpiredException;
import Exceptions.LeagueAlreadyExistsException;
import Exceptions.RepeatedTeamException;
import Exceptions.WrongTeamNumberException;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.NewLeagueGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class NewLeagueController implements ActionListener, FocusListener {

    private final MainFrameGUI mainFrame;
    private final NewLeagueGUI view;
    private final String defaultDateText = "Date: ";
    private final String defaultLegueText = "League name: ";

    private final String defaultNumeroEquips = "Número equips: ";
    private final String defaultHora = "Hour: ";
    private final LeagueManager leagueManager;
    private final TeamManager teamManager;
    public NewLeagueController(MainFrameGUI mainFrame, NewLeagueGUI view, LeagueManager leagueManager, TeamManager teamManager) {
        this.mainFrame = mainFrame;
        this.leagueManager = leagueManager;
        this.teamManager = teamManager;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "OK_BUTTON":

                    try {
                        String leagueName = view.getLeagueName();
                        Date data = leagueManager.stringToDate(view.getData());
                        Time hora = leagueManager.stringToTime(view.getHora());
                        String numeroEquipos = view.getNumeroEquipos();
                        leagueManager.introduceLeague(leagueManager.setLeague(leagueName, data, hora, 1, parseInt(numeroEquipos), true, teamManager.getTeamsOfLeague(leagueName)));
                        mainFrame.showTeamList();
                        view.clearTextFields();

                        break;

                    } catch (LeagueAlreadyExistsException | RepeatedTeamException | DateExpiredException | WrongTeamNumberException ex) {
                        view.exceptionMessage(ex.getMessage());
                    } catch (SQLException | ParseException ex) {
                        throw new RuntimeException(ex);
                    }

            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
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
        if (e.getSource() instanceof JTextField) {
            JTextField textField = (JTextField) e.getSource();
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
