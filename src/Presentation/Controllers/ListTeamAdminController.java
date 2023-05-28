package Presentation.Controllers;

import Business.Entities.League;
import Business.Entities.Team;
import Business.Entities.User;
import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Business.Managers.UserManager;
import Exceptions.IncorrectLeagueNameException;
import Exceptions.IncorrectTeamNameException;
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

public class ListTeamAdminController extends MouseAdapter implements ActionListener {
    private final ListTeamAdminGUI view;
    private final UserManager userManager;
    private final List<Team> selectedTeams;
    private final ListPlayerGUI listPlayerGUI;
    private final TeamManager teamManager;
    private final MainFrameGUI mainFrame;
    private final ListLeagueAdminController listLeagueAdminController;
    private int i;

    public ListTeamAdminController(ListTeamAdminGUI view, UserManager userManager, ListPlayerGUI listPlayerGUI, MainFrameGUI mainFrame, TeamManager teamManager, ListLeagueAdminController listLeagueAdminController) {
        this.view = view;
        this.userManager = userManager;
        this.selectedTeams = new ArrayList<>();
        this.listPlayerGUI = listPlayerGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.listLeagueAdminController = listLeagueAdminController;
    }

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
                    ex.printStackTrace();
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
                    ex.printStackTrace();
                    // Manejar la excepción según sea necesario
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Delete")) {
            System.out.println("HOLA");
            if (selectedTeams.isEmpty()) {
                //view.showWarningAtLeastOneLeague();
            } else {
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
                            ex.printStackTrace();
                        }
                        if (isChecked) {
                            teamsToRemove.add(teams);
                        } else {
                            view.getTable().setValueAt(false, row, 3);
                        }
                    }
                    for (Team team : teamsToRemove) {
                        try {
                            System.out.println("Team name: " + team.getName());
                            teamManager.deleteTeam(team.getName());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
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
        view.addTeams(teamManager.getTeamsOfLeague(listLeagueAdminController.getLeaguenames()));
    }

}
