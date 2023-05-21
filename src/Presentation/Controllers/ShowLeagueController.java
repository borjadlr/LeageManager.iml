package Presentation.Controllers;

import Business.Entities.League;
import Presentation.Views.MainFrameGUI;
import Presentation.Views.ShowLeague;
import Presentation.Views.TeamListGUI;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.PhantomReference;
import java.util.List;

public class ShowLeagueController extends MouseInputAdapter {
    private final ShowLeague showLeagues;
    private final MainFrameGUI mainFrame;
    private final TeamListGUI teamListGUI;

    private List<League> leagues;


    public ShowLeagueController(ShowLeague showLeagues, MainFrameGUI mainFrame, List<League> leagues, TeamListGUI teamListGUI) {
        this.showLeagues = showLeagues;
        this.mainFrame = mainFrame;
        this.leagues = leagues;
        this.teamListGUI = teamListGUI;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int i;

        if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
            int selectedRow = showLeagues.getTable().rowAtPoint(e.getPoint());
            if (selectedRow != -1) {
                String leagueName = showLeagues.getTable().getValueAt(selectedRow, 0).toString();
                for(i = 0; i < leagues.size(); i++){
                    if (leagueName.equals(leagues.get(i).getName())) {
                        teamListGUI.setTitle(leagues.get(i).getName());
                        mainFrame.showTeamList(); //passar la i, el nom de la lliga.

                    }
                }

            }
        }
    }
}
