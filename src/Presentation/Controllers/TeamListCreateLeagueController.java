/*package Presentation.Controllers;

import Business.Entities.Team;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.TeamListCreateLeague;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TeamListCreateLeagueController extends MouseInputAdapter implements ActionListener {
    private final TeamListCreateLeague teamListCreateLeague;
    private final MainFrameGUI mainFrameGUI;
    private final List<Team> selectedTeams;

    int i;

    public TeamListCreateLeagueController(TeamListCreateLeague teamListCreateLeague, MainFrameGUI mainFrameGUI) {
        this.teamListCreateLeague = teamListCreateLeague;
        this.mainFrameGUI = mainFrameGUI;
        this.selectedTeams = new ArrayList<>();
    }

    public void mouseClicked(MouseEvent e) {
        List<Team> teams;

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = teamListCreateLeague.getTable().rowAtPoint(e.getPoint());
            int selectedColumn = teamListCreateLeague.getTable().columnAtPoint(e.getPoint());

            if (selectedRow != -1 && e.getButton() == MouseEvent.BUTTON1) {
                Object cellValue = teamListCreateLeague.getTable().getValueAt(selectedRow, selectedColumn);
                String teamName = cellValue.toString();

                if (selectedColumn == 1 && cellValue instanceof Boolean) {
                    boolean isChecked = (Boolean) cellValue;
                    Team selectedTeam = teamListCreateLeague.get(selectedRow);
                    if (isChecked) {
                        if (!selectedTeams.contains(selectedTeam)) {
                            selectedTeams.add(selectedTeam);
                        }
                    } else {
                        selectedTeams.remove(selectedTeam);
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add leagues")) {
            if (selectedTeams.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select at least one team to delete.",
                        "No Teams Selected", JOptionPane.WARNING_MESSAGE);
            } else {
                int confirmDialog = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete the selected teams?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirmDialog == JOptionPane.YES_OPTION) {
                    for (Team team : selectedTeams) {
                        teams.remove(team);
                    }
                    refreshTable();
                    selectedTeams.clear();
                }
            }
        }
    }
    public void refreshTable() {
        teamListCreateLeague.addTeams(teams);
    }
}

 */



