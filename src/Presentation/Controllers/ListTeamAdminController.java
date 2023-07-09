package Presentation.Controllers;

import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.TeamManager;
import Exceptions.MatchIsPlayingException;
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
 * The controller class for the ListTeamAdminGUI.
 * Handles the mouse events and actions performed in the list team admin view.
 */
public class ListTeamAdminController extends MouseAdapter implements ActionListener {
    private final ListTeamAdminGUI view;
    private final List<Team> selectedTeams;
    private final ListPlayerGUI listPlayerGUI;
    private final TeamManager teamManager;
    private final MainFrameGUI mainFrame;
    private final ListLeagueAdminController listLeagueAdminController;
    private static final String DELETE = "Delete";


    /**
     * Constructs a ListTeamAdminController object.
     *
     * @param view                    The ListTeamAdminGUI instance.
     * @param listPlayerGUI           The ListPlayerGUI instance.
     * @param mainFrame               The MainFrameGUI instance.
     * @param teamManager             The TeamManager instance.
     * @param listLeagueAdminController The ListLeagueAdminController instance.
     */
    public ListTeamAdminController(ListTeamAdminGUI view, ListPlayerGUI listPlayerGUI, MainFrameGUI mainFrame, TeamManager teamManager, ListLeagueAdminController listLeagueAdminController) {
        this.view = view;
        this.selectedTeams = new ArrayList<>();
        this.listPlayerGUI = listPlayerGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.listLeagueAdminController = listLeagueAdminController;
    }

    /**
     * Handles the mouse clicked events in the list team admin view.
     *
     * @param e The MouseEvent that occurred.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        List<User> users;

        int selectedRow = view.getTable().rowAtPoint(e.getPoint());
        int selectedColumn = view.getTable().columnAtPoint(e.getPoint());

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            Object cellValue = view.getTable().getValueAt(selectedRow, selectedColumn);
            String teamName = cellValue.toString();
            if (selectedColumn != 6) {
                try {
                    List<Team> teamList = teamManager.getAllTeams();
                    int i;
                    for (i = 0; i < teamList.size(); i++) {
                        if (teamName.equals(teamList.get(i).getName())) {
                            System.out.println(teamList.get(i).getName());
                            users = teamManager.getPlayersOfTeam(teamName);
                            listPlayerGUI.addPlayers(users);
                            listPlayerGUI.setTitle(teamName);
                            mainFrame.showUserList();
                        }
                    }
                } catch (SQLException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
            } else if (cellValue instanceof Boolean) {
                Boolean isChecked = (Boolean) view.getTable().getValueAt(selectedRow, selectedColumn);
                try {
                    Team selectedTeam = teamManager.getTeamsOfLeague(listLeagueAdminController.getLeaguenames()).get(selectedRow);

                    if (isChecked) {
                        if (!selectedTeams.contains(selectedTeam)) {
                            selectedTeams.add(selectedTeam);
                            System.out.println(selectedTeam.getName());
                        }
                    } else {
                        selectedTeams.remove(selectedTeam);
                        System.out.println(selectedTeam.getName());
                    }
                } catch (SQLException ex) {
                    view.exceptionMessage(ex.getMessage());
                }
            }
        }
    }

    /**
     * Handles the action events in the list team admin view.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(DELETE)) {

            if (!selectedTeams.isEmpty()) {
                int confirmDialog = view.showAreYouSureDelete();
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    List<Team> teamsToRemove = new ArrayList<>();
                    for (Team teams : selectedTeams) {
                        boolean isChecked = false;
                        int row = 0;
                        try {
                            List<Team> teamss = teamManager.getTeamsOfLeague(listLeagueAdminController.getLeaguenames());
                            for (int i = 0; i < teamss.size(); i++) {
                                if (teams.getName().equals(teamss.get(i).getName())) {
                                    isChecked = (Boolean) view.getTable().getValueAt(i, 6);
                                    row = i;
                                    break;
                                }
                            }
                        } catch (SQLException ex) {
                            view.exceptionMessage(ex.getMessage());
                        }
                        if (isChecked) {
                            teamsToRemove.add(teams);
                        } else {
                            view.getTable().setValueAt(false, row, 3);
                        }
                    }
                    for (Team team : teamsToRemove) {
                        try {
                            teamManager.deleteTeam(team.getName());
                        } catch (SQLException | MatchIsPlayingException ex) {
                            view.exceptionMessage(ex.getMessage());
                        }
                    }
                    refreshTable();
                    selectedTeams.clear();
                }
            }
        }
    }

    /**
     * Refreshes the table in the list team admin view.
     *
     */
    public void refreshTable(){
        try {
            view.addTeams(teamManager.getTeamsOfLeague(listLeagueAdminController.getLeaguenames()));
        } catch (SQLException e) {
            view.exceptionMessage(e.getMessage());
        }
    }
}
