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

public class ListTeamUserController extends MouseInputAdapter {
    private final ListTeamUserGUI listTeamUserGUI;
    private final MainFrameGUI mainFrame;

    private final  TeamManager teamManager;
    private final List<Team> selectedTeams;

    public ListTeamUserController(ListTeamUserGUI listTeamUserGUI, MainFrameGUI mainFrame, TeamManager teamManager) {
        this.listTeamUserGUI = listTeamUserGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.selectedTeams = new ArrayList<>();
    }

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
                    //System.out.println("Selected team: " + selectedTeam.getName());
                } else {
                    System.out.println("Team already selected: " + selectedTeam.getName());
                }
            }
        }
    }

    public List<Team> getSelectedTeams() {
        return selectedTeams;
    }
}
