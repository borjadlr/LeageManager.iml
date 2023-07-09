package Presentation.Controllers;

import Business.Entities.Team;
import Business.Managers.TeamManager;
import Presentation.Views.ListTeamUserGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The controller class for the ListTeamUserGUI.
 * Handles the mouse events in the list team user view.
 */
public class ListTeamUserController extends MouseInputAdapter {
    private final ListTeamUserGUI listTeamUserGUI;
    private final MainFrameGUI mainFrame;

    private final  TeamManager teamManager;
    private final List<Team> selectedTeams;
    /**
     * Constructs a ListTeamUserController object.
     *
     * @param listTeamUserGUI The ListTeamUserGUI instance.
     * @param mainFrame       The MainFrameGUI instance.
     * @param teamManager     The TeamManager instance.
     */
    public ListTeamUserController(ListTeamUserGUI listTeamUserGUI, MainFrameGUI mainFrame, TeamManager teamManager) {
        this.listTeamUserGUI = listTeamUserGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.selectedTeams = new ArrayList<>();
    }
    /**
     * Handles the mouse clicked events in the list team user view.
     *
     * @param e The MouseEvent that occurred.
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = listTeamUserGUI.getTable().rowAtPoint(e.getPoint());
            if (selectedRow != -1) {
                Team selectedTeam = null;
                try {
                    selectedTeam = teamManager.getAllTeams().get(selectedRow);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (!selectedTeams.contains(selectedTeam)) {
                    selectedTeams.add(selectedTeam);
                } else {
                    System.out.println("Team already selected: " + selectedTeam.getName());
                }
            }
        }
    }
    /**
     * Returns the list of selected teams.
     *
     * @return The list of selected teams.
     */
    public List<Team> getSelectedTeams() {
        return selectedTeams;
    }
}
