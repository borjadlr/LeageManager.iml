package Presentation.Controllers;
import Business.Entities.League;
import Business.Managers.LeagueManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.MenuAdminGUI;
import Presentation.Views.ShowLeague;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuAdminController implements ActionListener{

    private final MenuAdminGUI view;
    private final MainFrameGUI mainFrameGUI;
    private final LeagueManager leagueManager;
    private final ShowLeague showLeague;

    public MenuAdminController(MainFrameGUI mainFrame, LeagueManager leagueManager, ShowLeague showLeague, MenuAdminGUI view) {
        this.leagueManager = leagueManager;
        this.mainFrameGUI = mainFrame;
        this.showLeague = showLeague;
        this.view = view;
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
                    break;
                case "VIEW_LEAGUES":
                    try {
                        leagues = leagueManager.listLeagues();
                        showLeague.addLeagues(leagues);
                        mainFrameGUI.showLeague();
                    } catch (SQLException | NullPointerException ex) {
                        view.exceptionMessage(ex.getMessage());
                    }
                    break;
            }
        }
    }
}
