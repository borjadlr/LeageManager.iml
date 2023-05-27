package Presentation.Controllers;

import Business.Entities.League;
import Business.Managers.LeagueManager;
import Exceptions.IncorrectLeagueNameException;
import Presentation.Views.ListLeagueAdminGUI;
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
    private ListLeagueAdminGUI view;
    private LeagueManager leagueManager;
    private List<League> selectedLeagues;
    private ListTeamUserGUI listTeamUserGUI;
    private MainFrameGUI mainFrame;
    private int i;

    public ListLeagueAdminController(ListLeagueAdminGUI view, LeagueManager leagueManager, ListTeamUserGUI listTeamUserGUI, MainFrameGUI mainFrame) {
        this.view = view;
        this.leagueManager = leagueManager;
        this.selectedLeagues = new ArrayList<>();
        this.listTeamUserGUI = listTeamUserGUI;
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
                            listTeamUserGUI.setTitle(leagueName);
                            mainFrame.showTeamList();
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Manejar la excepción según sea necesario
                }
            } else if (selectedColumn == 3 && cellValue instanceof Boolean) {
                Boolean isChecked = (Boolean) view.getTable().getValueAt(selectedRow, selectedColumn);
                try {
                    League selectedLeague = leagueManager.listLeagues().get(selectedRow);

                    if (isChecked) {
                        if (!selectedLeagues.contains(selectedLeague)) {
                            selectedLeagues.add(selectedLeague);
                        }
                    } else {
                        selectedLeagues.remove(selectedLeague);
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
                JOptionPane.showMessageDialog(null, "Please select at least one league to delete.",
                        "No League Selected", JOptionPane.WARNING_MESSAGE);
            } else {
                int confirmDialog = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete the selected leagues?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirmDialog == JOptionPane.YES_OPTION) {
                    for (League league : selectedLeagues) {
                        try {
                            leagueManager.deleteLeague(String.valueOf(league));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            // Manejar la excepción según sea necesario
                        } catch (IncorrectLeagueNameException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    //refreshTable();
                    selectedLeagues.clear();
                }
            }
        }
    }
/*
    public void refreshTable() throws SQLException {
        view.addLeagues(leagueManager.listLeagues());
    }

 */
}
