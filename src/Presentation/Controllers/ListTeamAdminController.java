/*
package Presentation.Controllers;

import Business.Entities.Team;
import Business.Managers.TeamManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ListTeamAdminGUI;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TeamListAdminController extends MouseInputAdapter implements ActionListener {
    private final ListTeamAdminGUI view;
    private final MainFrameGUI mainFrame;
    private final TeamManager teamManager;
    private final Team team;
    private final List<Team> selectedTeams;
    private final ListPlayerController playersListGUI;
    int i;

    public TeamListAdminController(ListTeamAdminGUI view, MainFrameGUI mainFrame, TeamManager teamManager, Team team) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.team = team;
        this.teamManager = teamManager;
        this.selectedTeams = new ArrayList<>();
        this.playersListGUI = new ListPlayerController();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = view.getTable().rowAtPoint(e.getPoint());
            int selectedColumn = view.getTable().columnAtPoint(e.getPoint());

            if (selectedRow != -1 && selectedColumn != -1) {
                Object cellValue = view.getTable().getValueAt(selectedRow, selectedColumn);
                String teamName = cellValue.toString();

                if (selectedColumn != 5) {
                    for (i = 0; i < team; i++) {
                        if (teamName.equals(teams.get(i).getName())) {
                            playersListGUI.setTitle(teamName);
                            mainFrame.showPlayerList();
                        }
                    }
                } else if (selectedColumn == 5 && cellValue instanceof Boolean) {
                    boolean isChecked = (Boolean) cellValue;
                    Team selectedTeam = teams.get(selectedRow);
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
        if (command.equals("Delete")) {
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
        view.addTeams(teams);
    }
}

 */
