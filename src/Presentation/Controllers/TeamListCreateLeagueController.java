package Presentation.Controllers;

import Business.Entities.Team;
import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Exceptions.NumberOfTeamsDoNotRelateException;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class for the TeamListCreateLeague view.
 * Handles user interactions and events related to team selection and league creation.
 */
public class TeamListCreateLeagueController extends MouseAdapter implements ActionListener {
    private final TeamListCreateLeague view;
    private final NewLeagueController newLeagueController;
    private final TeamManager teamManager;
    private final List<Team> selectedTeams;
    private final LeagueManager leagueManager;
    private final MainFrameGUI mainFrame;

    /**
     * Constructs a TeamListCreateLeagueController object.
     *
     * @param view               The TeamListCreateLeague view associated with this controller.
     * @param teamManager        The TeamManager instance used to manage teams.
     * @param mainFrame          The MainFrameGUI instance representing the main application frame.
     * @param leagueManager      The LeagueManager instance used to manage leagues.
     * @param newLeagueController The NewLeagueController instance associated with this controller.
     */
    public TeamListCreateLeagueController(TeamListCreateLeague view, TeamManager teamManager, MainFrameGUI mainFrame, LeagueManager leagueManager, NewLeagueController newLeagueController) {
        this.view = view;
        this.selectedTeams = new ArrayList<>();
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.leagueManager = leagueManager;
        this.newLeagueController = newLeagueController;
    }

    /**
     * Handles the mouseClicked event for team selection in the view.
     *
     * @param e The MouseEvent object representing the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int selectedRow = view.getTable().rowAtPoint(e.getPoint());
        int selectedColumn = view.getTable().columnAtPoint(e.getPoint());

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            Object cellValue = view.getTable().getValueAt(selectedRow, selectedColumn);

            if (cellValue instanceof Boolean) {
                Boolean isChecked = (Boolean) view.getTable().getValueAt(selectedRow, selectedColumn);
                try {
                    Team selectedTeam = teamManager.getAllTeams().get(selectedRow);
                    if (isChecked) {
                        if (!selectedTeams.contains(selectedTeam)) {
                            selectedTeams.add(selectedTeam);
                        }
                    } else {
                        selectedTeams.remove(selectedTeam);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Handles the actionPerformed event for button clicks in the view.
     *
     * @param e The ActionEvent object representing the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add Teams")) {
            if (selectedTeams.isEmpty()) {
                view.showWarningAtLeastOneTeam();
            } else {
                int confirmDialog = view.showAreYouSure();
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    try {
                        leagueManager.introduceTeamsLeague(selectedTeams, newLeagueController.getName());
                        mainFrame.showMenuAdmin();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (NumberOfTeamsDoNotRelateException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    for (int row = 0; row < view.getTable().getRowCount(); row++) {
                        view.getTable().getModel().setValueAt(false, row, 1);
                    }
                    view.getTable().clearSelection();
                    selectedTeams.clear();
                }
            }
        }
    }
}
