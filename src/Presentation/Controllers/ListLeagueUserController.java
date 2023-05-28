package Presentation.Controllers;

import Business.Entities.League;
import Business.Managers.LeagueManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ListLeagueUserGUI;
import Presentation.Views.ListTeamUserGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ListLeagueUserController extends MouseInputAdapter {
    private final ListLeagueUserGUI listLeaguesUserGUI;
    private final MainFrameGUI mainFrame;
    private final ListTeamUserGUI listTeamUserGUI;
    private final LeagueManager leagueManager;
    private final League league;

    public ListLeagueUserController(ListLeagueUserGUI listLeaguesUserGUI, MainFrameGUI mainFrame, ListTeamUserGUI listTeamUserGUI, LeagueManager leagueManager, League league) {
        this.listLeaguesUserGUI = listLeaguesUserGUI;
        this.mainFrame = mainFrame;
        this.listTeamUserGUI = listTeamUserGUI;
        this.leagueManager = leagueManager;
        this.league = league;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        List<League> leagues;
        try {
            leagues = leagueManager.listLeagues();
            System.out.println(leagues.size());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = listLeaguesUserGUI.getTable().rowAtPoint(e.getPoint());
            if (selectedRow != -1) {
                String leagueName = listLeaguesUserGUI.getTable().getValueAt(selectedRow, 0).toString();
                for(i = 0; i < leagues.size(); i++){
                    if (leagueName.equals(leagues.get(i).getName())) {
                        listTeamUserGUI.setTitle(leagues.get(i).getName());
                        mainFrame.showTeamsView();
                    }
                }

            }
        }
    }
}
