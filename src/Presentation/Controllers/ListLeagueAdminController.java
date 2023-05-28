package Presentation.Controllers;

import Business.Entities.League;
import Business.Entities.Team;
import Business.Managers.LeagueManager;
import Business.Managers.TeamManager;
import Exceptions.MatchIsPlayingException;
import Presentation.Views.ListLeagueAdminGUI;
import Presentation.Views.ListTeamAdminGUI;
import Presentation.Views.MainFrameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The controller class for the ListLeagueAdminGUI.
 * Handles the mouse and action events performed in the list league admin view.
 */

public class ListLeagueAdminController extends MouseAdapter implements ActionListener {
    private final ListLeagueAdminGUI view;
    private final LeagueManager leagueManager;
    private final List<League> selectedLeagues;
    private final ListTeamAdminGUI listTeamAdminGUI;
    private final TeamManager teamManager;
    private final MainFrameGUI mainFrame;
    private String leaguenames;

    /**
     * Constructs a ListLeagueAdminController object.
     *
     * @param view              The ListLeagueAdminGUI instance.
     * @param leagueManager     The LeagueManager instance.
     * @param listTeamAdminGUI  The ListTeamAdminGUI instance.
     * @param mainFrame         The MainFrameGUI instance.
     * @param teamManager       The TeamManager instance.
     */
    public ListLeagueAdminController(ListLeagueAdminGUI view, LeagueManager leagueManager, ListTeamAdminGUI listTeamAdminGUI, MainFrameGUI mainFrame, TeamManager teamManager) {
        this.view = view;
        this.leagueManager = leagueManager;
        this.selectedLeagues = new ArrayList<>();
        this.listTeamAdminGUI = listTeamAdminGUI;
        this.mainFrame = mainFrame;
        this.teamManager = teamManager;
        this.leaguenames = "";
    }
    /**
     * Handles the mouse clicked events in the list league admin view.
     *
     * @param e The MouseEvent that occurred.
     */

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
                    int i;
                    for (i = 0; i < leagues.size(); i++) {
                        if (leagueName.equals(leagues.get(i).getName())) {
                            leaguenames = leagueName;
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
    /**
     * Handles the action events performed in the list league admin view.
     *
     * @param e The ActionEvent that occurred.
     */
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
    /**
     * Refreshes the table in the list league admin view with updated league data.
     *
     * @throws SQLException If an SQL exception occurs while retrieving the league data.
     */
    public void refreshTable() throws SQLException {
        view.addLeagues(leagueManager.listLeagues());
    }
    /**
     * Returns the names of the selected leagues.
     *
     * @return The names of the selected leagues.
     */
    public String getLeaguenames(){
        return leaguenames;
    }

}
