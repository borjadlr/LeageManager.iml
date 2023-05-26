package Presentation.Controllers;

import Business.Entities.Team;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.TeamListGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TeamListController extends MouseInputAdapter {
    private final TeamListGUI teamListGUI;
    private MainFrameGUI mainFrame;
    private final List<Team> teams;
    private final List<Team> selectedTeams;

    public TeamListController(TeamListGUI teamListGUI, MainFrameGUI mainFrame, List<Team> teams) {
        this.teamListGUI = teamListGUI;
        this.mainFrame = mainFrame;
        this.teams = teams;
        this.selectedTeams = new ArrayList<>();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = teamListGUI.getTable().rowAtPoint(e.getPoint());
            if (selectedRow != -1) {
                Team selectedTeam = teams.get(selectedRow);
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
