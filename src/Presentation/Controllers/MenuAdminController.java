package Presentation.Controllers;
import Business.Entities.League;
import Business.Managers.LeagueManager;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuAdminController implements ActionListener{

    private final MenuAdminGUI view;
    private final MainFrameGUI mainFrameGUI;
    private final LeagueManager leagueManager;
    private final ListLeagueUserGUI listLeagueUserGUI;
    private final ListLeagueAdminGUI listLeagueAdminGUI;
    private final StatisticsGUI statisticsGUI;

    public MenuAdminController(MainFrameGUI mainFrame, LeagueManager leagueManager, ListLeagueUserGUI listLeagueUserGUI, MenuAdminGUI view, ListLeagueAdminGUI listLeagueAdminGUI, StatisticsGUI statisticsGUI) {
        this.leagueManager = leagueManager;
        this.mainFrameGUI = mainFrame;
        this.listLeagueUserGUI = listLeagueUserGUI;
        this.view = view;
        this.statisticsGUI = statisticsGUI;
        this.listLeagueAdminGUI = listLeagueAdminGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<League> leagues;

        if (e.getSource() instanceof JButton) {
            switch (e.getActionCommand()) {
                case "CREATE_NEW_ACCOUNT":
                    mainFrameGUI.showLogin();
                    break;
                case "CREATE_NEW_TEAM":
                    mainFrameGUI.showNewTeam();
                    break;
                case "NEW_LEAGUE":
                    mainFrameGUI.showMenuNewLeague();
                    break;
                case "DELETE_LEAGUE":
                    mainFrameGUI.showLeague();
                    try {
                        leagues = leagueManager.listLeagues();
                        System.out.println(leagues.size());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(leagues.size());
                    break;
                case "VIEW_LEAGUES":
                    try {
                        leagues = leagueManager.listLeagues();
                        listLeagueAdminGUI.addLeagues(leagues);
                        mainFrameGUI.showLeague();
                    } catch (SQLException | NullPointerException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    break;

                case "VIEW_STATISTICS":
                    System.out.println("perro");
                    mainFrameGUI.showStatistics();
                    break;
            }
        }
    }
}
