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
    private final  TeamManager teamManager;
    private final List<Team> selectedTeams;

    /**
     * Constructs a ListTeamUserController object.
     *
     * @param listTeamUserGUI The ListTeamUserGUI instance.
     * @param teamManager     The TeamManager instance.
     */
    public ListTeamUserController(ListTeamUserGUI listTeamUserGUI, TeamManager teamManager) {
        this.listTeamUserGUI = listTeamUserGUI;
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
                    listTeamUserGUI.exceptionMessage(ex.getMessage());
                }
                if (!selectedTeams.contains(selectedTeam)) {
                    selectedTeams.add(selectedTeam);
                }
            }
        }
    }

}
