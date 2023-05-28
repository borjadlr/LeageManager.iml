package Presentation.Controllers;

import Business.Entities.League;
import Business.Entities.Team;
import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Exceptions.IncorrectLeagueNameException;
import Exceptions.MatchIsPlayingException;
import Presentation.Views.ListLeagueAdminGUI;
import Presentation.Views.ListTeamAdminGUI;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ListTeamUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListLeagueAdminController extends MouseAdapter implements ActionListener {
    private final ListLeagueAdminGUI view;
    private final LeagueManager leagueManager;
    private final List<League> selectedLeagues;
    private final ListTeamAdminGUI listTeamAdminGUI;
    private final TeamManager teamManager;
    private final MainFrameGUI mainFrame;
    private int i;

    public ListLeagueAdminController(ListLeagueAdminGUI view, LeagueManager leagueManager, ListTeamAdminGUI listTeamAdminGUI, MainFrameGUI mainFrame, TeamManager teamManager) {
        this.view = view;
        this.leagueManager = leagueManager;
        this.selectedLeagues = new ArrayList<>();
        this.listTeamAdminGUI = listTeamAdminGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<Team> teams;

        int selectedRow = view.getTable().rowAtPoint(e.getPoint());
        int selectedColumn = view.getTable().columnAtPoint(e.getPoint());

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            Object cellValue = view.getTable().getValueAt(selectedRow, selectedColumn);
            String leagueName = cellValue.toString();
            if (selectedColumn != 3) {
                try {
                    List<League> leagues = leagueManager.listLeagues();
                    for (i = 0; i < leagues.size(); i++) {
                        if (leagueName.equals(leagues.get(i).getName())) {
                            System.out.println(leagues.get(i).getName());
                            teams = teamManager.getTeamsOfLeague(leagues.get(i).getName());
                            listTeamAdminGUI.addTeams(teams);
                            listTeamAdminGUI.setTitle(leagueName);
                            mainFrame.showTeamListAdminView();
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (cellValue instanceof Boolean) {
                Boolean isChecked = (Boolean) view.getTable().getValueAt(selectedRow, selectedColumn);
                try {
                    League selectedLeague = leagueManager.listLeagues().get(selectedRow);
                    System.out.println(selectedLeague.getName());

                    if (isChecked) {
                        if (!selectedLeagues.contains(selectedLeague)) {
                            selectedLeagues.add(selectedLeague);
                            System.out.println(selectedLeague.getName());
                        }
                    } else {
                        selectedLeagues.remove(selectedLeague);
                        System.out.println(selectedLeague.getName());
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
            if (selectedLeagues.isEmpty()) {
                view.showWarningAtLeastOneLeague();
            } else {
                int confirmDialog = view.showAreYouSureDelete();
                if (confirmDialog == JOptionPane.YES_OPTION) {
                    List<League> leaguesToRemove = new ArrayList<>();
                    for (League league : selectedLeagues) {
                        boolean isChecked = false;
                        int row = 0;
                        try {
                            List<League> leagues = leagueManager.listLeagues();
                            for (int i = 0; i < leagues.size(); i++) {
                                if (league.getName().equals(leagues.get(i).getName())) {
                                    isChecked = (Boolean) view.getTable().getValueAt(i, 3);
                                    row = i;
                                    break;
                                }
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        if (isChecked) {
                            leaguesToRemove.add(league);
                        } else {
                            view.getTable().setValueAt(false, row, 3);
                        }
                    }
                    for (League league : leaguesToRemove) {
                        try {
                            System.out.println("league name: " + league.getName());
                            leagueManager.deleteLeague(league.getName());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (MatchIsPlayingException ex) {
                            view.exceptionMessage("ERROR");
                        }
                    }
                    try {
                        refreshTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    selectedLeagues.clear();
                }
            }
        }
    }
    public void refreshTable() throws SQLException {
        view.addLeagues(leagueManager.listLeagues());
    }

}
