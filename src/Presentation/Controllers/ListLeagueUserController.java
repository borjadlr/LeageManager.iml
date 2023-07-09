package Presentation.Controllers;

import Business.Entities.League;
import Business.Managers.LeagueManager;
import Business.Managers.UserManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ListLeagueUserGUI;
import Presentation.Views.ListTeamUserGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
/**
 * The controller class for the ListLeagueUserGUI.
 * Handles the mouse events performed in the list league user view.
 */
public class ListLeagueUserController extends MouseInputAdapter {
    private final ListLeagueUserGUI listLeaguesUserGUI;
    private final MainFrameGUI mainFrame;
    private final ListTeamUserGUI listTeamUserGUI;
    private final UserManager userManager;

    /**
     * Constructs a ListLeagueUserController object.
     *
     * @param listLeaguesUserGUI The ListLeagueUserGUI instance.
     * @param mainFrame          The MainFrameGUI instance.
     * @param listTeamUserGUI    The ListTeamUserGUI instance.
     * @param userManager      The LeagueManager instance.
     */
    public ListLeagueUserController(ListLeagueUserGUI listLeaguesUserGUI, MainFrameGUI mainFrame, ListTeamUserGUI listTeamUserGUI, UserManager userManager) {
        this.listLeaguesUserGUI = listLeaguesUserGUI;
        this.mainFrame = mainFrame;
        this.listTeamUserGUI = listTeamUserGUI;
        this.userManager = userManager;
    }
    /**
     * Handles the mouse clicked events in the list league user view.
     *
     * @param e The MouseEvent that occurred.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        try {
            List<League> leagues = userManager.getUserLeagues();
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
        } catch (SQLException ex) {
            listLeaguesUserGUI.exceptionMessage(ex.getMessage());
        }

    }
}
