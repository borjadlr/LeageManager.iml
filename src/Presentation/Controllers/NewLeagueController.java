package Presentation.Controllers;

import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Exceptions.*;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.NewLeagueGUI;
import Presentation.Views.TeamListCreateLeague;
import Presentation.Views.TopPanelGUI;
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

/**
 * The controller class for the NewLeagueGUI.
 * Handles the actions and events in the new league creation view.
 */

public class NewLeagueController implements ActionListener, FocusListener {

    private final MainFrameGUI mainFrame;
    private String league_Name;
    private final NewLeagueGUI view;
    private final TopPanelGUI topPanelGUI;
    private final String defaultDateText = "Date: ";
    private final String defaultLegueText = "League name: ";
    private final String defaultNumeroEquips = "Número equips: ";
    private final String defaultHora = "Hour: ";
    private static final String OK_BUTTON = "OK_BUTTON";
    private static final String NEW_LEAGUE = "New League";
    private static final String HOUR = "Hour";
    private static final String DATE = "Date";
    private static final String TEAM_NUMBER = "Team Number";
    private static final String BLANK = "";
    private final LeagueManager leagueManager;
    private final TeamListCreateLeague teamListCreateLeague;
    private final TeamManager teamManager;

    /**
     * Constructs a NewLeagueController object.
     *
     * @param mainFrame          The MainFrameGUI instance.
     * @param view               The NewLeagueGUI instance.
     * @param leagueManager      The LeagueManager instance.
     * @param teamManager        The TeamManager instance.
     * @param teamListCreateLeague The TeamListCreateLeague instance.
     * @param topPanelGUI        The TopPanelGUI instance.
     */
    public NewLeagueController(MainFrameGUI mainFrame, NewLeagueGUI view, LeagueManager leagueManager, TeamManager teamManager, TeamListCreateLeague teamListCreateLeague, TopPanelGUI topPanelGUI) {
        this.mainFrame = mainFrame;
        this.leagueManager = leagueManager;
        this.teamManager = teamManager;
        this.view = view;
        this.teamListCreateLeague = teamListCreateLeague;
        this.topPanelGUI = topPanelGUI;
    }

    /**
     * Handles the action events in the new league creation view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (OK_BUTTON.equals(e.getActionCommand())) {
                try {
                    String date = leagueManager.correctData(view.getData());
                    String time = leagueManager.correctTime(view.getHora());
                    String leagueName = view.getLeagueName();
                    league_Name = leagueName;
                    Date data = leagueManager.stringToDate(date);
                    Time hora = leagueManager.stringToTime(time);
                    String numeroEquipos = view.getNumeroEquipos();
                    leagueManager.introduceLeague(leagueManager.setLeague(leagueName, data, hora, 1, parseInt(numeroEquipos), true, teamManager.getTeamsOfLeague(leagueName)));
                    teamListCreateLeague.addTeams(teamManager.getAllTeams());
                    topPanelGUI.hideBackButton(false);
                    mainFrame.showTeamsNewLeague();
                    view.clearTextFields();
                } catch (LeagueAlreadyExistsException | WrongTimeException | RepeatedTeamException |
                         DateExpiredException | WrongTeamNumberException | SQLException | ParseException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
            }
        }
    }

    /**
     * Handles the focus gained events in the text fields.
     *
     * @param e The FocusEvent that occurred.
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case DATE:
                    if (textField.getText().equals(defaultDateText)) {
                        textField.setText(BLANK);
                    }
                    break;
                case NEW_LEAGUE:
                    if (textField.getText().equals(defaultLegueText)) {
                        textField.setText(BLANK);
                    }
                    break;
                case TEAM_NUMBER:
                    if (textField.getText().equals(defaultNumeroEquips)) {
                        textField.setText(BLANK);
                    }
                    break;
                case HOUR:
                    if (textField.getText().equals(defaultHora)) {
                        textField.setText(BLANK);
                    }
                    break;
            }
        }
    }

    /**
     * Handles the focus lost events in the text fields.
     *
     * @param e The FocusEvent that occurred.
     */
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField textField) {
            switch (textField.getName()) {
                case DATE:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultDateText);
                    }
                    break;
                case NEW_LEAGUE:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultLegueText);
                    }
                    break;
                case TEAM_NUMBER:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultNumeroEquips);
                    }
                    break;
                case HOUR:
                    if (textField.getText().isEmpty()) {
                        textField.setText(defaultHora);
                    }
                    break;
            }
        }
    }

    /**
     * Returns the name of the league.
     *
     * @return The name of the league.
     */

    public String getName(){
        return league_Name;
    }
}
