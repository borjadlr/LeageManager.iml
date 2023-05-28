package Presentation.Controllers;

import Business.Entities.Team;
import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamListCreateLeagueController extends MouseAdapter implements ActionListener {
    private final TeamListCreateLeague view;
    private final NewLeagueController newLeagueController;
    private final TeamManager teamManager;
    private final List<Team> selectedTeams;
    private final ListTeamAdminGUI listTeamAdminGUI;
    private final LeagueManager leagueManager;
    private final MainFrameGUI mainFrame;
    private int i;

    public TeamListCreateLeagueController(TeamListCreateLeague view, TeamManager teamManager, ListTeamAdminGUI listTeamAdminGUI, MainFrameGUI mainFrame, LeagueManager leagueManager, NewLeagueController newLeagueController) {
        this.view = view;
        this.selectedTeams = new ArrayList<>();
        this.listTeamAdminGUI = listTeamAdminGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.leagueManager = leagueManager;
        this.newLeagueController = newLeagueController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<Team> teams;

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
                    // Manejar la excepción según sea necesario
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int i = 0;

        if (command.equals("Add Teams")) {
            if (selectedTeams.isEmpty()) {
                view.showWarningAtLeastOneTeam();
            } else {
                int confirmDialog = view.showAreYouSure();
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    try {
                        leagueManager.introduceTeamsLeague(selectedTeams, newLeagueController.getName());
                        System.out.println(newLeagueController.getName());
                        mainFrame.showMenuAdmin();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        refreshTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    selectedTeams.clear();
                }
            }
        }
    }
    public void refreshTable() throws SQLException {
        view.addTeams(teamManager.getAllTeams());
    }

}
