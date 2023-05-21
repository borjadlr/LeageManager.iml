package Presentation.Controllers;

import Business.Managers.UserManager;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ShowLeague;
import Presentation.Views.TeamListGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ShowLeagueController extends MouseInputAdapter {
    private final ShowLeague view;
    private final MainFrameGUI mainFrame;
    private final TeamListGUI teamListGUI;
    private UserManager userManager;


    public ShowLeagueController(ShowLeague showLeagues, MainFrameGUI mainFrame, UserManager leagues, TeamListGUI teamListGUI) {
        this.view = showLeagues;
        this.mainFrame = mainFrame;
        this.userManager = leagues;
        this.teamListGUI = teamListGUI;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = view.getTable().rowAtPoint(e.getPoint());
            if (selectedRow != -1) {
                String leagueName = view.getTable().getValueAt(selectedRow, 0).toString();
                try {
                    for (i = 0; userManager.getUserLeagues().size() > i; i++){
                        if (leagueName.equals(userManager.getUserLeagues().get(i).getName())) {
                            teamListGUI.setTitle(userManager.getUserLeagues().get(i).getName());
                            mainFrame.showTeamList();
                        }
                    }
                } catch (SQLException ex) {
                    view.parseMessage();
                }


            }
        }
    }
}
